package com.example.krankenhaus.srccode.converter;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter{
    @TypeConverter
    public static LocalDate toLocalDate(String dateString){
        return LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
    }

    @TypeConverter
    public static String fromLocalDate(LocalDate localDate){
        return DateTimeFormatter.ISO_DATE.format(localDate);
    }
}
