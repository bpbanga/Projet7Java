package com.nnk.springboot.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
public class CurvePointService {
    @Autowired
    private  CurvePointRepository curvePointRepository;

        private static final Logger logger = LogManager.getLogger("BidListService");
@Transactional(rollbackFor = { Exception.class})
        public List<CurvePoint> getCurvePoints() {

	return (List<CurvePoint>)curvePointRepository.findAll();
    }


@Transactional(rollbackFor = { Exception.class})
    public void postCurvePoint( CurvePoint curvePointToAdd){

        curvePointRepository.save(curvePointToAdd);
    }

@Transactional(rollbackFor = { Exception.class})
    public void putCurvePoint(int curvePointToUpdateId, int id, Double term , Double value)  {
        Optional <CurvePoint> curvePointUpdate = curvePointRepository.findById(curvePointToUpdateId);
        if ( curvePointUpdate.isPresent()){
            CurvePoint curvePoint = curvePointUpdate.get();
            curvePoint.setCurveId(id);
            curvePoint.setTerm(term);
            curvePoint.setValue(value);
            curvePointRepository.save(curvePoint);
        }
        
        
    }
@Transactional(rollbackFor = { Exception.class})
    public void deleteCurvePoint(int curvePointToDeleteId){
        Optional <CurvePoint> curvePointToDelete = curvePointRepository.findById(curvePointToDeleteId);
        if ( curvePointToDelete.isPresent()){

            curvePointRepository.delete(curvePointToDelete.get());
        }



    }


}
