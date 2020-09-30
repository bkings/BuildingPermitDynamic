package com.model.utility;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "unit_conversion",uniqueConstraints=@UniqueConstraint(columnNames={"unit", "conversion_unit"}))
public class UnitConversion implements java.io.Serializable{
  @Id 
  @Column(name = "id")
  private Integer id;
   @Column(name = "unit")
  private String unit;
  @Column(name = "qty")
  private Integer qty; 
  @Column(name = "conversion_unit")
  private String conversionUnit;
  @Column(name = "conversion_qty")
  private Float conversionQty;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getConversionUnit() {
        return conversionUnit;
    }

    public void setConversionUnit(String conversionUnit) {
        this.conversionUnit = conversionUnit;
    }

    public Float getConversionQty() {
        return conversionQty;
    }

    public void setConversionQty(Float conversionQty) {
        this.conversionQty = conversionQty;
    }
  @Override
    public String toString() {
return "\n{\"id\": \""+id+"\",\"qty\": \""+qty+"\",\"unit\": \""+unit+"\",\"conversionUnit\": \""+conversionUnit+"\",\"conversionQty\": \""+conversionQty+"\"}";
}
    
}