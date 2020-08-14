package com.example.springexample.pojo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class A {
  @Id
  private Integer id;
  private BigDecimal amount;
  private Integer version;
  private Date date;
}
