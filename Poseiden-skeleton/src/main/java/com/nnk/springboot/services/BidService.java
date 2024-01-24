package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.repositories.BidListRepository;
@Service
public class BidService {

     @Autowired
    private  BidListRepository bidListRepository;
    private static final Logger logger = LogManager.getLogger("BidListService");

@Transactional(rollbackFor = { Exception.class})
    public List<Bid> getBidLists() {

	return (List<Bid>)bidListRepository.findAll();
    }


@Transactional(rollbackFor = { Exception.class})
    public void postBid( Bid bidToAdd){

        bidListRepository.save(bidToAdd);
    }

@Transactional(rollbackFor = { Exception.class})
    public void putBid(int bidToUpdateId, String accountUp , String typeUp, Double bidQuantityUp)  {
        Optional <Bid> bidUpdate = bidListRepository.findById(bidToUpdateId);
        if ( bidUpdate.isPresent()){
            bidUpdate.get().setAccount(accountUp);
            bidUpdate.get().setType(typeUp);
            bidUpdate.get().setBidQuantity(bidQuantityUp);
            bidListRepository.save(bidUpdate.get());
        }
        
        
    }

@Transactional(rollbackFor = { Exception.class})
    public void deleteBid(int bidToDeleteId){
        Optional <Bid> bidToDelete = bidListRepository.findById(bidToDeleteId);
        if ( bidToDelete.isPresent()){

            bidListRepository.delete(bidToDelete.get());
        }



    }

}
