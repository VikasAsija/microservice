package com.demo.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.db.model.Quotes;
import com.demo.db.service.IStockQuoteService;


@RestController
@RequestMapping("/rest/db")
public class StockQuoteController {
	
	@Autowired
	private IStockQuoteService stockQuoteService; 
	
	@GetMapping("/{usrname}")
	public List<String> getQuotes(@PathVariable("usrname") final String usrName) {
		
		return stockQuoteService.getQuotes(usrName);
	}
	
	@PostMapping("/add")
	public List<String> addQuote(@RequestBody final Quotes quotes) {
		
		return stockQuoteService.addQuote(quotes.getUsrName(), quotes.getQuote());
	}
}
