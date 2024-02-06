package org.example.entity;

public class Race {
    private long id;
    private SwimStyle style;
    private int distance;


    public Race(long id, SwimStyle style, int distance) {
        this.id = id;
        this.style = style;
        this.distance = distance;

    }

    public Race() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SwimStyle getStyle() {
        return style;
    }

    public void setStyle(SwimStyle style) {
        this.style = style;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
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
