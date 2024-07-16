public class DirectorsFilter implements Filter {
    private String[] directors;
    public DirectorsFilter(String directors) {
        this.directors = directors.split(",");
    }

    @Override
    public boolean satisfies(String id) {
        String[] movieDirectors = MovieDatabase.getDirector(id).split(",");
        for (String director: directors) {
            for (String movieDirector: movieDirectors) {
                if (movieDirector.trim().equals(director.trim())) {
                    return true;
                }
            }
        }
        return false;
    }
}