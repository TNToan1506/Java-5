package com.example.demo.request;

import com.example.demo.entities.ChucVu;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NhanVienRequest {
    private Integer id;
    private String ma;
    private String ten;
    private String tenDem;
    private String ho;
    private String gioiTinh;
    private LocalDate ngaySinh;
    private String diaChi;
    private String sdt;
    private String matKhau;
    private ChucVu chucVu;
    private int trangThai;
}
