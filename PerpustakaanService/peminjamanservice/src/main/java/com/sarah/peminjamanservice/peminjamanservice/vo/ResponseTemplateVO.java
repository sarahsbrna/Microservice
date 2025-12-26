package com.sarah.peminjamanservice.peminjamanservice.vo;

import com.sarah.peminjamanservice.peminjamanservice.model.Peminjaman;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplateVO {
    private Peminjaman peminjaman;
    private Anggota anggota;
    private Buku buku;
}