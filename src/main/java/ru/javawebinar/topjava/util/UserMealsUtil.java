package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 29, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 29, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 29, 20, 0), "Ужин", 300)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
    }

    private static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        int i = 0;
        for (UserMeal meal : mealList) {
            List<UserMealWithExceed> Umwe = Arrays.asList(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(), isExceed(meal, mealList, caloriesPerDay)));
            UmwePrint(Umwe, startTime, endTime);
        }
        return null;
    }

    private static void UmwePrint(List<UserMealWithExceed> Umwe, LocalTime startTime, LocalTime endTime) {
        for (UserMealWithExceed Um : Umwe) {
            if (TimeUtil.isBetween(Um.dateTime.toLocalTime(), startTime, endTime))
                System.out.println(Um.dateTime + " " + Um.description + " " + Um.calories + " " + Um.exceed);
        }
    }


    public static boolean isExceed(UserMeal meal, List<UserMeal> mealList, int caloriesPerDay) {
        int sum = 0;
        for (UserMeal meal1 : mealList)
            if (meal.getDateTime().toLocalDate().compareTo(meal1.getDateTime().toLocalDate()) == 0)
                sum += meal1.getCalories();
        if (sum > caloriesPerDay) return true;
        else return false;
    }
}
