package org.example;

public class Race {
    private long id;
    private SwimStyle style;
    private int distance;


    public Race(long id, SwimStyle style,int distance) {
        this.id = id;
        this.style = style;
        this.distance = distance;

    }

    public Race() {
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
