package com.example.demo.common;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TimeUtil {
    public static Long getNowTime() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    }
}
