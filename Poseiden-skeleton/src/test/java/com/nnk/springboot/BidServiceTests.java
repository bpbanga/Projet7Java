package com.nnk.springboot;

import com.nnk.springboot.domain.Bid;
//import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;


@SpringBootTest
public class BidServiceTests {

	
	@Autowired
	private BidService bidListService;
	private static int idBidList;

	 private static Bid bidToAdd = new Bid();

	@BeforeAll
    public  static void setup() {
	
	bidToAdd.setBidListId(203);
	bidToAdd.setAccount("Account Test");
	bidToAdd.setType("Type Test");
	bidToAdd.setBidQuantity(10d);
    idBidList = 203;
    }

	@AfterEach
    public void deleteAddedBidList() {
	bidListService.deleteBid(idBidList);
    }
	

	@Test
	public void shouldRetrieveNonEMptyBidList(){
		Assertions.assertNotNull(bidListService.getBidLists());
	}
	@Test
	public void shouldAddSuccessfullyBid(){
		bidListService.postBid(bidToAdd);
		List<Bid> listBidLists = bidListService.getBidLists();
		Assertions.assertEquals(listBidLists.get(listBidLists.size() - 1).getAccount(),
												 bidToAdd.getAccount());
	}

	@Test
	public void shouldEditSuccefullyBid(){
		bidListService.postBid(bidToAdd);
		List<Bid> listBidLists = bidListService.getBidLists();
		int initSize = listBidLists.size();
		Bid bidToEdit = new Bid();
		bidToEdit.setBidListId(listBidLists.get(initSize-1).getBidListId());
		bidToEdit.setAccount("Account Tests");
		bidToEdit.setType("Types Tests");
		bidToEdit.setBidQuantity(15d);
		bidListService.putBid(bidToEdit.getBidListId(),bidToEdit.getAccount(),
								  bidToEdit.getType(), bidToEdit.getBidQuantity());
		listBidLists = bidListService.getBidLists();
		initSize = listBidLists.size();

		Assertions.assertEquals(bidToEdit.getAccount(), listBidLists.get(initSize-1).getAccount());
		Assertions.assertEquals(bidToEdit.getType(), listBidLists.get(initSize-1).getType());
		Assertions.assertEquals(bidToEdit.getBidQuantity(), listBidLists.get(initSize-1).getBidQuantity());
	}

	@Test
	public void shouldNotEditSuccefullyBid(){
		bidListService.postBid(bidToAdd);
		List<Bid> listBidLists = bidListService.getBidLists();
		int initSize = listBidLists.size();
		Bid bidToEdit = new Bid();
		bidToEdit.setBidListId(listBidLists.get(initSize-1).getBidListId()+1);
		bidToEdit.setAccount("Account Tests");
		bidToEdit.setType("Types Tests");
		bidToEdit.setBidQuantity(15d);
		bidListService.putBid(bidToEdit.getBidListId(),bidToEdit.getAccount(),
								  bidToEdit.getType(), bidToEdit.getBidQuantity());
		listBidLists = bidListService.getBidLists();
		initSize = listBidLists.size();

		Assertions.assertNotEquals(bidToEdit.getAccount(), listBidLists.get(initSize-1).getAccount());
		Assertions.assertNotEquals(bidToEdit.getType(), listBidLists.get(initSize-1).getType());
		Assertions.assertNotEquals(bidToEdit.getBidQuantity(), listBidLists.get(initSize-1).getBidQuantity());
	}

	@Test
    public void shouldDeleteSuccessfullyBid() {
		List<Bid> listBidLists = bidListService.getBidLists();
		int initSize = listBidLists.size();

		bidListService.postBid(bidToAdd);
		listBidLists = bidListService.getBidLists();
		assertEquals(initSize + 1, listBidLists.size() );

		bidListService.deleteBid(listBidLists.get(initSize).getBidListId());
		listBidLists = bidListService.getBidLists();
		assertEquals(initSize, listBidLists.size());


	
    }


	
}
