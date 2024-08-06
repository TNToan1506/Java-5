package com.example.demo.request;

import com.example.demo.entities.LoaiXeMay;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class XeMayRequest {
    private Integer id;
    private String ma;
    private String ten;
    private LocalDate ngaySX;
    private BigDecimal giaNhap;
    private BigDecimal giaBan;
    private Integer soLuong;
    private LoaiXeMay loaiXeMay;
}
