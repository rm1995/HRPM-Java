package com.bitproject.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="unit_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Unittype {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;


    @Column(name = "name")
    @Basic(optional = false)
    private String name;
}
