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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value = "user_address")
    private Set<Address> address;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @OneToMany(mappedBy = "user")
    private Set<ShopOrder> shopOrders;

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference(value = "user_review")
    private List<Review> reviewList;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value = "user_paymentMethod")
    private Set<PaymentMethod> paymentMethods;
}
