package com.example.demo.controller;

import com.example.demo.entities.NhanVien;
import com.example.demo.repository.NhanVienRepository;
import com.example.demo.request.NhanVienRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("nhan-vien")
public class NhanVienController {
    @Autowired
    NhanVienRepository nhanVienRepository;
    @GetMapping()
    public ResponseEntity<?>findAll(){
        return ResponseEntity.ok(nhanVienRepository.findAll().stream().map(NhanVien::toRespone));
    }
    @GetMapping("/phan-trang")
    public ResponseEntity<?>phanTrang(@RequestParam(defaultValue = "0")Integer page){
        PageRequest pageRequest=PageRequest.of(page, 5);
        return ResponseEntity.ok(nhanVienRepository.findAll(pageRequest));
    }
    @PostMapping("/add")
    public ResponseEntity<?>add(@RequestBody NhanVienRequest nhanVien){
        if (nhanVien.getMa()==null||String.valueOf(nhanVien.getMa()).trim().isEmpty()||nhanVien.getMa().length()>=256){
            return ResponseEntity.badRequest().body("Mã không được bỏ trống và giới hạn dưới 255 ký tự!");
        }
        if (nhanVien.getTen()==null||String.valueOf(nhanVien.getTen()).trim().isEmpty()||nhanVien.getTen().length()>=50){
            return ResponseEntity.badRequest().body("Tên không được bỏ trống và giới hạn dưới 50 ký tự!");
        }
//        if (nhanVien.getTenDem()==null||String.valueOf(nhanVien.getTenDem()).trim().isEmpty()||nhanVien.getTenDem().length()>=50){
//            return ResponseEntity.badRequest().body("Tên đệm không được bỏ trống và giới hạn dưới 50 ký tự!");
//        }
//        if (nhanVien.getHo()==null||String.valueOf(nhanVien.getHo()).trim().isEmpty()||nhanVien.getHo().length()>=50){
//            return ResponseEntity.badRequest().body("Họ không được bỏ trống và giới hạn dưới 50 ký tự!");
//        }
        if (nhanVien.getDiaChi()==null||String.valueOf(nhanVien.getDiaChi()).trim().isEmpty()||nhanVien.getDiaChi().length()>=50){
            return ResponseEntity.badRequest().body("Địa chỉ không được bỏ trống và giới hạn dưới 255 ký tự!");
        }
        if (nhanVienRepository.findByMa(nhanVien.getMa())!=null){
            return ResponseEntity.badRequest().body("Mã đã tồn tại vui lòng nhập mã mới!");
        }
        NhanVien nv=new NhanVien();
        nv.setMa(nhanVien.getMa());
        nv.setTen(nhanVien.getTen());
        nv.setTenDem(nhanVien.getTenDem());
        nv.setHo(nhanVien.getHo());
        nv.setDiaChi(nhanVien.getDiaChi());
        nv.setGioiTinh(nhanVien.getGioiTinh());
        nv.setChucVu(nhanVien.getChucVu());
        nhanVienRepository.save(nv);
        return ResponseEntity.ok("Thêm thành công!");
    }
    @PutMapping("/update")
    public ResponseEntity<?>update(@RequestBody NhanVien nhanVien,@RequestParam("id")Integer id){
        if (nhanVien.getMa()==null||String.valueOf(nhanVien.getMa()).trim().isEmpty()||nhanVien.getMa().length()>=256){
            return ResponseEntity.badRequest().body("Mã không được bỏ trống và giới hạn dưới 255 ký tự!");
        }
        if (nhanVien.getTen()==null||String.valueOf(nhanVien.getTen()).trim().isEmpty()||nhanVien.getTen().length()>=50){
            return ResponseEntity.badRequest().body("Tên không được bỏ trống và giới hạn dưới 50 ký tự!");
        }
//        if (nhanVien.getTenDem()==null||String.valueOf(nhanVien.getTenDem()).trim().isEmpty()||nhanVien.getTenDem().length()>=50){
//            return ResponseEntity.badRequest().body("Tên đệm không được bỏ trống và giới hạn dưới 50 ký tự!");
//        }
//        if (nhanVien.getHo()==null||String.valueOf(nhanVien.getHo()).trim().isEmpty()||nhanVien.getHo().length()>=50){
//            return ResponseEntity.badRequest().body("Họ không được bỏ trống và giới hạn dưới 50 ký tự!");
//        }
        if (nhanVien.getDiaChi()==null||String.valueOf(nhanVien.getDiaChi()).trim().isEmpty()||nhanVien.getDiaChi().length()>=50){
            return ResponseEntity.badRequest().body("Địa chỉ không được bỏ trống và giới hạn dưới 255 ký tự!");
        }
        if (nhanVienRepository.findByMaAndId(nhanVien.getMa(),id)!=null){
            return ResponseEntity.badRequest().body("Mã đã tồn tại vui lòng nhập mã mới!");
        }
        NhanVien nhanVienU=nhanVienRepository.getById(id);
        if (nhanVienU==null){
            return ResponseEntity.badRequest().body("Không tìm thấy nhân viên có id: "+id);
        }
        BeanUtils.copyProperties(nhanVien, nhanVienU,"id");
        nhanVienRepository.save(nhanVienU);
        return ResponseEntity.ok("Cập nhật thành công!");
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?>delete(@RequestParam("id")Integer id){
        NhanVien nhanVienD=nhanVienRepository.getById(id);
        if (nhanVienD==null){
            return ResponseEntity.badRequest().body("Không tìm thấy nhân viên có id: "+id);
        }
        nhanVienRepository.deleteById(id);
        return ResponseEntity.ok("Xóa thành công!");
    }
    @GetMapping("/detail")
    public ResponseEntity<?>detail(@RequestParam("id")Integer id){
        if (!nhanVienRepository.findById(id).isPresent()){
            return ResponseEntity.badRequest().body("Không tìm thấy nhân viên có id: "+id);
        }
        return ResponseEntity.ok(nhanVienRepository.findById(id));
    }
    @GetMapping("/filterGiamDoc")
    public ResponseEntity<?>filterGiamDoc(){
        List<NhanVien>listNhanVienFilter=nhanVienRepository.findAll().stream().
                filter(nhanVien -> nhanVien.getChucVu().getTen().equalsIgnoreCase("Giám đốc")).collect(Collectors.toList());
        return ResponseEntity.ok(listNhanVienFilter);
    }

}
