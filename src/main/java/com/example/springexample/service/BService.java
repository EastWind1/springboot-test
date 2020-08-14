package com.example.springexample.service;

import com.example.springexample.pojo.entity.D;
import com.example.springexample.repository.ARepository;
import com.example.springexample.repository.DRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

public class BService {
    private final ARepository aRepository;
    private final DRepository dRepository;
    private final EntityManager entityManager;

    public BService(ARepository aRepository, DRepository dRepository, EntityManager entityManager) {
        this.aRepository = aRepository;
        this.dRepository = dRepository;
        this.entityManager = entityManager;
    }
    @Transactional(rollbackFor = Exception.class)
    public void test1() {
        D d = new D();
        d.setIsTrue(false);
        dRepository.save(d);
    }
}
