package com.example.springexample.controller;

import java.util.*;
import java.util.concurrent.CountDownLatch;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.springexample.config.I18nConfig;
import com.example.springexample.pojo.dto.Doc;
import com.example.springexample.repository.ARepository;
import com.example.springexample.repository.DRepository;
import com.example.springexample.service.AService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Slf4j
@RestController
@RequestMapping("")
public class DocController {
    private final I18nConfig i18nConfig;
    private final EntityManager entityManager;
    private final ARepository aRepository;
    private final DRepository dRepository;
    private final AService aService;
    private final RequestMappingHandlerMapping requestMappingHandlerMapping;
    public DocController(I18nConfig i18nConfig, EntityManager entityManager, ARepository aRepository, DRepository dRepository, AService aService, RequestMappingHandlerMapping requestMappingHandlerMapping) {
        this.i18nConfig = i18nConfig;
        this.entityManager = entityManager;
        this.aRepository = aRepository;
        this.dRepository = dRepository;
        this.aService = aService;
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
    }

    @GetMapping("api")
    public Set<RequestMappingInfo> getApi() {

        return requestMappingHandlerMapping.getHandlerMethods().keySet();
    }
    @PutMapping("date")
    public Doc getDoc(@RequestBody Doc doc) {
        log.info(doc.getDate().toString());
        return doc;
    }
    @GetMapping("date")
    @Transactional
    public Date getDate() {
        String sql = "insert into a (amount, version) values (?1, 0)";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, 20);
        query.executeUpdate();
        log.info("getRequest");
        return new Date();
    }
    @GetMapping("properties")
    public String getProperties(@RequestParam String key) {
       return i18nConfig.getI18n(key);
    }
    @GetMapping("like")
    public List like() {
        return aRepository.findByIdLike(2);
    }
    @GetMapping("test")
    public void test() {
        aService.test1();
    }

    @GetMapping("task")
    public void task() {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        try {

            //核心线程数
            threadPoolTaskExecutor.setCorePoolSize(5);
            threadPoolTaskExecutor.setAllowCoreThreadTimeOut(true);
            //线程存活时间
            threadPoolTaskExecutor.setKeepAliveSeconds(100);
            //最大线程数
            threadPoolTaskExecutor.setMaxPoolSize(5);
            //配置队列大小
            threadPoolTaskExecutor.setQueueCapacity(50);
            //配置线程池前缀
            threadPoolTaskExecutor.setThreadNamePrefix("banktransdetail-async-service-");
            // 设置任务执行完关闭
            threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
            threadPoolTaskExecutor.initialize();
            Arrays.asList(1,2,3,4,5).forEach(i -> {
                threadPoolTaskExecutor.submit(() -> {
                    log.info("thread ["+ i + "] start");

                    try {
                        if (i == 3) {
                            throw new Exception();
                        }
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    log.info("thread ["+ i + "] end");
                    countDownLatch.countDown();
                });
            });

            log.info("threadpool preend");
            countDownLatch.await();
            threadPoolTaskExecutor.shutdown();

            log.info("threadpool end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

            log.info("final end");
        }
    }
}
