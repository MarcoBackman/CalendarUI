package constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum MonthEnum {
    JANUARY("JAN", 0),
    FEBRUARY("FEB", 1),
    MARCH( "MAR", 2),
    APRIL("APR", 3),
    MAY("MAY", 4),
    JUNE( "JUN", 5),
    JULY("JUL", 6),
    AUGUST("AUG", 7),
    SEPTEMBER("SEP", 8),
    OCTOBER("OCT", 9),
    NOVEMBER("NOV", 10),
    DECEMBER("DEC", 11);

    private final String monthInShortStr;
    private final int monthCode;
    private static final Map<Integer, MonthEnum> monthEnumMap;

    static {
        monthEnumMap = new HashMap<>();

        Arrays.stream(MonthEnum.values()).toList().forEach(monthEnum -> {
            monthEnumMap.put(monthEnum.getMonthCode(), monthEnum);
        });
    }

    MonthEnum(String monthInShortStr, int monthCode) {
        this.monthInShortStr = monthInShortStr;
        this.monthCode = monthCode;
    }

    public int getMonthCode() {
        return monthCode;
    }

    public String getMonthInShortStr() {
        return monthInShortStr;
    }

    public static String getShortMonthStrByNumberCode(int numberCode) {
        return monthEnumMap.get(numberCode).getMonthInShortStr();
    }
}
