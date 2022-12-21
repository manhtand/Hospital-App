package com.example.krankenhaus.srccode.converter;

import androidx.room.TypeConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDataTimeConverter {
    @TypeConverter
    public static LocalDateTime toLocalDate(String dateString){
        return LocalDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME);
    }

    @TypeConverter
    public static String fromLocalDateTime(LocalDateTime localDateTime){
        return DateTimeFormatter.ISO_DATE_TIME.format(localDateTime);
    }
}
