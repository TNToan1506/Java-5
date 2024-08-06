package com.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class XeMayRespone {
    private Integer id;
    private String ma;
    private String ten;
    private LocalDate ngaySX;
    private BigDecimal giaBan;
    private Integer soLuong;
    private String loaiXeMay;
}
