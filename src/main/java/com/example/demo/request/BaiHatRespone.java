package com.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaiHatRespone {
    private Integer id;
    private String tenBaiHat;
    private String tenTacGia;
    private int thoiLuong;
    private LocalDate ngaySanXuat;
    private double gia;
    private String tenCaSi;
    private String queQuan;
    private LocalDate ngayRaMat;
}
