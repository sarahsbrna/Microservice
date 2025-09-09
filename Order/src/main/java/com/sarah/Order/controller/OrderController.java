package com.sarah.Order.controller;

import com.sarah.Order.model.Order;
import com.sarah.Order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sarah.Order.vo.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // ambil semua order
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // ambil detail order + produk + pelanggan
@GetMapping("/produk/{id}")
public ResponseTemplate getOrderWithProdukById(@PathVariable Long id) {
    return orderService.getOrderWithProdukById(id);
}


    // tambah order baru
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrUpdateOrder(order);
    }

    // hapus order
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
    }
}
