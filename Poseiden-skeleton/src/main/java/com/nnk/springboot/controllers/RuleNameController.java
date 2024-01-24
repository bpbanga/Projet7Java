package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleService;

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
public class RuleNameController {
    // TODO: Inject RuleName service
    @Autowired
    private RuleService ruleService;
    @Autowired
    private RuleNameRepository ruleNameRepository;

        private static final Logger logger = LogManager.getLogger("RuleNameController");


    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        // TODO: find all RuleName, add to model
        List<RuleName> listRuleName = ruleService.getRules();
        model.addAttribute("ruleNames", listRuleName);
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if (!result.hasErrors()) {
			logger.info("Returning ruleName/add page");
            model.addAttribute("ruleName", ruleName);
		    ruleService.postRule(ruleName);
             ruleNameRepository.save(ruleName);
            return "redirect:/ruleName/list";
		}
		logger.info("error Redirect: ruleName/add");
        return "ruleName/add";
        // TODO: check data valid and save to db, after saving return RuleName list

        
       
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        Optional<RuleName> rule = ruleNameRepository.findById(id);
        if (!rule.isPresent()){

        }
        model.addAttribute("ruleName", rule.get());
        
        // TODO: get RuleName by Id and to model then show to the form
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
                                if (result.hasErrors()) {
		logger.info("error Redirect ruleName/update ");
			return "ruleName/update";
		}
		logger.info("Returning ruleName/update page");
		model.addAttribute("ruleName", ruleName);
		ruleService.putRule(ruleName.getId(), ruleName.getName(), ruleName.getDescription(), ruleName.getJson(),
         ruleName.getTemplate(), ruleName.getSqlStr(), ruleName.getSqlPart());
        
        // TODO: check required fields, if valid call service to update RuleName and return RuleName list
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
         Optional<RuleName> rule = ruleNameRepository.findById(id);
         ruleService.deleteRule(rule.get().getId());
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list
        return "redirect:/ruleName/list";
    }
}
