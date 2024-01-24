package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Bid;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.TradeService;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
/*
 * 
 */
@Controller
public class TradeController {
    // TODO: Inject Trade service
     @Autowired
    private TradeService tradeService;
    @Autowired
    private TradeRepository tradeRepository;
     private static final Logger logger = LogManager.getLogger("TradeController");

    @RequestMapping("/trade/list")
    public String home(Model model)

    {
        
        List<Trade> listTrades = tradeService.getTrades();
        model.addAttribute("trades", listTrades);
        // TODO: find all Trade, add to model
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade trade) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Trade list

        if (!result.hasErrors()) {
            logger.info("Returning trade/add page");
		model.addAttribute("trade", trade);
		tradeService.postTrade(trade);
        
        return "redirect:/trade/list";	
		}
               logger.info("error Redirect: trade/add");
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Trade by Id and to model then show to the form

         Optional<Trade> trade = tradeRepository.findById(id);
         if(!trade.isPresent()){

         }
        model.addAttribute("trade", trade.get());
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
                                                if (result.hasErrors()) {
			logger.info("error Redirect trade/update ");
            return "trade/update"; 
		}
		logger.info("Returning trade/update page");
		model.addAttribute("trade", trade);
		tradeService.putTrade(trade.getTradeId(), trade.getAccount(), trade.getType(), trade.getBuyQuantity());
       
        // TODO: check required fields, if valid call service to update Trade and return Trade list
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {

         Optional<Trade> trade = tradeRepository.findById(id);
         tradeService.deleteTrade(trade.get().getTradeId());
        // TODO: Find Trade by Id and delete the Trade, return to Trade list
        return "redirect:/trade/list";
    }
}
