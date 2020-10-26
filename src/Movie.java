
public class Movie {

	private int movieID;
	private String movieTitle;
	private int movieValue;
	private float rating;
	private int length;

	public Movie(int movieID, String movieTitle, int movieValue, float rating, int length) {
		super();
		this.movieID = movieID;
		this.movieTitle = movieTitle;
		this.movieValue = movieValue;
		this.rating = rating;
		this.length = length;
	}

	public int getMovieID() {
		return movieID;
	}

	public String getTitle() {
		return movieTitle;
	}

	public int getValue() {
		return movieValue;
	}

	public float getRating() {
		return rating;
	}

	public int getLength() {
		return length;
	}

	public String toString() {
		return String.format("(Movie) " + movieTitle+": "+"Value "+movieValue+"kr, "+"Length "+length+", "+"Rating "+rating+".");
	}
	
	public String printString() {
		return String.format("\n" + movieID + " (Movie): " + movieTitle );
	}	
	public String movieCsvRecord() {
		return String.format("%d,%s,%d,%d,%f", movieID, movieTitle, movieValue,length,rating);
	}
	
	public void Hej() {
		System.out.println("hej");
	}
}
