package com.example.springexample.controller;

import com.example.springexample.pojo.dto.E;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@ShellComponent
@Slf4j
public class ShellController {
    private final EntityManager entityManager;
    private final ObjectMapper objectMapper;

    public ShellController(EntityManager entityManager, ObjectMapper objectMapper) {
        this.entityManager = entityManager;
        this.objectMapper = objectMapper;
    }

    @ShellMethod("test shell")
    public String testShell(@ShellOption String text) {
        return text.toUpperCase();
    }

    @ShellMethod("data")
    @Transactional(rollbackFor = Exception.class)
    public void getDate()  {
        String sql = "delete from bpbanktranscationdetails where version > '2020-07-28 19:57:00' and version < '2020-07-28 19:59:00'";

            Query query = entityManager.createNativeQuery(sql);
            query.executeUpdate();

    }
    @ShellMethod("dto")
    public void getDto() {

    }
}
