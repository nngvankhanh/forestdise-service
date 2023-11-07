package com.forestdise.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "variant")
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String skuCode;
    private int stockQuantity;
    private double weight;
    private double price;
    private double salePrice;
    private String img;
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference(value = "product_variant")
    private Product product;

    @OneToMany(mappedBy = "variant")
    @JsonManagedReference(value = "variant_image")
    private List<Image> images;

    @OneToMany(mappedBy = "variant")
    @JsonManagedReference(value = "variant_video")
    private List<Video> videos;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference(value = "optionValue_variant")
    @JoinTable(name = "variant_optionvalue", joinColumns = @JoinColumn(name = "variant_id"),
            inverseJoinColumns = @JoinColumn(name = "optionvalue_id"))
    private List<OptionValue> optionValues;

    @OneToOne(mappedBy = "variant")
    private CartLine cartLine;

    @OneToOne(mappedBy = "variant")
    private SaveForLater saveForLater;

    @OneToMany(mappedBy = "variant")
    @JsonManagedReference(value = "variant_review")
    private List<Review> reviews;

    @OneToMany(mappedBy = "variant")
    @JsonManagedReference(value = "variant_shopOrder")
    private Set<ShopOrder> shopOrders;
}

