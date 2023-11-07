package com.forestdise.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imgPath;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    @JsonBackReference(value = "variant_image")
    private Variant variant;

    @ManyToOne
    @JoinColumn(name="store_category_id")
    @JsonBackReference(value = "storeCategory_image")
    private StoreCategory storeCategory;



}
