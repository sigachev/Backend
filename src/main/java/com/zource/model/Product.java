package com.zource.model;

import com.fasterxml.jackson.annotation.*;
import com.zource.model.jsonViews.ProductView;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ProductView.Short.class)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull
    @JsonView(ProductView.Short.class)
    private String name;

    @Column(name = "sku")
    @JsonView(ProductView.Short.class)
    private String sku;

    @Column(name = "description")
    @JsonView(ProductView.Short.class)
    private String description;

    @Column(name = "price")
    @JsonView(ProductView.Short.class)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name="priceFor")
    @JsonView(ProductView.Short.class)
    private UnitOfMeasure priceFor;

    @Enumerated(EnumType.STRING)
    @Column(name="soldBy")
    @JsonView(ProductView.Short.class)
    private UnitOfMeasure soldBy;

    @Column(name="uomRatio")
    @JsonView(ProductView.Short.class)
    private double uomRatio;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false, updatable=false)
    @JsonView(ProductView.Short.class)
    private Date create_date;

    @Column(name = "enabled")
    @JsonView(ProductView.Short.class)
    private boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    /*@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)*/
    @JsonProperty("brand")
    @JsonView(ProductView.Short.class)
    private Brand brand;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_category",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    @JsonIgnore
    private Set<Category> categories = new HashSet<>();


    @OneToMany(mappedBy="product", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ProductImage> productImages = new HashSet<>();



    public Product() {
    }



}
