package com.example.krankenhaus.srccode.entities;

import android.content.Context;

import com.example.krankenhaus.R;

public enum HealthState {
    NEW("NEW"),
    CONTAGIOUS("CONTAGIOUS"),
    STABLE("STABLE"),
    CRITICAL("CRITICAL"),
    RECOVERED("RECOVERED");

    public final String value;

    private HealthState(String value) {
        this.value = value;
    }

    public String getString(Context context) {
        switch (this) {
            case NEW:
                return context.getString(R.string.health_state_new);
            case CONTAGIOUS:
                return context.getString(R.string.health_state_contagious);
            case STABLE:
                return context.getString(R.string.health_state_stable);
            case CRITICAL:
                return context.getString(R.string.health_state_critical);
            case RECOVERED:
                return context.getString(R.string.health_state_recovered);
        }
        return "";
    }

    public int getColor(Context context) {
        switch (this) {
            case NEW:
                return context.getColor(R.color.health_state_new);
            case CONTAGIOUS:
                return context.getColor(R.color.health_state_contagious);
            case STABLE:
                return context.getColor(R.color.health_state_stable);
            case CRITICAL:
                return context.getColor(R.color.health_state_critical);
            case RECOVERED:
                return context.getColor(R.color.health_state_recovered);
        }

        return context.getColor(R.color.black);
    }
}
