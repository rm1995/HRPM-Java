package com.bitproject.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
@Table(name="material")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Material {
    //
    @Id
    //
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //
     @Basic(optional = false)
    //
    @Column(name = "id")
    private Integer id;


    @Column(name = " material_code")
    @Basic(optional = false)
    //@Pattern(regexp = "", message = "Invalid Item Code")
    private String material_code;

    @Column(name = " material_name")
    @Basic(optional = false)
    private String material_name;

    @Column(name = "unit_size")
    @Basic(optional = false)
    private Integer unit_size;

    @Column(name = " material_photo")
    @Basic(optional = true)
    private byte[] material_photo;

    @Column(name = "re-order")
    @Basic(optional = true)
    private Integer reorder;

    @Column(name = " added-date")
    @Basic(optional = false)
    private LocalDate addeddate;

    @Column(name = " description")
    @Basic(optional = true)
    private String description;


    @JoinColumn(name="material_category_id",referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Materialcategory material_category;

    @JoinColumn(name="unit_type_id",referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Unittype unit_type;

    @JoinColumn(name="material_status_id",referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Material_status material_status;

    @JoinColumn(name="employee_id",referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Employee employee;


}
