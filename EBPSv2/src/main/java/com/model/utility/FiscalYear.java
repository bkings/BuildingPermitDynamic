package com.model.utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.DateConvert;

@Entity
@Table(name = "fiscal_year")
public class FiscalYear implements java.io.Serializable {

    @Id
    @Column(name = "year_code")
    private Integer yearCode;
    @Column(name = "year_name", nullable = false)
    private String yearName;
    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private java.util.Date startDate;
    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private java.util.Date endDate;
    @Column(name = "status", columnDefinition = "VARCHAR(1)")
    private String status;

    public Integer getYearCode() {
        return yearCode;
    }

    public void setYearCode(Integer yearCode) {
        this.yearCode = yearCode;
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }

    public String getStartDate() {
//    System.out.println(startDate);
        return DateConvert.adtobsDate(startDate);
    }

    public void setStartDate(String startDate) {
//    System.out.println(startDate);
        this.startDate = DateConvert.bsToAdDate(startDate);
//    System.out.println("Date conversion:: "+this.startDate + " " + startDate);
    }

    public Object getEndDate() {
        try {
            return DateConvert.adtobsDate(endDate);
        } catch (Exception e) {
        }
        return "";
    }

    public void setEndDate(String endDate) {

        this.endDate = DateConvert.bsToAdDate(endDate);
    System.out.println(this.endDate + " " + endDate);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\n{\"yearCode\": \"" + yearCode + "\",\"yearName\": \"" + yearName + "\",\"startDate\": \"" + startDate + "\",\"endDate\": \"" + endDate + "\",\"status\": \"" + status + "\"}";
    }
}
