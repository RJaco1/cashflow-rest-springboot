package com.rjaco.service;

import java.util.List;

public interface ICRUD<T> {
	
	T createData(T t);
	T updateData(T t);
	void deleteData(int id);
	T listDataUsingId(int id);
	List<T> listData();

}
