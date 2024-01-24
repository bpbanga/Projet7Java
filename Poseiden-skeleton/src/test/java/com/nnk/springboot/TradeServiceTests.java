package com.nnk.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;

@SpringBootTest
public class TradeServiceTests {

     @Autowired
	private TradeService tradeService;
	private static int idTrade;

	 private static Trade tradeToAdd = new Trade();

	@BeforeAll
    public  static void setup() {
		tradeToAdd.setTradeId(102);
	    tradeToAdd.setAccount("accountUp");
        tradeToAdd.setType("typeUp");
        tradeToAdd.setBuyQuantity(25d);
        idTrade = 102;
    }

	@AfterEach
    public void deleteAddedTrade() {
	tradeService.deleteTrade(idTrade);
    }
	

	@Test
	public void shouldRetrieveNonEMptyTrade(){
		Assertions.assertNotNull(tradeService.getTrades());
	}
	@Test
	public void shouldAddSuccessfullyTrade(){
		tradeService.postTrade(tradeToAdd);
		List<Trade> listTrades = tradeService.getTrades();
		Assertions.assertEquals(listTrades.get(listTrades.size() - 1).getAccount(),
												 tradeToAdd.getAccount());
	}

	@Test
	public void shouldEditSuccefullyTrade(){
		tradeService.postTrade(tradeToAdd);
		List<Trade> listRatings = tradeService.getTrades();
		int initSize = listRatings.size();
		Trade tradeToEdit = new Trade();
		tradeToEdit.setTradeId(listRatings.get(initSize-1).getTradeId());
        tradeToEdit.setAccount("accountUps");
        tradeToEdit.setType("typeUps");
        tradeToEdit.setBuyQuantity(20d);
		tradeService.putTrade(tradeToEdit.getTradeId(),tradeToEdit.getAccount(),tradeToEdit.getType(),
								  tradeToEdit.getBuyQuantity());
		List<Trade> listTrades = tradeService.getTrades();
		initSize = listTrades.size();
		Assertions.assertEquals(tradeToEdit.getAccount(), listTrades.get(initSize-1).getAccount());
		Assertions.assertEquals(tradeToEdit.getType(), listTrades.get(initSize-1).getType());
		Assertions.assertEquals(tradeToEdit.getBuyQuantity(), listTrades.get(initSize-1).getBuyQuantity());
	}

	@Test
	public void shouldNotEditSuccefullyTrade(){
		tradeService.postTrade(tradeToAdd);
		List<Trade> listRatings = tradeService.getTrades();
		int initSize = listRatings.size();
		Trade tradeToEdit = new Trade();
		tradeToEdit.setTradeId(listRatings.get(initSize-1).getTradeId()+1);
        tradeToEdit.setAccount("accountUps");
        tradeToEdit.setType("typeUps");
        tradeToEdit.setBuyQuantity(20d);
		tradeService.putTrade(tradeToEdit.getTradeId(),tradeToEdit.getAccount(),tradeToEdit.getType(),
								  tradeToEdit.getBuyQuantity());
		List<Trade> listTrades = tradeService.getTrades();
		initSize = listTrades.size();
		Assertions.assertNotEquals(tradeToEdit.getAccount(), listTrades.get(initSize-1).getAccount());
		Assertions.assertNotEquals(tradeToEdit.getType(), listTrades.get(initSize-1).getType());
		Assertions.assertNotEquals(tradeToEdit.getBuyQuantity(), listTrades.get(initSize-1).getBuyQuantity());
	}

	@Test
    public void shouldDeleteSuccessfullyTrade() {
		List<Trade> lisTrades = tradeService.getTrades();
		int initSize = lisTrades.size();

		tradeService.postTrade(tradeToAdd);
		lisTrades = tradeService.getTrades();
		assertEquals(initSize + 1, lisTrades.size());

		tradeService.deleteTrade(lisTrades.get(initSize).getTradeId());
		lisTrades = tradeService.getTrades();	
		assertEquals(initSize, lisTrades.size());
    }

}
