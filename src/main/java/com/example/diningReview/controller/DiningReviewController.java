package com.example.diningReview.controller;

import com.example.diningReview.model.*;
import com.example.diningReview.repositories.DiningReviewRepository;
import com.example.diningReview.repositories.RestaurantRepository;
import com.example.diningReview.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class DiningReviewController {
    @Autowired
    private final DiningReviewRepository diningReviewRepository;
    @Autowired
    private final RestaurantRepository restaurantRepository;
    @Autowired
    private final UserRepository userRepository;

    public DiningReviewController(DiningReviewRepository diningReviewRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.diningReviewRepository = diningReviewRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/dining-review")
    public DiningReview createNewDiningReview(@RequestBody DiningReview diningReview){
        if(restaurantRepository.findById(diningReview.getRestaurantId()).isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This restaurant does not exist");
        }
        if(userRepository.findByUserName(diningReview.getUserName()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user does not exist");
        }

        return diningReviewRepository.save(diningReview);
    }

    @GetMapping("/dining-review")
    public Iterable<DiningReview> getAllDiningReviews() {
        return diningReviewRepository.findAll();
    }

    @GetMapping("/dining-review/pending")
    public Iterable<DiningReview> getPendingDiningReviews() {
        return diningReviewRepository.findByAdminReviewAction(AdminReviewAction.PENDING);
    }

    @GetMapping("/dining-review/accepted/{id}")
    public Iterable<DiningReview> getApprovedDiningReviewsByRestaurantId(@PathVariable("id") Long restaurantId) {
        return diningReviewRepository.findByAdminReviewActionAndRestaurantId(AdminReviewAction.APPROVED, restaurantId);
    }

    @PutMapping("/dining-review/{id}/approved")
    public DiningReview approveDiningReview(@PathVariable("id") Long id) {
        Optional<DiningReview> reviewOptional = this.diningReviewRepository.findById(id);
        if(reviewOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This review does not exist");
        }
        DiningReview reviewToApprove = reviewOptional.get();
        if(reviewToApprove.getAdminReviewAction() == AdminReviewAction.PENDING) {
            reviewToApprove.setAdminReviewAction(AdminReviewAction.APPROVED);
        }
        return diningReviewRepository.save(reviewToApprove);
    }

}
