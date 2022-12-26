package com.example.krankenhaus.srccode.converter;

import androidx.room.TypeConverter;

import com.example.krankenhaus.srccode.entities.HealthState;

public class HealthStateConverter {
    @TypeConverter
    public static HealthState toHealthState(String healthStateString){
        return HealthState.valueOf(healthStateString);
    }

    @TypeConverter
    public static String fromHealthState(HealthState healthState){
        return healthState.value;
    }
}
