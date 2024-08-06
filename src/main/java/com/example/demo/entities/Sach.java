package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "sach")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id") // Column name in the database
    private Integer id;

    @Column(name = "Ma") // Column name in the database
    private String ma;

    @Column(name = "Ten") // Column name in the database
    private String ten;

    @Column(name = "ngayxuatban") // Column name in the database
    private LocalDate ngayXuatBan;

    @Column(name = "sotrang") // Column name in the database
    private int soTrang;

    @Column(name = "dongia") // Column name in the database
    private BigDecimal donGia; // Don't forget to import java.math.BigDecimal


    @ManyToOne()
    @JoinColumn(name = "IdNXB")
    private NXB nxb;
}
