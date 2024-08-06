package com.example.demo.repository;

import com.example.demo.entities.BaiHat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface BaiHatReository extends JpaRepository<BaiHat,Integer> {
    @Query("SELECT bh FROM BaiHat bh where bh.caSi.tenCaSi like %:tenCaSi% AND bh.gia>:min and bh.gia<:max")
    List<BaiHat> findBaiHatByCaSiAndGia(@Param("tenCaSi") String tenCaSi, @Param("min") double min, @Param("max") double max);
}
