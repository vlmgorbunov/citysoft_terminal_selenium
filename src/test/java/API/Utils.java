package API;

import org.joda.time.DateTime;

import java.sql.Timestamp;

public class Utils {

    //   private static final SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd.HH.mm");

    //   public static SimpleDateFormat currentDateISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    static DateTime currentDate = new DateTime();
    public static DateTime lastMonthISO8601 = currentDate.minusMonths (1).withTimeAtStartOfDay();

    public static DateTime yesterdayStartOfDayISO8601 = currentDate.minusDays(1).withTimeAtStartOfDay();

    public static DateTime yesterdayEndOFDayISO8601 = currentDate.minusDays(1).withTimeAtStartOfDay().withHourOfDay(23).withMinuteOfHour(59);

    public static DateTime lastWeekISO8601 = currentDate.minusWeeks(1).withTimeAtStartOfDay();
    public static DateTime currentDateStartOFDayISO8601 = currentDate.withTimeAtStartOfDay();
    public static DateTime currentDateEndOFDayISO8601 = currentDate.withHourOfDay(23).withMinuteOfHour(59);


    public static void main(String[] args) {

        // method 1
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);

        System.out.println("date "+currentDateEndOFDayISO8601);


    }
}
