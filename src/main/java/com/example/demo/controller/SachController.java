package com.example.demo.controller;

import com.example.demo.entities.Sach;
import com.example.demo.repository.SachRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("sach")

public class SachController {
    @Autowired
    SachRepository sachRepository;
    @GetMapping()
    @ResponseBody
    public ResponseEntity<?>getAll(){
        return ResponseEntity.ok(sachRepository.findAll());
    }
    @GetMapping("/phanTrang")
    public ResponseEntity<?>getListPage(@RequestParam(defaultValue = "0")Integer page){
        PageRequest pageRequest =PageRequest.of(page, 5);
        return ResponseEntity.ok(sachRepository.findAll(pageRequest));
    }
    @PostMapping("/add")
    public ResponseEntity<?>add(@RequestBody Sach sach){
        if (sach.getMa()==null||sach.getMa().trim().isEmpty()){
            return ResponseEntity.badRequest().body("Mã không được bỏ trống!");
        }
        if (sach.getDonGia()==null||String.valueOf(sach.getDonGia()).trim().isEmpty()){
            return ResponseEntity.badRequest().body("Đơn giá không được bỏ trống!");
        }
        if (sach.getNxb()==null||String.valueOf(sach.getNxb()).trim().isEmpty()){
            return ResponseEntity.badRequest().body("Nhà xuất bản không được bỏ trống!");
        }
        if (sach.getTen()==null||String.valueOf(sach.getTen()).trim().isEmpty()){
            return ResponseEntity.badRequest().body("Tên không được bỏ trống!");
        }
        if (Integer.valueOf(sach.getSoTrang())==null||sach.getSoTrang()<0||String.valueOf(sach.getSoTrang()).trim().isEmpty()){
            return ResponseEntity.badRequest().body("Số trang không được bỏ trống hoặc nhỏ hơn 0!");
        }
        if (sachRepository.findByMa(sach.getMa()).isPresent()){
            return ResponseEntity.badRequest().body("Mã đã tồn tại!");
        }


        sach.setNgayXuatBan(LocalDate.now());
        sachRepository.save(sach);
        return ResponseEntity.ok("Thành công!");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?>update(@PathVariable Integer id,@RequestBody Sach sach){
        Optional<Sach>sachFindById=sachRepository.findById(id);
        if (sachFindById.isPresent()){
            Sach sachUpdate=sachFindById.get();
            BeanUtils.copyProperties(sach, sachUpdate,"id");
            sachRepository.save(sachUpdate);
        }
        else{
            return ResponseEntity.badRequest().body("Không tìm thấy sách có id là: "+id);
        }
        return ResponseEntity.ok("Sửa thành công!");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>delete(@PathVariable Integer id){
        if (sachRepository.findById(id).isPresent()){
            sachRepository.deleteById(id);
        }
        else{
            return ResponseEntity.badRequest().body("Xảy ra lỗi rồi!");
        }
        return ResponseEntity.ok("Xóa thành công!");
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<?>detail(@PathVariable Integer id){
        if (sachRepository.findById(id).isPresent()){
            return ResponseEntity.ok(sachRepository.findById(id));
        }
        else{
            return ResponseEntity.badRequest().body("Không tìm thấy sách có id là: "+id);
        }
    }
    @GetMapping("/filterNXB")
    public ResponseEntity<?>filterNXB(){
        List<Sach>listSachFilter=sachRepository.findAll().stream().filter(sach -> sach.getNxb().getTen().equals("Nguyễn Văn Anh")).collect(Collectors.toList());
        return ResponseEntity.ok(listSachFilter);
    }
    @GetMapping("/sortSachDesc")
    public ResponseEntity<?>sortSachDesc(){
        List<Sach>listSachDesc=sachRepository.findAll().stream().sorted(Comparator.comparing(Sach::getDonGia).reversed()).collect(Collectors.toList());
        return ResponseEntity.ok(listSachDesc);
    }
    @GetMapping("/filterSach")
    public ResponseEntity<?>filterSach(){
        List<Sach>listSachFilter=sachRepository.findAll().stream().filter(sach -> sach.getTen().toLowerCase().contains("a"))
                .filter(sach -> {
                    int years = LocalDate.now().getYear() - sach.getNgayXuatBan().getYear();
                    return years >= 10 && years <= 20;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(listSachFilter);

    }
}
