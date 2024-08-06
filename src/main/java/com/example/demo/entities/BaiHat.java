package com.example.demo.entities;

import com.example.demo.request.BaiHatRespone;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "baihat")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaiHat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tenbaihat")
    private String tenBaiHat;
    @Column(name = "tentacgia")
    private String tenTacGia;
    @Column(name = "thoiluong")
    private int thoiLuong;
    @Column(name = "ngaysanxuat")
    private LocalDate ngaySanXuat;
    @Column(name = "gia")
    private double gia;
    @ManyToOne()
    @JoinColumn(name = "casiid")
    private CaSi caSi;
    @Column(name = "ngayramat")
    private LocalDate ngayRaMat;
    public BaiHatRespone toRespone(){
        return  new BaiHatRespone(
                id,
                tenBaiHat,
                tenTacGia,
                thoiLuong,
                ngaySanXuat,
                gia,
                caSi.getTenCaSi(),
                caSi.getQueQuan(),
                ngayRaMat);
    }
}
