package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "ban")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ban {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ma")
    private String ma;
    @Column(name = "ten")
    private String ten;
    @Column(name = "ngaysinh")
    private LocalDate ngaySinh;
    @Column(name = "sothich")
    private String soThich;
    @Column(name = "gioitinh")
    private Integer gioiTinh;
    @ManyToOne
    @JoinColumn(name = "idmqh")
    private MoiQuanHe moiQuanHe;
}
