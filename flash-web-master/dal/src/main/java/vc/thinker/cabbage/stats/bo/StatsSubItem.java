package vc.thinker.cabbage.stats.bo;

/**
 * Author: Sean
 * Date: 24/8/2017
 * Time: 10:08 AM
 */
public class StatsSubItem {

    public StatsSubItem(String label, int value) {
        this.label = label;
        this.value = value;

    }

    private String label;
    private int value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
