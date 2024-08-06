package com.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NhanVienRespone {
    private Integer id;
    private String ten;
    private String ma;
    private String diaChi;
    private String gioiTinh;
    private String tenChucVu;
}
