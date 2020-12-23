package vc.thinker.cabbage.stats.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Sean
 * Date: 24/8/2017
 * Time: 2:05 PM
 */
public class LineGroup {
    private String title;
    private String viewName;
    private List<Line> lines;

    private List<String> xAxis;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public List<Line> getLines() {
        return lines == null ? new ArrayList<>() : lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public void addLine(Line line) {
        if (lines == null) {
            lines = new ArrayList<>();
        }

        lines.add(line);
    }

    public List<String> getxAxis() {
        return xAxis == null ? new ArrayList<>() : xAxis;
    }

    public void setxAxis(List<String> xAxis) {
        this.xAxis = xAxis;
    }

    public void addAxisLabel(String label) {
        if (xAxis == null) {
            xAxis = new ArrayList<>();
        }

        xAxis.add(label);
    }


    public List<String> getLabels() {
        List<String> labels = new ArrayList<>();
        getLines().forEach(line -> labels.add(line.getName()));
        return labels;
    }
}
