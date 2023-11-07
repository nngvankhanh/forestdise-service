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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "optionvalue")
public class OptionValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @ManyToOne
    @JoinColumn(name = "option_id")
    @JsonBackReference(value = "optionTable_optionValue")
    private OptionTable optionTable;

    @ManyToMany(mappedBy = "optionValues")
    @JsonBackReference(value = "optionValue_variant")
    private List<Variant> variants ;
    public List<Variant> getVariants() {
        if (variants == null) {
            variants = new ArrayList<>();
        }
        return variants;
    }
}
