import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
	public void removeMovie(int id) throws IOException {
		for (int i = 0; i < movies.size(); i++) {
			if ((int) movies.get(i).getMovieID() == id) {
				System.out.println("Successfully removed " + movies.get(i).getTitle());
				movies.remove(i);
				writeMovieToFile();

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

		String filePath = "movielibrary.csv";
		FileWriter fileWriter = new FileWriter(filePath);

		for (Movie movie : movies) {
			fileWriter.write(movie.movieCsvRecord());
		}
		fileWriter.close();

	}

	public Movie readFile() throws IOException {
		
		FileReader fr  = new FileReader("movielibrary.csv");
		Scanner scanner = new Scanner(fr);
		while (scanner.hasNextLine()) {
			String csvRecord = scanner.nextLine();
			movies.add(Movie.parseMovie(csvRecord));
					
		}
		return null;
		
	}
}