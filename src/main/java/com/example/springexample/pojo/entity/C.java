package com.example.springexample.pojo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
@Data
public class C {
  @Id
  private Integer id;
  private BigDecimal amount;
  @ManyToOne
  @JoinColumn(name = "parent_id")
  private B parent;
  private Integer version;

}
