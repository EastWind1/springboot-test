package com.example.springexample.pojo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Data
public class B {
  @Id
  private Integer id;
  private BigDecimal amount;
  @ManyToOne
  @JoinColumn(name = "parent_id")
  private A parent;

}
