package com.example.demo.repository;

import com.example.demo.entities.Sach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SachRepository extends JpaRepository<Sach,Integer> {
    @Query("SELECT s FROM Sach s WHERE s.ma =:ma")
    Optional<Sach> findByMa(String ma);
}
