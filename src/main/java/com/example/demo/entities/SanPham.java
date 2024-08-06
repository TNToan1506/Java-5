package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "san_pham")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ma_san_pham")
    private String ma_san_pham;
    @Column(name = "ten_san_pham")
    private String ten_san_pham;
    @Column(name = "trang_thai")
    private String trang_thai;
    @Column(name = "ngay_tao")
    private LocalDateTime ngay_tao;
    @Column(name = "ngay_sua")
    private LocalDateTime ngay_sua;
    @Column(name = "id_danh_muc")
    private Integer id_danh_muc;
}
