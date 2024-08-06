package com.example.demo.controller;

import com.example.demo.entities.XeMay;
import com.example.demo.repository.XeMayRepository;
import com.example.demo.request.NhanVienRequest;
import com.example.demo.request.XeMayRequest;
import com.example.demo.request.XeMayRespone;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;

@RestController
@RequestMapping("xe-may")
public class XeMayController {
    @Autowired
    XeMayRepository xeMayRepository;
    @GetMapping()
    public ResponseEntity<?>findAll(){
        return  ResponseEntity.ok(xeMayRepository.findAll().stream().map(XeMay::toReposne));
    }
    @GetMapping("/phan-trang")
    public ResponseEntity<?>phanTrang(@RequestParam(name = "page",defaultValue = "0")Integer page){
        PageRequest pageRequest=PageRequest.of(page, 5);
        return ResponseEntity.ok(xeMayRepository.findAll(pageRequest).stream().map(XeMay::toReposne));
    }
    @PostMapping("/add")
    public ResponseEntity<?>add(@RequestBody XeMayRequest xeMayRequest){
        XeMay xeMay=new XeMay();
        xeMay.setMa(xeMayRequest.getMa());
        xeMay.setTen(xeMayRequest.getTen());
        xeMay.setGiaNhap(xeMayRequest.getGiaNhap());
        xeMay.setGiaBan(xeMayRequest.getGiaBan());
        xeMay.setNgaySX(xeMayRequest.getNgaySX());
        xeMay.setLoaiXeMay(xeMayRequest.getLoaiXeMay());
        xeMay.setSoLuong(xeMayRequest.getSoLuong());
        xeMayRepository.save(xeMay);
//        BeanUtils.copyProperties(xeMay, xeMayRequest);
        return ResponseEntity.ok("Thêm thành công!");
    }
    @PutMapping("/update")
    public ResponseEntity<?>update(@RequestBody XeMayRequest xeMayRequest,@RequestParam(name = "id")Integer id){
        if (!xeMayRepository.existsById(id)){
            return ResponseEntity.badRequest().body("Sửa thất bại!Không tìm thấy xe máy có id: "+id);
        }
        else{
            XeMay xeMayU=xeMayRepository.getById(id);
            xeMayU.setMa(xeMayRequest.getMa());
            xeMayU.setTen(xeMayRequest.getTen());
            xeMayU.setGiaNhap(xeMayRequest.getGiaNhap());
            xeMayU.setGiaBan(xeMayRequest.getGiaBan());
            xeMayU.setNgaySX(xeMayRequest.getNgaySX());
            xeMayU.setLoaiXeMay(xeMayRequest.getLoaiXeMay());
            xeMayU.setSoLuong(xeMayRequest.getSoLuong());
            xeMayRepository.save(xeMayU);
            return ResponseEntity.ok("Sửa thành công!");
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?>delete(@RequestParam(name = "id")Integer id){
        if(!xeMayRepository.findById(id).isPresent()){
            return ResponseEntity.badRequest().body("Xoan thất bại!Không tìm thấy xe máy có id: "+id);
        }
        xeMayRepository.deleteById(id);
        return ResponseEntity.ok("Xoa thanh cong!");
    }
    @GetMapping("/filterXeGa")
    public ResponseEntity<?>xeGa(){
        return  ResponseEntity.ok(xeMayRepository.findAll().stream().map(XeMay::toReposne)
                .filter(
                        xeMayRespone ->
                                xeMayRespone.getLoaiXeMay().equalsIgnoreCase("Xe ga")));
    }
    @GetMapping("/filterGiaCa")
    public ResponseEntity<?>giaCa(){
        return  ResponseEntity.ok(xeMayRepository.findAll().stream().map(XeMay::toReposne).sorted(Comparator.comparing(XeMayRespone::getGiaBan).reversed()));
    }
}
