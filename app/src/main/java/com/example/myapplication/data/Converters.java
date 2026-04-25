package com.example.myapplication.data;

import androidx.room.TypeConverter;
import java.util.Date;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Mood fromString(String value) {
        return value == null ? null : Mood.valueOf(value);
    }

    @TypeConverter
    public static String moodToString(Mood mood) {
        return mood == null ? null : mood.name();
    }
}
