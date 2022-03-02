package com.example.diningReview.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="USERS")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_name")
    private String userName;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="zipcode")
    private String zipcode;

    @Column(name="has_peanut_allergy")
    private Boolean hasPeanutAllergy;

    @Column(name="has_egg_allergy")
    private Boolean hasEggAllergy;

    @Column(name="has_dairy_allergy")
    private Boolean hasDairyAllergy;

}
