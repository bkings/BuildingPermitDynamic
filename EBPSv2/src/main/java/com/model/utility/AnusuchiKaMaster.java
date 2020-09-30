package com.model.utility;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "anusuchi_ka_master")
public class AnusuchiKaMaster implements java.io.Serializable{
  @Id 
  @Column(name = "ID")
  private String id;
  @Column(name = "NAME")
  private String name;
  @Column(name = "NAME_NEPALI",columnDefinition = "VARCHAR")
  private String nameNepali;

    public AnusuchiKaMaster() {
    }

    public AnusuchiKaMaster(String id, String name, String nameNepali) {
        this.id = id;
        this.name = name;
        this.nameNepali = nameNepali;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameNepali() {
        return nameNepali;
    }

    public void setNameNepali(String nameNepali) {
        this.nameNepali = nameNepali;
    }

  @Override
    public String toString() {
return "\n{\"id\": \""+id+"\",\"name\": \""+name+"\",\"nameNepali\": \""+nameNepali+"\"}";
}
}