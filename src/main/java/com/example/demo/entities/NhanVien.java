package com.example.demo.entities;

import com.example.demo.request.NhanVienRespone;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "nhan_vien")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ma")
    private String ma;
    @Column(name = "ten")
    private String ten;
    @Column(name = "ten_dem")
    private String tenDem;
    @Column(name = "ho")
    private String ho;
    @Column(name = "gioi_tinh")
    private String gioiTinh;
    @Column(name = "dia_chi")
    private String diaChi;
    @Column(name = "sdt")
    private String sdt;
    @Column(name = "mat_khau")
    private String matKhau;
    @ManyToOne
    @JoinColumn(name = "id_cv")
    private ChucVu chucVu;
    @Column(name = "trang_thai")
    private int trangThai;

    public NhanVienRespone toRespone() {
        return new NhanVienRespone(
                this.id,
                this.ten,
                ma,
                this.diaChi,
                this.gioiTinh,
                this.chucVu.getTen()
        );
    }
}
