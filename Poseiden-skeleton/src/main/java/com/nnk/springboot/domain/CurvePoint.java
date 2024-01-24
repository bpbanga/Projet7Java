package com.nnk.springboot.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;


/**
 * application curvepoint model class
 */
@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "must not be null")
    private Integer curveId;

    private Timestamp asOfDate;

    @Min(value = 1)
    private Double term;
    
    @Min(value = 1)
    private Double value;

    private Timestamp creationDate;

    public CurvePoint() {
    }

/**
 * simple class constructor
 * @param id
 * @param curveId
 * @param asOfDate
 * @param term
 * @param value
 * @param creationDate
 */    


    public CurvePoint(Integer id, @NotNull(message = "must not be null") Integer curveId, Timestamp asOfDate,
            Double term, Double value, Timestamp creationDate) {
        this.id = id;
        this.curveId = curveId;
        this.asOfDate = asOfDate;
        this.term = term;
        this.value = value;
        this.creationDate = creationDate;
    }


/**
 * constructor form
 * @param id
 * @param curveId
 * @param term
 * @param value
 */

    public CurvePoint(Integer id, @NotNull(message = "must not be null") Integer curveId, Double term, Double value) {
        this.id = id;
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCurveId() {
        return curveId;
    }

    public void setCurveId(Integer curveId) {
        this.curveId = curveId;
    }

    public Timestamp getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(Timestamp asOfDate) {
        this.asOfDate = asOfDate;
    }

    public Double getTerm() {
        return term;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

}
