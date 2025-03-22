package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;
public class MovieService {

    // enable case-insensitive search in title or description
    public List<Movie> filterMoviesByQuery(List<Movie> movies, String searchQuery) {
        List<Movie> filtered = new ArrayList<>();
    
        if (searchQuery == null || searchQuery.isEmpty()) {
            filtered.addAll(movies);
            return filtered;
        }
    
        String query = searchQuery.toLowerCase();
    
        for (Movie movie : movies) {
            String title = movie.getTitle() != null ? movie.getTitle().toLowerCase() : "";
            String description = movie.getDescription() != null ? movie.getDescription().toLowerCase() : "";
    
            if (title.contains(query) || description.contains(query)) {
                filtered.add(movie);
            }
        }
    
        return filtered;
    }

    public List<Movie> filterMoviesByGenre(List<Movie> movies, Genre genre) {
        List<Movie> filtered = new ArrayList<>();
    
        if (genre == null) {
            filtered.addAll(movies);
            return filtered;
        }
    
        for (Movie movie : movies) {
            if (movie.getGenres().contains(genre)) {
                filtered.add(movie);
            }
        }
    
        return filtered;
    }
    
    



}
