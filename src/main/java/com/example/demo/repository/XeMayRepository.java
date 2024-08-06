package com.example.demo.repository;

import com.example.demo.entities.XeMay;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

public interface XeMayRepository extends JpaRepository<XeMay,Integer> {
    @Query("SELECT xm FROM XeMay xm where xm.ma=:ma")
    XeMay findByMa(@PathParam("ma")String ma);
    @Query("SELECT xm FROM XeMay xm where xm.ma=:ma and xm.id<>:id")
    XeMay findByIdAndMa(@PathParam("ma")String ma,@PathParam("ma")Integer id);

}
