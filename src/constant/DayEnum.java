package constant;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum DayEnum {
    MONDAY('M', "MON", 1),
    TUESDAY('T', "TUE", 2),
    WEDNESDAY('W', "WED", 3),
    THURSDAY('H', "THU", 4),
    FRIDAY('F', "FRI", 5),
    SATURDAY('S', "SAT", 6),
    SUNDAY('S', "SUN", 0);

    private final String dayInSingleChar;
    private final String dayInShortStr;
    private final int numberCode;
    private static final Map<Integer, DayEnum> dayEnumMap;

    static {
        dayEnumMap = new HashMap<>();

        Arrays.stream(DayEnum.values()).toList().forEach(dayEnum -> {
            dayEnumMap.put(dayEnum.getNumberCode(), dayEnum);
        });
    }

    DayEnum(char singleLetter, String shortString, int numberCode) {
        this.dayInSingleChar = "" + singleLetter;
        this.dayInShortStr = shortString;
        this.numberCode = numberCode;
    }

    public static String getSingleLetterByNumCode(int numberCode) {
        return dayEnumMap.get(numberCode).getDayInSingleChar();
    }
}
