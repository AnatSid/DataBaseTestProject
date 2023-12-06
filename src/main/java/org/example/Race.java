package org.example;

public class Race {
    private long id;
    private String style;
    private int distance;


    public Race(long id, String style,int distance) {
        this.id = id;
        this.style = style;
        this.distance = distance;

    }

    public Race() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
    @Override
    public String toString() {
        return "Race{" +
                "id=" + id +
                ", distance=" + distance +
                ", style='" + style + '\'' +
                '}';
    }
}
