package com.example.diningReview.repositories;

import com.example.diningReview.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{
    Optional<Restaurant> findByNameAndZipcode(String name, String zipcode);
    Iterable<Restaurant> findByZipcodeAndKeyword(String zipcode, String keyword);
}
