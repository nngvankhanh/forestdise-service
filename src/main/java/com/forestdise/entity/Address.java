package com.forestdise.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String district;

    private String ward;

    private String city;

    private String street;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user_address")
    private User user;

    @ManyToOne
    @JoinColumn(name="seller_id")
    @JsonBackReference(value = "seller_address")
    private Seller seller;

    @OneToMany(mappedBy = "address")
    @JsonManagedReference(value = "address_shopOrder")
    private Set<ShopOrder> shopOrders;
}
