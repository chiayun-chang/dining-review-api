package com.example.diningReview.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="DINING_REVIEW")
@Getter
@Setter
@NoArgsConstructor
public class DiningReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_name")
    private String userName;

    @Column(name="restaurant_id")
    private Long restaurantId;

    @Column(name="peanut_allergy")
    private Integer peanutAllergy;

    @Column(name="egg_allergy")
    private Integer eggAllergy;

    @Column(name="dairy_allergy")
    private Integer dairyAllergy;

    @Column(name="commentary")
    private String commentary;

    @Column(name="admin_review_action")
    private AdminReviewAction adminReviewAction;
}
