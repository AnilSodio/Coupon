package vc.thinker.cabbage.stats.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Sean
 * Date: 24/8/2017
 * Time: 2:06 PM
 */
public class Line {
    private String name;
    private String stack;
    private Boolean showTop;
    private List<Integer> datas;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public Boolean getShowTop() {
        return showTop == null ? Boolean.FALSE : Boolean.TRUE;
    }

    public void setShowTop(Boolean showTop) {
        this.showTop = showTop;
    }

    public List<Integer> getDatas() {
        return datas == null ? new ArrayList<>() : datas;
    }

    public void setDatas(List<Integer> datas) {
        this.datas = datas;
    }


    public void addData(Integer data) {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        datas.add(data);
    }
}
