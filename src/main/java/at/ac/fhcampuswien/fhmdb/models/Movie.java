package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private String title;
    private String description;
    private List<Genre> genres;

    public Movie(String title, String description, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static List<Movie> initializeMovies() {
        List<Movie> movies = new ArrayList<>();

        // The Shawshank Redemption
        movies.add(new Movie(
                "The Shawshank Redemption",
                "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
                Arrays.asList(Genre.DRAMA)
        ));

        // The Godfather
        movies.add(new Movie(
                "The Godfather",
                "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
                Arrays.asList(Genre.DRAMA, Genre.CRIME)
        ));

        // Life Is Beautiful
        movies.add(new Movie(
                "Life Is Beautiful",
                "When an open-minded Jewish librarian and his son become victims of the Holocaust, he uses a perfect mixture of will, humor, and imagination to protect his son from the dangers around their camp.",
                Arrays.asList(Genre.DRAMA, Genre.ROMANCE)
        ));

        // The Usual Suspects
        movies.add(new Movie(
                "The Usual Suspects",
                "A sole survivor tells of the twisty events leading up to a horrific gun battle on a boat, which began when five criminals met at a seemingly random police lineup.",
                Arrays.asList(Genre.CRIME, Genre.DRAMA, Genre.MYSTERY)
        ));

        // Puss in Boots
        movies.add(new Movie(
                "Puss in Boots",
                "An outlaw cat, his childhood egg-friend, and a seductive thief kitty set out in search for the eggs of the fabled Golden Goose to clear his name, restore his lost honor, and regain the trust of his mother and town.",
                Arrays.asList(Genre.COMEDY, Genre.FAMILY, Genre.ANIMATION)
        ));

        // Avatar
        movies.add(new Movie(
                "Avatar",
                "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.",
                Arrays.asList(Genre.ANIMATION, Genre.DRAMA, Genre.ACTION)
        ));

        // The Wolf of Wall Street
        movies.add(new Movie(
                "The Wolf of Wall Street",
                "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.",
                Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.BIOGRAPHY)
        ));

        return movies;
    }
}
