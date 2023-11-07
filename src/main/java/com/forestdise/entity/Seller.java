package com.forestdise.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name ="SELLER")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seller_name")
    private String sellerName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "seller")
    @JsonManagedReference(value = "address_seller")
    private Set<Address> address;

    @OneToMany(mappedBy = "seller")
    @JsonManagedReference(value = "store_seller")
    private List<Store> storeList;

    @OneToMany(mappedBy = "seller")
    @JsonManagedReference(value = "comment_seller")
    private List<Comment> storeComments;
}
