package com.nnk.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleService;

@SpringBootTest
public class RulesServiceTests {

      @Autowired
	private RuleService ruleService;
	private static int idRule;

	 private static RuleName ruleToAdd = new RuleName();

	@BeforeAll
    public  static void setup() { 
		ruleToAdd.setId(102);   
        ruleToAdd.setName("nameAd");
        ruleToAdd.setDescription("desAd");
        ruleToAdd.setJson("jsonAd");
        ruleToAdd.setTemplate("tempAd");
        ruleToAdd.setSqlStr("sqlAd");
        ruleToAdd.setSqlPart("sqlPartAd");
        idRule = 102;
    }

	@AfterEach
    public void deleteAddedRule() {
	ruleService.deleteRule(idRule);
    }
	

	@Test
	public void shouldRetrieveNonEMptyRule(){
		Assertions.assertNotNull(ruleService.getRules());
	}
	@Test
	public void shouldAddSuccessfullyRule(){
		ruleService.postRule(ruleToAdd);
		List<RuleName> listRatings = ruleService.getRules();
		Assertions.assertEquals(listRatings.get(listRatings.size() - 1).getName(),
												 ruleToAdd.getName());
	}

	@Test
	public void shouldEditSuccefullyRule(){
		ruleService.postRule(ruleToAdd);
		List<RuleName> listRuleNames = ruleService.getRules();
		int initSize = listRuleNames.size();
		RuleName ruleToEdit = new RuleName();
		ruleToEdit.setId(listRuleNames.get(initSize -1).getId());
        ruleToEdit.setName("nameAdd");
        ruleToEdit.setDescription("desAdd");
        ruleToEdit.setJson("jsonAdd");
        ruleToEdit.setTemplate("tempAdd");
        ruleToEdit.setSqlStr("sqlAdd");
        ruleToEdit.setSqlPart("sqlPartAdd");
		ruleService.putRule(ruleToEdit.getId(),ruleToEdit.getName(),ruleToEdit.getDescription(),
							ruleToEdit.getJson(), ruleToEdit.getTemplate(), ruleToEdit.getSqlStr(), ruleToEdit.getSqlPart());
		listRuleNames = ruleService.getRules();
		initSize = listRuleNames.size();
		Assertions.assertEquals(ruleToEdit.getName(), listRuleNames.get(initSize-1).getName());
		Assertions.assertEquals(ruleToEdit.getDescription(), listRuleNames.get(initSize-1).getDescription());
		Assertions.assertEquals(ruleToEdit.getJson(), listRuleNames.get(initSize-1).getJson());
		Assertions.assertEquals(ruleToEdit.getTemplate(), listRuleNames.get(initSize -1).getTemplate());
		Assertions.assertEquals(ruleToEdit.getSqlStr(), listRuleNames.get(initSize -1).getSqlStr());
		Assertions.assertEquals(ruleToEdit.getSqlPart(), listRuleNames.get(initSize -1).getSqlPart());

	}

	@Test
	public void shouldNotEditSuccefullyRule(){
		ruleService.postRule(ruleToAdd);
		List<RuleName> listRuleNames = ruleService.getRules();
		int initSize = listRuleNames.size();
		RuleName ruleToEdit = new RuleName();
		ruleToEdit.setId(listRuleNames.get(initSize-1).getId()+1);
        ruleToEdit.setName("nameAdd");
        ruleToEdit.setDescription("desAdd");
        ruleToEdit.setJson("jsonAdd");
        ruleToEdit.setTemplate("tempAdd");
        ruleToEdit.setSqlStr("sqlAdd");
        ruleToEdit.setSqlPart("sqlPartAdd");
		ruleService.putRule(ruleToEdit.getId(),ruleToEdit.getName(),ruleToEdit.getDescription(),
							ruleToEdit.getJson(), ruleToEdit.getTemplate(), ruleToEdit.getSqlStr(), ruleToEdit.getSqlPart());
		listRuleNames = ruleService.getRules();
		initSize = listRuleNames.size();
		Assertions.assertNotEquals(ruleToEdit.getName(), listRuleNames.get(initSize-1).getName());
		Assertions.assertNotEquals(ruleToEdit.getDescription(), listRuleNames.get(initSize-1).getDescription());
		Assertions.assertNotEquals(ruleToEdit.getJson(), listRuleNames.get(initSize-1).getJson());
		Assertions.assertNotEquals(ruleToEdit.getTemplate(), listRuleNames.get(initSize -1).getTemplate());
		Assertions.assertNotEquals(ruleToEdit.getSqlStr(), listRuleNames.get(initSize -1).getSqlStr());
		Assertions.assertNotEquals(ruleToEdit.getSqlPart(), listRuleNames.get(initSize -1).getSqlPart());

	}

	@Test
    public void shouldDeleteSuccessfullyRule() {
		List<RuleName> listRuleNames = ruleService.getRules();
		int initSize = listRuleNames.size();

		ruleService.postRule(ruleToAdd);
		listRuleNames = ruleService.getRules();
		assertEquals(initSize + 1, listRuleNames.size());

		ruleService.deleteRule(listRuleNames.get(initSize).getId());
		listRuleNames = ruleService.getRules();	
		assertEquals(initSize, listRuleNames.size());
		
    }

}
