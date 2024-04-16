package com.example.GameStore.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    private double totalAmount;
    @Column(length = 20)
    private String orderStatus;

}
