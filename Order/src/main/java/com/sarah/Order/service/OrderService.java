package com.sarah.Order.service;

import com.sarah.Order.model.Order;
import com.sarah.Order.repository.OrderRepository;
import com.sarah.Order.vo.ResponseTemplate;
import com.sarah.Order.vo.Pelanggan;
import com.sarah.Order.vo.Produk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public Order createOrUpdateOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

   
    public ResponseTemplate getOrderWithProdukById(Long id) {
        Optional<Order> optionalOrder = getOrderById(id);
        if (optionalOrder.isEmpty()) {
            return null; // atau lempar exception NotFound
        }

        Order order = optionalOrder.get();

        // Ambil data produk via REST API
        Produk produk = restTemplate.getForObject(
                "http://localhost:8081/api/produk/" + order.getProdukId(),
                Produk.class
        );

        // Ambil data pelanggan via REST API
        Pelanggan pelanggan = restTemplate.getForObject(
                "http://localhost:8082/api/pelanggan/" + order.getPelangganId(),
                Pelanggan.class
        );

        // Buat response template
        ResponseTemplate vo = new ResponseTemplate();
        vo.setOrder(order);
        vo.setProduk(produk);
        vo.setPelanggan(pelanggan);

        return vo;
    }
}