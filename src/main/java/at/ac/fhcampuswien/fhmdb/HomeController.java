package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import at.ac.fhcampuswien.fhmdb.models.MovieService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView<Movie> movieListView;

    @FXML
    public JFXComboBox<Genre> genreComboBox;

    @FXML
    public JFXButton sortBtn;

    @FXML
    public JFXButton resetBtn;

    public List<Movie> allMovies = Movie.initializeMovies();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList(); // automatically updates
                                                                                                // corresponding UI
                                                                                                // elements when
                                                                                                // underlying data
                                                                                                // changes
    private final MovieService movieService = new MovieService();
    private boolean isAscending = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies); // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies); // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // add genre filter items
        genreComboBox.getItems().addAll(Genre.values());
        genreComboBox.setPromptText("Filter by Genre");

        // add event handlers to buttons
        searchBtn.setOnAction(actionEvent -> applyFilters());

        // Reset button event handler
        resetBtn.setOnAction(actionEvent -> resetFilters());

        // Genre combo box change listener
        genreComboBox.setOnAction(event -> applyFilters());

        // Sort button implementation
        sortBtn.setOnAction(actionEvent -> {
            if (sortBtn.getText().equals("Sort (asc)")) {
                isAscending = false;
                applySorting();
                sortBtn.setText("Sort (desc)");
            } else {
                isAscending = true;
                applySorting();
                sortBtn.setText("Sort (asc)");
            }
        });
    }

    private void applyFilters() {
        String searchQuery = searchField.getText();
        Genre selectedGenre = genreComboBox.getValue();

        // First filter by search query
        List<Movie> filteredMovies = movieService.filterMoviesByQuery(allMovies, searchQuery);

        // Then filter by genre
        filteredMovies = movieService.filterMoviesByGenre(filteredMovies, selectedGenre);

        // Update observable list
        observableMovies.clear();
        observableMovies.addAll(filteredMovies);

        // Reapply current sorting if any
        applySorting();
    }

    private void applySorting() {
        List<Movie> sortedMovies = movieService.sortMovies(
                new ArrayList<>(observableMovies),
                isAscending);

        observableMovies.clear();
        observableMovies.addAll(sortedMovies);
    }

    public void resetFilters() {
        // Clear search field
        searchField.clear();

        // Reset genre selection
        genreComboBox.setValue(null);

        // Reset movies to original list
        observableMovies.clear();
        observableMovies.addAll(allMovies);

        // Maintain current sort order
        applySorting();
    }
}