package com.example.diningReview.repositories;

import com.example.diningReview.model.AdminReviewAction;
import com.example.diningReview.model.DiningReview;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
//import java.util.Optional;

public interface DiningReviewRepository extends CrudRepository<DiningReview, Long>{
    //Optional<DiningReview> findByUserName(String userName);
    List<DiningReview> findByAdminReviewAction(AdminReviewAction PENDING);
    List<DiningReview> findByAdminReviewActionAndRestaurantId(AdminReviewAction status, Long restaurantId);
}
