package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
@Service
public class TradeService {
     @Autowired
    private  TradeRepository tradeRepository;
    private static final Logger logger = LogManager.getLogger("TradeService");

    public List<Trade> getTrades() {

	 return (List<Trade>)tradeRepository.findAll();
     }



    public void postTrade( Trade tradeToAdd){

        tradeRepository.save(tradeToAdd);
    }


    public void putTrade(int tradeToUpdateId, String accountUp , String typeUp, Double buyQuantityUp)  {
        Optional <Trade> tradeUpdate = tradeRepository.findById(tradeToUpdateId);
        if ( tradeUpdate.isPresent()){
            tradeUpdate.get().setAccount(accountUp);
            tradeUpdate.get().setType(typeUp);
            tradeUpdate.get().setBuyQuantity(buyQuantityUp);
            tradeRepository.save(tradeUpdate.get());
        }
        
        
    }

    public void deleteTrade(int tradeToDeleteId){
        Optional <Trade> tradeToDelete = tradeRepository.findById(tradeToDeleteId);
        if ( tradeToDelete.isPresent()){

            tradeRepository.delete(tradeToDelete.get());
        }



    }

}
