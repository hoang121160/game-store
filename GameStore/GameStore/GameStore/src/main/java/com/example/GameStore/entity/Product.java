package com.example.GameStore.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private double price;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

}
