package com.rjaco.dto;

public class TransactionReportDTO {

	private double expense;
	private double income;
	private String date;

	public TransactionReportDTO() {
	}

	public TransactionReportDTO(double expense, double income, String date) {
		super();
		this.expense = expense;
		this.income = income;
		this.date = date;
	}

	public double getExpense() {
		return expense;
	}

	public void setExpense(double expense) {
		this.expense = expense;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
