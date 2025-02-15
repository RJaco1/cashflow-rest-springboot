package com.rjaco.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.rjaco.dao.IUserAccountDAO;
import com.rjaco.dto.TransactionDTO;
import com.rjaco.model.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rjaco.dao.ITransactionDAO;
import com.rjaco.dto.TransactionReportDTO;
import com.rjaco.service.ITransactionService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private ITransactionDAO dao;

    @Autowired
    private IUserAccountDAO userDAO;

    @Override
    public Transaction createData(Transaction t) {
        return dao.save(t);
    }

    @Override
    public Transaction updateData(Transaction t) {
        return dao.save(t);
    }

    @Override
    public void deleteData(int id) {
        dao.deleteById(id);
    }

    @Override
    public Page<Transaction> findByCategoryType(Integer id, Pageable pageable) {
        return dao.findByCatType(id, pageable);
    }

    @Override
    public Transaction findData(int id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public List<Transaction> getData() {
        return dao.findAll();
    }

    @Override
    public Page<Transaction> pageable(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public Page<TransactionDTO> getDataDTO(Pageable pageable) {

        List<TransactionDTO> tranDto = new ArrayList<>();
        dao.findAll(pageable).forEach(transaction -> {
            tranDto.add(new TransactionDTO(
                    transaction.getTransactionId(),
                    transaction.getAmount(),
                    transaction.getDate(),
                    transaction.getCategory().getCategoryId(),
                    transaction.getCategory().getCategoryName(),
                    transaction.getCategory().getCategorytype(),
                    transaction.getCurrency().getCurrencyId(),
                    transaction.getCurrency().getCurrency(),
                    transaction.getAccount().getAccountId(),
                    transaction.getAccount().getAccountName(),
                    transaction.getUser().getUserId(),
                    transaction.getUser().getUsername(),
                    transaction.getUser().getEmail()
            ));
        });

        return new PageImpl<TransactionDTO>(tranDto, pageable, tranDto.size());
    }

    @Override
    public Page<TransactionDTO> findTransactionsByUsername(String username, Pageable pageable) {

        UserAccount user = userDAO.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User does not exist", username));
        }

        Page<Transaction> transactionPages = dao.findTranByUserId(user.getUserId(), pageable);
        List<TransactionDTO> tranDto = transactionPages.getContent()
                .stream()
                .map(transaction -> new TransactionDTO(
                        transaction.getTransactionId(),
                        transaction.getAmount(),
                        transaction.getDate(),
                        transaction.getCategory().getCategoryId(),
                        transaction.getCategory().getCategoryName(),
                        transaction.getCategory().getCategorytype(),
                        transaction.getCurrency().getCurrencyId(),
                        transaction.getCurrency().getCurrency(),
                        transaction.getAccount().getAccountId(),
                        transaction.getAccount().getAccountName(),
                        transaction.getUser().getUserId(),
                        transaction.getUser().getUsername(),
                        transaction.getUser().getEmail()
                )).collect(Collectors.toList());

        return new PageImpl<>(tranDto, pageable, transactionPages.getTotalElements());
    }

    @Override
    public Page<TransactionDTO> findUserTransactionsByCatType(String username, Integer id, Pageable pageable) {
        UserAccount user = userDAO.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User does not exist", username));
        }

        Page<Transaction> transactionPages = dao.findUserTransactionsByCatType(user.getUserId(), id, pageable);
        List<TransactionDTO> tranDto = transactionPages.getContent()
                .stream()
                .map(transaction -> new TransactionDTO(
                        transaction.getTransactionId(),
                        transaction.getAmount(),
                        transaction.getDate(),
                        transaction.getCategory().getCategoryId(),
                        transaction.getCategory().getCategoryName(),
                        transaction.getCategory().getCategorytype(),
                        transaction.getCurrency().getCurrencyId(),
                        transaction.getCurrency().getCurrency(),
                        transaction.getAccount().getAccountId(),
                        transaction.getAccount().getAccountName(),
                        transaction.getUser().getUserId(),
                        transaction.getUser().getUsername(),
                        transaction.getUser().getEmail()
                )).collect(Collectors.toList());

        return new PageImpl<>(tranDto, pageable, transactionPages.getTotalElements());
    }

    @Override
    public TransactionDTO findUserTransactionById(String username, Integer id) {
        UserAccount user = userDAO.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User does not exist", username));
        }

        Transaction tran = dao.findUserTransactionById(user.getUserId(), id);

        return new TransactionDTO(
                tran.getTransactionId(),
                tran.getAmount(),
                tran.getDate(),
                tran.getCategory().getCategoryId(),
                tran.getCategory().getCategoryName(),
                tran.getCategory().getCategorytype(),
                tran.getCurrency().getCurrencyId(),
                tran.getCurrency().getCurrency(),
                tran.getAccount().getAccountId(),
                tran.getAccount().getAccountName(),
                tran.getUser().getUserId(),
                tran.getUser().getUsername(),
                tran.getUser().getEmail());
    }

    @Override
    public Transaction createUserTransaction(TransactionDTO transactionDTO) {
        UserAccount user = userDAO.findOneByUsername(transactionDTO.getUsername());

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User does not exist", transactionDTO.getUsername()));
        }

        Transaction tran = getTransaction(transactionDTO, user);

        return dao.save(tran);
    }

    @Override
    public Transaction updateUserTransaction(TransactionDTO transactionDTO) {
        UserAccount user = userDAO.findOneByUsername(transactionDTO.getUsername());

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User does not exist", transactionDTO.getUsername()));
        }

        Transaction tran = getTransaction(transactionDTO, user);

        return dao.save(tran);
    }

    @Override
    public void deleteUserTransaction(String username, Integer id) {
        UserAccount user = userDAO.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User does not exist", username));
        }

        dao.deleteById(id);
    }

    @NotNull
    private static Transaction getTransaction(TransactionDTO transactionDTO, UserAccount user) {
        Category cat = new Category();
        cat.setCategoryId(transactionDTO.getCategoryId());
        cat.setCategoryName(transactionDTO.getCategoryName());

        Currency cur = new Currency();
        cur.setCurrencyId(transactionDTO.getCurrencyId());
        cur.setCurrency(transactionDTO.getCurrency());

        Account acc = new Account();
        acc.setAccountId(transactionDTO.getAccountId());
        acc.setAccountName(transactionDTO.getAccountName());

        Transaction tran = new Transaction();
        tran.setTransactionId(transactionDTO.getTransactionId());
        tran.setAmount(transactionDTO.getAmount());
        tran.setDate(transactionDTO.getDate());
        tran.setCategory(cat);
        tran.setCurrency(cur);
        tran.setAccount(acc);
        tran.setUser(user);

        return tran;
    }

    @Override
    public List<TransactionReportDTO> listTransactionReport() {
        List<TransactionReportDTO> t = new ArrayList<>();
        dao.transactionsReport().forEach(x -> {
            TransactionReportDTO tDTO = new TransactionReportDTO();
            tDTO.setExpense(Double.parseDouble(String.valueOf(x[0])));
            tDTO.setIncome(Double.parseDouble(String.valueOf(x[1])));
            tDTO.setDate(String.valueOf(x[2]));
            t.add(tDTO);
        });
        return t;
    }

    @Override
    public List<TransactionReportDTO> findUserTransactionsReport(String username) {
        UserAccount user = userDAO.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("User does not exist", username));
        }

        return dao.userTransactionsReport(user.getUserId()).stream().map(x -> {
            TransactionReportDTO tDto = new TransactionReportDTO();
            tDto.setExpense(Double.parseDouble(String.valueOf(x[0])));
            tDto.setIncome(Double.parseDouble(String.valueOf(x[1])));
            tDto.setDate(String.valueOf(x[2]));
            return tDto;
        }).collect(Collectors.toList());
    }

    @Override
    public byte[] generateReport() {
        byte[] data = null;
        try {
            InputStream file = new ClassPathResource("/reports/transactions.jasper").getInputStream();
            JasperPrint print = JasperFillManager.fillReport(file, null,
                    new JRBeanCollectionDataSource(this.listTransactionReport()));
            data = JasperExportManager.exportReportToPdf(print);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}
