import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings() {
        SecondRatings secondRatings = new SecondRatings();
        System.out.println("Number of movies: " + secondRatings.getMovieSize());
        System.out.println("Number of raters: " + secondRatings.getRaterSize());
        int minimalRaters = 3;
        ArrayList<Rating> averageRatings = secondRatings.getAverageRatings(minimalRaters);
        Collections.sort(averageRatings);
        for (Rating rating: averageRatings) {
            String title = secondRatings.getTitle(rating.getItem());
            System.out.println(rating.getValue() + " " + title);
        }
    }
    
    public void getAverageRatingOneMovie() {
        SecondRatings secondRatings = new SecondRatings();
        String movieTitle = "The Godfather"; // Change this to the desired movie title
        String movieID = secondRatings.getID(movieTitle);
        double averageRating = secondRatings.getAverageByID(movieID, 0);
        System.out.println("Average rating for " + movieTitle + ": " + averageRating);
    }
}