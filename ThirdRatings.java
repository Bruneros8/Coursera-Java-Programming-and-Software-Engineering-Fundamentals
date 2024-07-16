import java.util.ArrayList;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;

    public ThirdRatings() {
        this("ratings.csv");
    }

    public ThirdRatings(String ratingsFile) {
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(ratingsFile);
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter TrueFilter) {
        ArrayList<Rating> averageRatings = new ArrayList<>();
        ArrayList<String> movies = MovieDatabase.filterBy(TrueFilter);
        for (String movieID: movies) {
            double ratingSum = 0;
            int numRaters = 0;
            for (Rater rater: myRaters) {
                if (rater.hasRating(movieID)) {
                    ratingSum += rater.getRating(movieID);
                    numRaters++;
                }
            }
            if (numRaters >= minimalRaters) {
                double averageRating = ratingSum / numRaters;
                averageRatings.add(new Rating(movieID, averageRating));
            }
        }
        return averageRatings;
    }
}