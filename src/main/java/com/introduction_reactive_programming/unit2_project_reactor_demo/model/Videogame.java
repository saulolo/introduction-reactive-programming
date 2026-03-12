package com.introduction_reactive_programming.unit2_project_reactor_demo.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Videogame {

    String name;
    Double price;
    Console console;
    List<Review> reviews;
    String officialWebsite;
    Boolean isDiscount;
    Integer totalSold;
}
