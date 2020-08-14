package com.example.springexample.repository;

import com.example.springexample.pojo.entity.D;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DRepository extends JpaRepository<D, Integer> {
}
