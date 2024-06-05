package util;

import constant.DayEnum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {
    private static final Map<Integer, DayEnum> dayEnumMap = new HashMap<>();

    public DateUtil() {
        Arrays.stream(DayEnum.values()).toList().forEach(dayEnum -> {
            dayEnumMap.put(dayEnum.getNumberCode(), dayEnum);
        });
    }

}
