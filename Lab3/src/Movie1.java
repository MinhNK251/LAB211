import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Movie1 {
    private List<Byte> ratingList;
    private String title;
    private String director;
    private String actor;
    private byte rating;
    
    public Movie1() {
        ratingList = new ArrayList<>();
    }
    public Movie1(String title, String director, String actor, byte rating) {
        this.title = title;
        this.director = director;
        this.actor = actor;
        this.rating = rating;
    }
    //<editor-fold defaultstate="collapsed" desc="GETTERS AND SETTERS">
    public String getTitle() {
        return "Titanic";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return "James Cameron";
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return "Leonardo DiCaprio";
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }
    //</editor-fold>
    
    public void addRating(){
        Scanner sc = new Scanner(System.in);
        System.out.print("New rating: ");
        byte rating = sc.nextByte();
        ratingList.add(rating);
        System.out.println("New rating added");
    }
    
    public void showRatings(){
        if (ratingList.isEmpty())
            System.out.println("This movie is not yet rated!");
        else {
            System.out.println("\nMovie: "+getTitle());
            System.out.println("Director: "+getDirector());
            System.out.println("Actor: "+getActor());
            System.out.print("Ratings: ");
            for (Byte rate : ratingList)
                System.out.print(rate+", ");
            System.out.println();
        }
    }
}
