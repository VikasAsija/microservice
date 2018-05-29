package com.demo.db.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class StockQuoteDao implements IStockQuoteDao{
	
	static Map<String, List<String>> usrQuoteMap = new HashMap<String, List<String>>();
	static {
		List<String> vikasQuotes = new ArrayList<String>();
		vikasQuotes.add("A");
		vikasQuotes.add("B");
		List<String> poojaQuotes = new ArrayList<String>();
		poojaQuotes.add("C");
		poojaQuotes.add("D");
		poojaQuotes.add("E");
		usrQuoteMap.put("vikas", vikasQuotes);
		usrQuoteMap.put("pooja", poojaQuotes);
	}
	
	@Override
	public List<String> getQuotes(String usrName) {
		return usrQuoteMap.get(usrName);
	}
	
	@Override
	public List<String> addQuote(String usrName, String quote) {
		
		List<String> quotes = usrQuoteMap.get(usrName);
		if(quotes == null){
			quotes = new ArrayList<String>();
			usrQuoteMap.put(usrName, quotes);
		}
		quotes.add(quote);
		return getQuotes(usrName);
	}

}
