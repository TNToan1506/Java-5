package com.example.demo.controller;

import com.example.demo.entities.BaiHat;
import com.example.demo.repository.BaiHatReository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("bai-hat")
public class BaiHatController {
    @Autowired
    BaiHatReository baiHatReository;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(baiHatReository.findAll().stream().map(BaiHat::toRespone));
    }

    @GetMapping("/phan-trang")
    public ResponseEntity<?> phanTrang(@RequestParam(name = "page", defaultValue = "0") Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 5);
        return ResponseEntity.ok(baiHatReository.findAll(pageRequest).stream().map(BaiHat::toRespone));
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody BaiHat baiHat) {
        baiHatReository.save(baiHat);
        return ResponseEntity.ok("Add done!");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam(name = "id") Integer id) {
        baiHatReository.deleteById(id);
        return ResponseEntity.ok("Delete done!");

    }

    @GetMapping("/search")
    public List<BaiHat> search(@RequestParam(name = "tenCaSi") String tenCaSi,
                               @RequestParam(name = "min") double min,
                               @RequestParam(name = "max") double max) {
        System.out.println(tenCaSi);
        System.out.println(min);
        System.out.println(max);
        return baiHatReository.findBaiHatByCaSiAndGia(tenCaSi, min, max);
    }

    @GetMapping("/stream")
    public ResponseEntity<?> stream() {
        return ResponseEntity.ok(baiHatReository.findAll().stream().max(Comparator.comparingDouble(BaiHat::getGia)).stream().max(Comparator.comparingInt(BaiHat::getThoiLuong)));
    }

    @GetMapping("/api")
    public ResponseEntity<?> api() {
        return ResponseEntity.ok(baiHatReository.findAll().stream().collect(Collectors.groupingBy(BaiHat::getCaSi,Collectors.summingInt(BaiHat::getThoiLuong))));
    }
}