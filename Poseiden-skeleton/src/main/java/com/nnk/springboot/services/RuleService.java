package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.RuleNameRepository;


@Service
public class RuleService {
     @Autowired
    private  RuleNameRepository ruleNameRepository;
    private static final Logger logger = LogManager.getLogger("RuleService");

    public List<RuleName> getRules() {

	return (List<RuleName>)ruleNameRepository.findAll();
    }



    public void postRule( RuleName ruleToAdd){

        ruleNameRepository.save(ruleToAdd);
    }


    public void putRule(int ruleToUpdateId, String nameToUpdate, String descriptionToUpdate,
     String jsonToUpdate, String templateToUpdate,
     String sqlStrToUpdate, String sqlPartToUpdate)  {
        Optional <RuleName> ruleUpdate = ruleNameRepository.findById(ruleToUpdateId);
        if ( ruleUpdate.isPresent()){
            ruleUpdate.get().setName(nameToUpdate);
            ruleUpdate.get().setDescription(descriptionToUpdate);
            ruleUpdate.get().setJson(jsonToUpdate);
            ruleUpdate.get().setTemplate(templateToUpdate);
            ruleUpdate.get().setSqlStr(sqlStrToUpdate);
            ruleUpdate.get().setSqlPart(sqlPartToUpdate);
            ruleNameRepository.save(ruleUpdate.get());
        }
        
        
    }

    public void deleteRule(int bidToDeleteId){
        Optional <RuleName> bidToDelete = ruleNameRepository.findById(bidToDeleteId);
        if ( bidToDelete.isPresent()){

            ruleNameRepository.delete(bidToDelete.get());
        }



    }

}
