package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@SpringBootTest
public class CurvePointServiceTests {

	

	@Autowired
	private CurvePointService curvePointService;
	private static int idCurvePoint;

	 private static CurvePoint curvePointToAdd = new CurvePoint();

	@BeforeAll
    public  static void setup() {

	curvePointToAdd.setId(202);
	curvePointToAdd.setCurveId(202);
	curvePointToAdd.setTerm(2020d);
	curvePointToAdd.setValue(235d);
    idCurvePoint = 202;
    }

	@AfterEach
    public void deleteAddedCurvePoint() {
	curvePointService.deleteCurvePoint(idCurvePoint);
    }
	

	@Test
	public void shouldRetrieveNonEMptyCurvePoint(){
		Assertions.assertNotNull(curvePointService.getCurvePoints());
	}
	@Test
	public void shouldAddSuccessfullyCurvePoint(){
		curvePointService.postCurvePoint(curvePointToAdd);
		List<CurvePoint> listCurvePoints = curvePointService.getCurvePoints();
		Assertions.assertEquals(listCurvePoints.get(listCurvePoints.size() - 1).getCurveId(),
												 curvePointToAdd.getCurveId());
	}

	@Test
	public void shouldEditSuccefullyCurvePoint(){
		curvePointService.postCurvePoint(curvePointToAdd);
		List<CurvePoint> listCurvePoints = curvePointService.getCurvePoints();
		int initSize = listCurvePoints.size();
		CurvePoint curvePointToEdit = new CurvePoint();
		curvePointToEdit.setId(listCurvePoints.get(initSize-1).getId());
		curvePointToEdit.setCurveId(222);
		curvePointToEdit.setTerm(2026d);
		curvePointToEdit.setValue(239d);

		curvePointService.putCurvePoint(curvePointToEdit.getId(), curvePointToEdit.getCurveId(),curvePointToEdit.getTerm(),
								  curvePointToEdit.getValue());
		listCurvePoints = curvePointService.getCurvePoints();
		initSize = listCurvePoints.size();

		Assertions.assertEquals(curvePointToEdit.getCurveId(), listCurvePoints.get(initSize-1).getCurveId());
		Assertions.assertEquals(curvePointToEdit.getTerm(), listCurvePoints.get(initSize-1).getTerm());
		Assertions.assertEquals(curvePointToEdit.getValue(), listCurvePoints.get(initSize-1).getValue());
	}

@Test
	public void shouldNotEditSuccefullyCurvePoint(){
		curvePointService.postCurvePoint(curvePointToAdd);
		List<CurvePoint> listCurvePoints = curvePointService.getCurvePoints();
		int initSize = listCurvePoints.size();
		CurvePoint curvePointToEdit = new CurvePoint();
		curvePointToEdit.setId(listCurvePoints.get(initSize-1).getId()+1);
		curvePointToEdit.setCurveId(222);

	curvePointToEdit.setTerm(2026d);
	curvePointToEdit.setValue(239d);

		curvePointService.putCurvePoint(curvePointToEdit.getId(), curvePointToEdit.getCurveId(),curvePointToEdit.getTerm(),
								  curvePointToEdit.getValue());
		listCurvePoints = curvePointService.getCurvePoints();
		initSize = listCurvePoints.size();

		Assertions.assertNotEquals(curvePointToEdit.getCurveId(), listCurvePoints.get(initSize-1).getCurveId());
		Assertions.assertNotEquals(curvePointToEdit.getTerm(), listCurvePoints.get(initSize-1).getTerm());
		Assertions.assertNotEquals(curvePointToEdit.getValue(), listCurvePoints.get(initSize-1).getValue());
	}

	@Test
    public void shouldDeleteSuccessfullyCurvePoint() {
		List<CurvePoint> listCurvePoints = curvePointService.getCurvePoints();
		int initSize = listCurvePoints.size();

		curvePointService.postCurvePoint(curvePointToAdd);
	 	listCurvePoints = curvePointService.getCurvePoints();
		assertEquals(initSize + 1, listCurvePoints.size());
		curvePointService.deleteCurvePoint(listCurvePoints.get(initSize).getId());
		listCurvePoints = curvePointService.getCurvePoints();
		assertEquals(initSize, listCurvePoints.size());
    }


}
