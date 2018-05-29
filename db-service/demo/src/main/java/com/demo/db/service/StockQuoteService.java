package com.demo.db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.db.dao.IStockQuoteDao;

@Service
public class StockQuoteService implements IStockQuoteService {
	
	@Autowired
	private IStockQuoteDao stockQuoteDao;
	
	@Override
	public List<String> getQuotes(String usrName) {
		return stockQuoteDao.getQuotes(usrName);
	}
	
	public List<String> addQuote(String usrName, String quote) {
		
		return stockQuoteDao.addQuote(usrName, quote);
	}
}
