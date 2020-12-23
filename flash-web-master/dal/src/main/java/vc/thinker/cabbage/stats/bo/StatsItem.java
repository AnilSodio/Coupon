package vc.thinker.cabbage.stats.bo;


import java.util.ArrayList;
import java.util.List;

/**
 * Author: Sean
 * Date: 24/8/2017
 * Time: 10:07 AM
 */
public class StatsItem {
    private String title;

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    private String subTitle;


    private List<StatsSubItem> list;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<StatsSubItem> getList() {
        return list == null ? new ArrayList<>() : list;
    }

    public void setList(List<StatsSubItem> list) {
        this.list = list;
    }

    public void addItem(StatsSubItem item) {
        if (list == null) {
            list = new ArrayList<>();
        }

        list.add(item);
    }
}
