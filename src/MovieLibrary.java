import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class MovieLibrary {
	
	ArrayList<Movie> movies;

	public MovieLibrary() {
		movies = new ArrayList<Movie>();
	}

	public void addMovie(Movie movie) throws FileNotFoundException {
		movies.add(movie);
		writeMovieToFile();
		
	}
	public void removeMovie(int id) {
		for (int i = 0; i < movies.size(); i++) {
			if ((int) movies.get(i).getMovieID() == id) {
				movies.remove(i);
				System.out.println("Successfully lended");

			}
		}

	}
	
	public String toString() {
		
		String total = "";
	 for (int i=0; i<movies.size(); i++){
			Movie newMovie = movies.get(i);
			total = total + newMovie.printString();
			
			
		}
	return total;
	}
	public void writeMovieToFile() throws FileNotFoundException {
		File bookFile = new File("library.csv");
		PrintWriter wb = new PrintWriter (bookFile);
		for(Movie movie : movies) {
			String csvRecord = movie.movieCsvRecord();
			wb.println(csvRecord);
		}
		wb.close();
		
	}
}