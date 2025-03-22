package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import at.ac.fhcampuswien.fhmdb.models.MovieService;

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
    public JFXButton filterBtn;

    public List<Movie> allMovies = Movie.initializeMovies();
    private final MovieService movieService = new MovieService();

    private final ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // add genre filter
        genreComboBox.getItems().addAll(Genre.values());
        genreComboBox.setPromptText("Filter by Genre");

        searchBtn.setOnAction(actionEvent -> applyFilters());

        if (filterBtn != null) {
            filterBtn.setOnAction(actionEvent -> applyFilters());
        }

        genreComboBox.setOnAction(event -> applyFilters());

        }

    private void applyFilters() {
        String searchQuery = searchField.getText();
        Genre selectedGenre = genreComboBox.getValue();
    
        List<Movie> filteredMovies = movieService.filterMoviesByQuery(allMovies, searchQuery);
        filteredMovies = movieService.filterMoviesByGenre(filteredMovies, selectedGenre);
    
        observableMovies.clear();
        observableMovies.addAll(filteredMovies);
        applySorting();
    }

    private void applySorting() {
        List<Movie> sortedMovies = movieService.sortMovies(
            new ArrayList<>(observableMovies),
            isAscending
        );
    
        observableMovies.clear();
        observableMovies.addAll(sortedMovies);
    }    

    public void clearFilters() {
        searchField.clear();
        genreComboBox.setValue(null);
    
        observableMovies.clear();
        observableMovies.addAll(allMovies);
    
        applySorting();  // Maintain current sort order
    }
    

    
}