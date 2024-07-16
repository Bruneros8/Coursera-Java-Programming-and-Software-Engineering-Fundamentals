import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(moviefile);
        myRaters = firstRatings.loadRaters(ratingsfile);
    }

    public int getMovieSize() {
        return myMovies.size();
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    public double getAverageByID(String id, int minimalRaters) {
        int count = 0;
        double totalRating = 0.0;        
        for (Rater rater: myRaters) {
            if (rater.hasRating(id)) {
                count++;
                totalRating += rater.getRating(id);
            }
        }
        if (count >= minimalRaters) {
            return totalRating / count;
        }
        else {
            return 0.0;
        }
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> averageRatings = new ArrayList<>();
        for (Movie movie: myMovies) {
            double averageRating = getAverageByID(movie.getID(), minimalRaters);
            if (averageRating > 0.0) {
                averageRatings.add(new Rating(movie.getID(), averageRating));
            }
        }
        return averageRatings;
    }
    
    public String getTitle(String id) {
        for (Movie movie: myMovies) {
            if (movie.getID().equals(id)) {
                return movie.getTitle();
            }
        }
        return "NO SUCH TITLE.";
    }
    
    public String getID(String title) {
        for (Movie movie: myMovies) {
            if (movie.getTitle().equals(title)) {
                return movie.getID();
            }
        }
        return "NO SUCH ID.";
    }
}