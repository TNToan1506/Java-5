package com.example.demo.entities;

import com.example.demo.request.XeMayRespone;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "xemay")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class XeMay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ma")
    private String ma;
    @Column(name = "ten")
    private String ten;
    @Column(name = "ngaysx")
    private LocalDate ngaySX;
    @Column(name = "mota")
    private String moTa;
    @Column(name = "gianhap")
    private BigDecimal giaNhap;
    @Column(name = "giaban")
    private BigDecimal giaBan;
    @Column(name = "soluong")
    private Integer soLuong;
    @Column(name = "website")
    private String website;
    @ManyToOne
    @JoinColumn(name = "idlxm")
    private LoaiXeMay loaiXeMay;
    @Column(name = "trangthai")
    private Integer trangThai;
    public XeMayRespone toReposne(){
        return new XeMayRespone(id, ma, ten, ngaySX, giaBan, soLuong, loaiXeMay.getTen());
    }
}
