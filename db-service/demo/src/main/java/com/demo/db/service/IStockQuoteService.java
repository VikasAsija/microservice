package com.demo.db.service;

import java.util.List;

public interface IStockQuoteService {
	
	List<String> getQuotes(String usrName);
	List<String> addQuote(String usrName, String quote); 

}
