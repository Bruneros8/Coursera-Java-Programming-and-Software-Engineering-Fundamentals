import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movieData = new ArrayList<>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record: parser) {
            String currentID = record.get("id");
            String currentTitle = record.get("title");
            String currentYear = record.get("year");
            String currentCountry = record.get("country");
            String currentGenre = record.get("genre");
            String currentDirector = record.get("director");
            int currentMinutes = Integer.parseInt(record.get("minutes"));
            String currentPoster = record.get("poster");
            Movie currentMovie = new Movie(currentID, currentTitle, currentYear, currentCountry, currentGenre, currentDirector, currentPoster, currentMinutes);
            movieData.add(currentMovie);
        }
        return movieData;
    }

    public void testLoadMovies() {
        ArrayList<Movie> loadMovies = loadMovies("data/ratedmovies_short.csv");
        System.out.println("There're " + loadMovies.size() + " movies in the file ratedmovies_short.csv");
        int comedyCount = 0;
        int longMoviesCount = 0;
        int maxMoviesByDirector = 0;
        HashMap<String, Integer> directorCounts = new HashMap<>();
        for (Movie movie: loadMovies) {
            if (movie.getGenres().contains("Comedy")) {
                comedyCount++;
            }
            if (movie.getMinutes() > 150) {
                longMoviesCount++;
            }
            String[] directors = movie.getDirector().split(", ");
            for (String director: directors) {
                directorCounts.put(director, directorCounts.getOrDefault(director, 0) + 1);
            }
        }
        System.out.println(comedyCount + " movies are comedy movies");
        System.out.println(longMoviesCount + " movies are over 150 minutes long");
        if (!directorCounts.isEmpty()) {
            maxMoviesByDirector = Collections.max(directorCounts.values());
            System.out.println(maxMoviesByDirector + " is the maximum number of movies directed by any director");
            System.out.print("Below are their names: ");
            for (Map.Entry<String, Integer> entry : directorCounts.entrySet()) {
                if (entry.getValue().equals(maxMoviesByDirector)) {
                    System.out.print(entry.getKey() + ", ");
                }
            }
            System.out.println();
        }
        else {
            System.out.println("NO DIRECTOR DATA FOUND");
        }
    }

    public ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> raterData = new ArrayList<>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record: parser) {
            String raterID = record.get("rater_id");
            String movieID = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));
            Rater currentRater = null;
            for (Rater rater: raterData) {
                if (rater.getID().equals(raterID)) {
                    currentRater = rater;
                    break;
                }
            }
            if (currentRater == null) {
                currentRater = new EfficientRater(raterID);
                raterData.add(currentRater);
            }
            currentRater.addRating(movieID, rating);
        }
        return raterData;
    }

    public void testLoadRaters() {
        ArrayList<Rater> loadRaters = loadRaters("data/ratings_short.csv");
        System.out.println("There're " + loadRaters.size() + " raters in the file ratings_short.csv in total");
        String raterID = "2";
        int ratingsForRater = 0;
        int maxRatings = 0;
        ArrayList<String> maxRaters = new ArrayList<>();
        String movieID = "1798709";
        int ratingsForMovie = 0;
        HashSet<String> uniqueMovies = new HashSet<>();
        for (Rater rater: loadRaters) {
            if (rater.getID().equals(raterID)) {
                ratingsForRater = rater.numRatings();
            }
            int numRatings = rater.numRatings();
            if (numRatings > maxRatings) {
                maxRatings = numRatings;
                maxRaters.clear();
                maxRaters.add(rater.getID());
            }
            else if (numRatings == maxRatings) {
                maxRaters.add(rater.getID());
            }
            if (rater.hasRating(movieID)) {
                ratingsForMovie++;
            }
            ArrayList<String> ratedItems = rater.getItemsRated();
            uniqueMovies.addAll(ratedItems);
        }
        System.out.println("The rater " + raterID + " has " + ratingsForRater + " ratings");
        System.out.println(maxRatings + " is the maximum number of ratings by any rater");
        System.out.println(maxRaters.size() + " raters achieved this number");
        System.out.println("Those are the raters with maximum ratings: " + maxRaters);
        System.out.println("The movie " + movieID + " has " + ratingsForMovie + " ratings");
        System.out.println(uniqueMovies.size() + " is the number of different movies rated by all raters");
    }
}