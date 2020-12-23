package vc.thinker.cabbage.stats.bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Author: Sean
 * Date: 24/8/2017
 * Time: 7:39 PM
 */
public class ItemCollector {
    public static long dayMilliSeconds = 1000 * 60 * 60 * 24;
    private static Calendar calendar = Calendar.getInstance();
    private static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd ");
    private String title = "";
    private long day;
    private int first = 0;
    private double doubleFirst = 0.0;
    private int second = 0;
    private double doubleSecond = 0.0;


    public ItemCollector(long day) {
        this.day = day;
    }

    public static List<ItemCollector> getItemsByDays(int day) {
        List<ItemCollector> itemCollectors = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_MONTH, -1 * day);

        for (int i = 0; i < day; i++) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            itemCollectors.add(new ItemCollector(getDayFromCalendar(calendar)));
        }

        return itemCollectors;
    }

    public static long getDayFromCalendar(Calendar calendar) {
        return calendar.getTimeInMillis() / dayMilliSeconds;
    }

    public String getTitle() {
        calendar.setTimeInMillis(day * dayMilliSeconds);
        return formatter.format(calendar.getTime());

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getFirst() {
        return first + (int) doubleFirst;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public void addFirst(int first) {
        this.first += first;
    }

    public void addFirst(double first) {
        this.doubleFirst += first;
    }

    public int getSecond() {
        return second + (int) doubleSecond;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void addSecond(int second) {
        this.second += second;
    }

    public void addSecond(double second) {
        this.doubleSecond += second;
    }
}
