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


    @Override
    public String toString() {
        StringBuilder reviewDetails = new StringBuilder();
        if (reviews == null || reviews.isEmpty()) {
            reviewDetails.append("No hay reviews.");
        } else {
            reviewDetails.append(reviews.size()).append(" review(s)");
            reviews.forEach(reviewDetails::append);
        }

        return "\n---------------------------------------" +
                "\n  🎮 " + name +
                "\n---------------------------------------" +
                "\n  💰 Precio       : $" + price +
                "\n  🎯 Consola      : " + console +
                "\n  🏷️ Descuento    : " + (isDiscount ? "Yes" : "No") +
                "\n  📦 Total ventas : " + totalSold +
                "\n  🌐 Website      : " + officialWebsite +
                "\n  📝 Reviews      : " + reviewDetails +
                "\n---------------------------------------";
    }

}
