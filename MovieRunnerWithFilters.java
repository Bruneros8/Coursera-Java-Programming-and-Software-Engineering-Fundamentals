import java.util.ArrayList;

public class MovieRunnerWithFilters {
    public void printAverageRatingsByMinutes() {
        ThirdRatings thirdRatings = new ThirdRatings("data/ratedmoviesfull.csv");
        System.out.println(thirdRatings.getRaterSize() + "raters are found");
        System.out.println("There're " + MovieDatabase.size() + "movies in the database");
        int minimalRaters = 1;
        int minMinutes = 110;
        int maxMinutes = 170;
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println(averageRatings.size() + " movies are found");
        for (Rating rating: averageRatings) {
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByDirectors() {
        ThirdRatings thirdRatings = new ThirdRatings("data/ratedmoviesfull.csv");
        System.out.println(thirdRatings.getRaterSize() + "raters are found");
        System.out.println("There're " + MovieDatabase.size() + "movies in the database");
        int minimalRaters = 1;
        String directors = "Charles Chaplin,Michael Mann,Spike Jonze";
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new DirectorsFilter(directors));
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println("Number of movies found: " + averageRatings.size());
        for (Rating rating: averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(rating.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings thirdRatings = new ThirdRatings("data/ratedmoviesfull.csv");
        System.out.println(thirdRatings.getRaterSize() + "raters are found");
        System.out.println("There're " + MovieDatabase.size() + "movies in the database");
        int minimalRaters = 1;
        int year = 1980;
        String genre = "Romance";
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(year));
        allFilters.addFilter(new GenreFilter(genre));
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println(averageRatings.size() + " movies are found");
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings thirdRatings = new ThirdRatings("data/ratedmoviesfull.csv");
        System.out.println(thirdRatings.getRaterSize() + "raters are found");
        System.out.println("There're " + MovieDatabase.size() + "movies in the database");
        int minimalRaters = 1;
        int minMinutes = 30;
        int maxMinutes = 170;
        String directors = "Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola";
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        allFilters.addFilter(new DirectorsFilter(directors));
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println(averageRatings.size() + " movies are found");
        for (Rating rating: averageRatings) {
            System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem()) + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(rating.getItem()));
        }
    }
}