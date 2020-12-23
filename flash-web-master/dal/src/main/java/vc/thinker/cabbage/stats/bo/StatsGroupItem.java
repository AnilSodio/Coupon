package vc.thinker.cabbage.stats.bo;

/**
 * Author: Sean
 * Date: 26/8/2017
 * Time: 11:31 AM
 */
public class StatsGroupItem {
    private String name;
    private String unit;
    private Double doubleValue;
    private Integer intValue;
    private String strValue;
    private String color;
    private Integer precision = 2;

    public StatsGroupItem(String name, Double value, String unit, String color) {
        this.name = name;
        this.unit = unit;
        this.doubleValue = value;
        this.color = color;
    }

    public StatsGroupItem(String name, Double value, String unit, Integer precision) {
        this.name = name;
        this.unit = unit;
        this.doubleValue = value;
        this.precision = precision;
    }


    public StatsGroupItem(String name, Double value, String unit) {
        this.name = name;
        this.unit = unit;
        this.doubleValue = value;
    }

    public StatsGroupItem(String name, Integer value, String unit, String color) {
        this.name = name;
        this.unit = unit;
        this.intValue = value;
        this.color = color;
    }

    public StatsGroupItem(String name, Integer value, String unit, Integer precision) {
        this.name = name;
        this.unit = unit;
        this.intValue = value;
        this.precision = precision;
    }

    public StatsGroupItem(String name, Integer value, String unit) {
        this.name = name;
        this.unit = unit;
        this.intValue = value;
    }

    public StatsGroupItem(String name, String value, String unit, String color) {
        this.name = name;
        this.unit = unit;
        this.strValue = value;
        this.color = color;
    }

    public StatsGroupItem(String name, String value, String unit, Integer precision) {
        this.name = name;
        this.unit = unit;
        this.strValue = value;
        this.precision = precision;
    }

    public StatsGroupItem(String name, String value, String unit) {
        this.name = name;
        this.unit = unit;
        this.strValue = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        String re = "";
        if (doubleValue != null) {
            re = String.format("%." + precision + "f", doubleValue);
        } else if (intValue != null) {
            re = intValue + "";
        } else if (strValue != null) {
            re = strValue + "";
        }

        return re;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public void setValue(Integer intValue) {
        this.intValue = intValue;
    }

    public void setValue(String strValue) {
        this.strValue = strValue;
    }

    public String getColor() {
        return color == null ? "blue-sharp" : color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
