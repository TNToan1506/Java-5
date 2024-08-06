package com.example.demo.controller;

import com.example.demo.entities.SanPham;
import com.example.demo.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("san-pham")
public class SanPhamController {
    @Autowired
    SanPhamRepository sanPhamRepository;
    @GetMapping()
    public ResponseEntity<?>getAll(){
        return ResponseEntity.ok(sanPhamRepository.findAll());
    }
    @PostMapping("/add")
    public ResponseEntity<?>add(@RequestBody SanPham sanPham){
        try {
            Optional<SanPham> existingSanPham = sanPhamRepository.findByMaSanPham(sanPham.getMa_san_pham());
            if (existingSanPham.isPresent()) {
                return ResponseEntity.badRequest().body("Mã sản phẩm đã tồn tại!");
            }

            if (sanPham.getTen_san_pham() == null || sanPham.getTen_san_pham().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Tên sản phẩm không được bỏ trống!");
            }

            if (sanPham.getMa_san_pham() == null || sanPham.getMa_san_pham().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Mã sản phẩm không được bỏ trống!");
            }
            if (!sanPham.getMa_san_pham().startsWith("SP")) {
                return ResponseEntity.badRequest().body("Mã sản phẩm phải bắt đầu bằng 'SP'");
            }

            sanPhamRepository.save(sanPham);
            return ResponseEntity.ok("Thêm sản phẩm thành công!");
        } catch (Exception e) {
            // Log lỗi và trả về lỗi 500
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi thêm sản phẩm!");
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody SanPham updatedSanPham) {
        if (sanPhamRepository.findById(id).isPresent()) {
            SanPham sanPham = sanPhamRepository.findById(id).get();
            sanPham.setMa_san_pham(updatedSanPham.getMa_san_pham());
            sanPham.setTen_san_pham(updatedSanPham.getTen_san_pham());
            sanPham.setTrang_thai(updatedSanPham.getTrang_thai());
            sanPham.setId_danh_muc(updatedSanPham.getId_danh_muc());
            return ResponseEntity.ok(sanPhamRepository.save(sanPham));
        } else {
            return ResponseEntity.badRequest().body("Không tìm thấy sản phẩm có id " + id);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            sanPhamRepository.deleteById(id);
            return ResponseEntity.ok("Xóa sản phẩm thành công");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Không thể xóa sản phẩm có id " + id);
        }
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getDetail(@PathVariable Integer id) {
        Optional<SanPham> optionalSanPham = sanPhamRepository.findById(id);
        if (optionalSanPham.isPresent()) {
            return ResponseEntity.ok(optionalSanPham.get());
        } else {
            return ResponseEntity.badRequest().body("Không tìm thấy sản phẩm có id " + id);
        }
    }
    @GetMapping("/filter-and-sort")
    public ResponseEntity<List<SanPham>> filterAndSort(
            @RequestParam(required = false) String maSanPham,
            @RequestParam(required = false) String tenSanPham,
            @RequestParam(required = false) Integer idDanhMuc,
            @RequestParam(required = false, defaultValue = "asc") String sortOrder) {

        List<SanPham> filteredAndSortedProducts = sanPhamRepository.findAll().stream()
                .filter(sanPham -> (maSanPham == null || sanPham.getMa_san_pham().contains(maSanPham)))
                .filter(sanPham -> (tenSanPham == null || sanPham.getTen_san_pham().contains(tenSanPham)))
                .filter(sanPham -> (idDanhMuc == null || sanPham.getId_danh_muc()==idDanhMuc))
                .sorted((sp1, sp2) -> sortOrder.equalsIgnoreCase("asc") ?
                        sp1.getTen_san_pham().compareTo(sp2.getTen_san_pham()) :
                        sp2.getTen_san_pham().compareTo(sp1.getTen_san_pham()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(filteredAndSortedProducts);
    }
}
