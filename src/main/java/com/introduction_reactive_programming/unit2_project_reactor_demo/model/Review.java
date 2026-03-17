package com.introduction_reactive_programming.unit2_project_reactor_demo.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
 public class Review {

    String comment;
    Integer score;


    @Override
    public String toString() {
        return "\n      │  💬 " + comment +
                "\n      │  🏅 Score: " + "⭐".repeat(score) + " (" + score + "/5)" +
                "\n      │-----------------------";
    }
}
