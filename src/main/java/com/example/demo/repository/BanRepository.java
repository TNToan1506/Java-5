package com.example.demo.repository;

import com.example.demo.entities.Ban;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BanRepository extends JpaRepository<Ban,Integer> {
    @Query("Select b FROM Ban b where b.ma=:ma")
    Ban findByMa(String ma);
    @Query("Select b FROM Ban b where b.ma=:ma and b.id<>:id")
    Ban findByMaAndId(String ma,Integer id);
}
