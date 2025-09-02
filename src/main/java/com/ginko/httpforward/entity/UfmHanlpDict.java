package com.ginko.httpforward.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UFM_HANLP_DICT", schema = "UFM")
public class UfmHanlpDict {

  @Id
  private Long hanlpDictId;
  @Column(name = "WORD") // 對應欄位
  private String word;
  private String nature;
  private String frequency;
  private String hanlpDictStatus;
  private String entryId;
  private java.sql.Date entryDate;
  private String trId;
  private java.sql.Date trDate;


  public Long getHanlpDictId() {
    return hanlpDictId;
  }

  public void setHanlpDictId(Long hanlpDictId) {
    this.hanlpDictId = hanlpDictId;
  }


  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }


  public String getNature() {
    return nature;
  }

  public void setNature(String nature) {
    this.nature = nature;
  }


  public String getFrequency() {
    return frequency;
  }

  public void setFrequency(String frequency) {
    this.frequency = frequency;
  }


  public String getHanlpDictStatus() {
    return hanlpDictStatus;
  }

  public void setHanlpDictStatus(String hanlpDictStatus) {
    this.hanlpDictStatus = hanlpDictStatus;
  }


  public String getEntryId() {
    return entryId;
  }

  public void setEntryId(String entryId) {
    this.entryId = entryId;
  }


  public java.sql.Date getEntryDate() {
    return entryDate;
  }

  public void setEntryDate(java.sql.Date entryDate) {
    this.entryDate = entryDate;
  }


  public String getTrId() {
    return trId;
  }

  public void setTrId(String trId) {
    this.trId = trId;
  }


  public java.sql.Date getTrDate() {
    return trDate;
  }

  public void setTrDate(java.sql.Date trDate) {
    this.trDate = trDate;
  }

}
