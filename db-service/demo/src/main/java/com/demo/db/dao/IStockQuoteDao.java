package com.demo.db.dao;

import java.util.List;

public interface IStockQuoteDao {
	
	List<String> getQuotes(String usrName);
	List<String> addQuote(String usrName, String quote);

}
