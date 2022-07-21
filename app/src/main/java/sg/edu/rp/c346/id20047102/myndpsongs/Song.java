package sg.edu.rp.c346.id20047102.myndpsongs;

import java.io.Serializable;

public class Song implements Serializable {

    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song(int id, String title, String singers, int year, int stars) {
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSingers() {
        return singers;
    }

    public void setSingers(String singers) {
        this.singers = singers;
    }

    public String getYear() {
        String yearString = String.valueOf(year);
        return yearString;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStars() {
        String ratings = "";

        if (stars == 1) {
            ratings = "*";
        } else if (stars == 2) {
            ratings = "* *";
        } else if (stars == 3) {
            ratings = "* * *";
        } else if (stars == 4) {
            ratings = "* * * *";
        } else if (stars == 5) {
            ratings = "* * * * *";
        }

        return ratings;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

}
