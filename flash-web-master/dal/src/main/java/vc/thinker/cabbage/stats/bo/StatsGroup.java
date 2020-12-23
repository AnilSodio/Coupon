package vc.thinker.cabbage.stats.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Sean
 * Date: 26/8/2017
 * Time: 11:31 AM
 */
public class StatsGroup {
    private String title;
    private String icon;
    private List<StatsGroupItem> items;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<StatsGroupItem> getItems() {
        return items == null ? new ArrayList<>() : items;
    }

    public void setItems(List<StatsGroupItem> items) {
        this.items = items;
    }

    public void addItem(StatsGroupItem item) {
        if (items == null) {
            items = new ArrayList<>();
        }

        items.add(item);
    }
}
