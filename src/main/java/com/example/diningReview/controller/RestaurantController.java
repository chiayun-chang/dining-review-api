package com.example.diningReview.controller;

import com.example.diningReview.model.*;
import com.example.diningReview.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@RestController
public class RestaurantController {
    @Autowired
    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping("/restaurant")
    public Restaurant createNewRestaurant(@RequestBody Restaurant restaurant) {
        if(restaurantRepository.findByNameAndZipcode(restaurant.getName(), restaurant.getZipcode()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This restaurant already exist");
        }
        return restaurantRepository.save(restaurant);
    }

    @GetMapping("/restaurant/{id}")
    public Optional<Restaurant> getRestaurantById(@PathVariable("id") Long id) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        if(restaurantOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This restaurant does not exist");
        }
        return restaurantRepository.findById(id);
    }

    @GetMapping("/restaurant/search")
    public Iterable<Restaurant> searchRestaurants(@RequestParam(name="zipcode", required = false) String zipcode,
                                                  @RequestParam(name="keyword", required = false) String keyword,
                                                  @RequestParam(name="maxPeanutAllergy", required = false) Float peanutAllergy,
                                                  @RequestParam(name="maxEggAllergy", required = false) Float eggAllergy,
                                                  @RequestParam(name="maxDairyAllergy", required = false) Float dairyAllergy) {
        if(zipcode != null && keyword != null) {
            return restaurantRepository.findByZipcodeAndKeyword(zipcode, keyword);
        }
        return new ArrayList<>();
    }
}
