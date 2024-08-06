package com.example.demo.repository;

import com.example.demo.entities.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NhanVienRepository extends JpaRepository<NhanVien,Integer> {
    @Query("SELECT nv FROM NhanVien nv WHERE nv.ma=:ma")
    NhanVien findByMa(String ma);
    @Query("SELECT nv FROM NhanVien nv WHERE nv.ma=:ma AND  nv.id<>:id")
    NhanVien findByMaAndId(@Param("ma") String ma,@Param("id") Integer id);
}
