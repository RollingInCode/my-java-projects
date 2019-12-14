import java.time.LocalTime;

public class Player {

    private String name;
    private int score;
    private LocalTime duration;

    public String getName() {

        return name;
    }

    public int getScore() {

        return score;
    }

    public LocalTime getDuration() {

        return duration;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }
}
