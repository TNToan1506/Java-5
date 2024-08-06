package com.example.demo.repository;

import com.example.demo.entities.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SanPhamRepository extends JpaRepository<SanPham,Integer> {
    @Query("SELECT sp FROM SanPham sp WHERE sp.ma_san_pham LIKE %:maSanPham%")
    Optional<SanPham> findByMaSanPham(String maSanPham);
}
