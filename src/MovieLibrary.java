import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class MovieLibrary {
	
	ArrayList<Movie> movies;

	public MovieLibrary() {
		movies = new ArrayList<Movie>();
	}

	public void addMovie(Movie movie) throws IOException {
		movies.add(movie);
		writeMovieToFile();
		
	}
	public void removeMovie(int id) {
		for (int i = 0; i < movies.size(); i++) {
			if ((int) movies.get(i).getMovieID() == id) {
				movies.remove(i);
				System.out.println("Successfully removed movie");

			}
		}

	}
	
	public void movieInfo(int id) {
		boolean found = false;
		for (Movie movie : movies) {
			if(movie.getMovieID()==id) {
				System.out.println(movie.toString());
				found=true;
			}
		}
		if(!found) {
			System.out.println("Error! No movie with id "+id+" registered");
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
	public void writeMovieToFile() throws IOException {
		String filePath = "library.csv";
		FileWriter fileWriter = new FileWriter(filePath, true);
		for(Movie movie : movies) {
			String csvRecord = movie.movieCsvRecord();
			fileWriter.write(csvRecord);
		}
		fileWriter.close();
		
	}
}