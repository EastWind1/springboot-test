package com.example.springexample.service;

import com.example.springexample.repository.ARepository;
import com.example.springexample.repository.DRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
@Slf4j
public class AService {
    private final ARepository aRepository;
    private final DRepository dRepository;
    private final TransactionTemplate transactionTemplate;
    private final EntityManager entityManager;
    public AService(ARepository aRepository, DRepository dRepository, TransactionTemplate transactionTemplate, EntityManager entityManager) {
        this.aRepository = aRepository;
        this.dRepository = dRepository;
        this.transactionTemplate = transactionTemplate;
        this.entityManager = entityManager;
    }
    @Transactional(rollbackOn = Exception.class)
    public void test1() {
        BService bService = new BService(aRepository, dRepository, entityManager);
        bService.test1();
        log.error("awsl");
    }
}
