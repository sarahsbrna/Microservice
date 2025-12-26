// src/main/java/com/sarah/order/order/event/OrderCreatedEvent.java
package com.sarah.order.order.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {
    private Long orderId;
    private Long produkId;
    private Long pelangganId;
    private int jumlah;
    private String tanggal;
    private double total;
    // Data tambahan yang diambil dari service lain
    private String namaProduk;
    private double hargaProduk;
    private String namaPelanggan;
    private String emailPelanggan;
}