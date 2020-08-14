package com.example.springexample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/jdbc")
public class JDBCController {
    private final JdbcTemplate jdbcTemplate;
    private final EntityManager entityManager;
    public JDBCController(JdbcTemplate jdbcTemplate, EntityManager entityManager) {
        this.jdbcTemplate = jdbcTemplate;
        this.entityManager = entityManager;
    }
    @RequestMapping("")
    public String index() {
        return "jdbc test";
    }
    @RequestMapping("a")
    @Cacheable(value = "jdbcCache")
    public List<Map<String, Object>> testA() {
        log.info("start query");
        return jdbcTemplate.queryForList("select * from a");
    }
    @CacheEvict(value = "jdbcCache")
    @RequestMapping("b")
    public void cleanA() {
        log.info("clean a");

    }
    @CachePut(value = "jdbcCache")
    @RequestMapping("c")
    public List<Map<String, Object>> resetA() {
        log.info("reset query");
        return new ArrayList<>();

    }

}
