package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieService {

    public List<Movie> filterMoviesByQuery(List<Movie> movies, String searchQuery) {
    if (searchQuery == null || searchQuery.isEmpty()) {
        return new ArrayList<>(movies);
    }

    String query = searchQuery.toLowerCase();
    return movies.stream()
            .filter(movie ->
                    movie.getTitle().toLowerCase().contains(query) ||
                    movie.getDescription().toLowerCase().contains(query))
            .collect(Collectors.toList());
}

}
