package com.nnk.springboot;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.repositories.BidListRepository;

@SpringBootTest
public class BidTests {

    @Autowired
	private BidListRepository bidListRepository;

    @Test
	public void bidListTest() {

		Bid bid = new Bid(5, "account", "type", 10d);
		
		// Save
		bid = bidListRepository.save(bid);
		Assertions.assertNotNull(bid.getBidListId());
		Assertions.assertEquals(bid.getBidQuantity(), 10d, 10d);

		// Update
		bid.setBidQuantity(20d);
		bid = bidListRepository.save(bid);
		Assertions.assertEquals(bid.getBidQuantity(), 20d, 20d);

		// Find
		List<Bid> listResult = (List<Bid>) bidListRepository.findAll();
		Assertions.assertTrue(listResult.size() > 0);

		// Delete
		Integer id = bid.getBidListId();
		bidListRepository.delete(bid);
		Optional<Bid> bidList = bidListRepository.findById(id);
		Assertions.assertFalse(bidList.isPresent());
	}

}
