package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "casi")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CaSi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tencasi")
    String tenCaSi;
    @Column(name = "quequan")
    private String queQuan;
    @Column(name = "tuoi")
    private int tuoi;
    @Column(name = "congty")
    private String congTy;
    @Column(name = "sdt")
    private String sdt;
    @Column(name = "gioitinh")
    private int gioiTinh;
}
