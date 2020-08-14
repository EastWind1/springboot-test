package com.example.springexample.repository;

import com.example.springexample.pojo.entity.A;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ARepository extends JpaRepository<A, Long> {
    @Query(value = "select id, amount, version from a where id like %?1%", nativeQuery = true)
    List<A> findByIdLike(Integer id);
}
