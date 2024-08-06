package com.example.demo.controller;

import com.example.demo.entities.Ban;
import com.example.demo.repository.BanRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("ban")
public class BanController {
    @Autowired
    BanRepository banRepository;
    @GetMapping()
    public ResponseEntity<?>getAll(){
        return ResponseEntity.ok(banRepository.findAll());
    }
    @GetMapping("/phanTrang")
    public ResponseEntity<?>phanTrang(@RequestParam(defaultValue = "0") Integer page){
        PageRequest pageRequest=PageRequest.of(page, 5);
        return ResponseEntity.ok(banRepository.findAll(pageRequest));
    }
    @PostMapping("/add")
    public ResponseEntity<?>add(@RequestBody Ban ban){
        if (ban.getTen()==null||ban.getTen().trim().isEmpty()||ban.getTen().matches(".*\\d.*")){
            return ResponseEntity.badRequest().body("Không được bỏ trống tên và không được chứa số!");
        }
        if (ban.getGioiTinh()==null||String.valueOf(ban.getGioiTinh()).trim().isEmpty()||ban.getTen().matches("\\d+")){
            return ResponseEntity.badRequest().body("Không được bỏ trống giới tính và chỉ được chứa số!");
        }
        if (ban.getMoiQuanHe()==null||String.valueOf(ban.getMoiQuanHe()).trim().isEmpty()||String.valueOf(ban.getMoiQuanHe()).matches("\\d+")){
            return ResponseEntity.badRequest().body("Không được bỏ trống mối quan hệ và chỉ được chứa số!");
        }
        if (ban.getSoThich()==null||String.valueOf(ban.getSoThich()).trim().isEmpty()){
            return ResponseEntity.badRequest().body("Không được bỏ trống sở thích!");
        }
        if (ban.getNgaySinh()==null||String.valueOf(ban.getNgaySinh()).trim().isEmpty()){
            return ResponseEntity.badRequest().body("Không được bỏ trống ngày sinh! ");
        }
        if (null != banRepository.findByMa(ban.getMa())){
            return ResponseEntity.badRequest().body("Mã đã tồn tại! ");
        }
        if (ban.getMa()==null||String.valueOf(ban.getMa()).trim().isEmpty()){
            return ResponseEntity.badRequest().body("Không được bỏ trống Mã! ");
        }
        banRepository.save(ban);
        return ResponseEntity.ok("Thêm thành công!");
    }
    @PutMapping("/update")
    public ResponseEntity<?>update(@RequestParam("id") Integer id,@RequestBody Ban ban){
        if (ban.getTen()==null||ban.getTen().trim().isEmpty()||ban.getTen().matches(".*\\d.*")){
            return ResponseEntity.badRequest().body("Không được bỏ trống tên và không được chứa số!");
        }
        if (ban.getGioiTinh()==null||String.valueOf(ban.getGioiTinh()).trim().isEmpty()||ban.getTen().matches("\\d+")){
            return ResponseEntity.badRequest().body("Không được bỏ trống giới tính và chỉ được chứa số!");
        }
        if (ban.getMoiQuanHe()==null||String.valueOf(ban.getMoiQuanHe()).trim().isEmpty()||String.valueOf(ban.getMoiQuanHe()).matches("\\d+")){
            return ResponseEntity.badRequest().body("Không được bỏ trống mối quan hệ và chỉ được chứa số!");
        }
        if (ban.getSoThich()==null||String.valueOf(ban.getSoThich()).trim().isEmpty()){
            return ResponseEntity.badRequest().body("Không được bỏ trống sở thích!");
        }
        if (ban.getNgaySinh()==null||String.valueOf(ban.getNgaySinh()).trim().isEmpty()){
            return ResponseEntity.badRequest().body("Không được bỏ trống ngày sinh! ");
        }
        if (null != banRepository.findByMaAndId(ban.getMa(),id)){
            return ResponseEntity.badRequest().body("Mã đã tồn tại! ");
        }
        if (ban.getMa()==null||String.valueOf(ban.getMa()).trim().isEmpty()){
            return ResponseEntity.badRequest().body("Không được bỏ trống Mã! ");
        }
        Ban ban1=banRepository.getById(id);
        ban1.setMa(ban.getMa());
        ban1.setGioiTinh(ban.getGioiTinh());
        ban1.setMoiQuanHe(ban.getMoiQuanHe());
        ban1.setNgaySinh(ban.getNgaySinh());
        ban1.setTen(ban.getTen());
        ban1.setSoThich(ban.getSoThich());
        banRepository.save(ban1);
        return ResponseEntity.ok("Sửa thành công!");
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?>delete(@RequestParam("id")Integer id){
        banRepository.deleteById(id);
        return ResponseEntity.ok("Xóa thành công!");
    }
    @GetMapping("/filterSoThich")
    public ResponseEntity<?>filterSoThich(){
        List<Ban> listBanFilter= banRepository.findAll().
                stream().
                filter(ban -> ban.getSoThich().equals("Lăn")).collect(Collectors.toList());
        return ResponseEntity.ok(listBanFilter);
    }
    @GetMapping("/filterMoiQuanHe")
    public ResponseEntity<?>filterMQH(){
        List<Ban> listBanFilter= banRepository.findAll().
                stream().
                filter(ban -> ban.getMoiQuanHe().getTen().equals("Có Người Yêu")).collect(Collectors.toList());
        return ResponseEntity.ok(listBanFilter);
    }
}
