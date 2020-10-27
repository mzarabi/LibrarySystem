
public class Movie {

	private int movieID;
	private String movieTitle;
	private int movieValue;
	private float rating;
	private int length;

	public Movie(int movieID, String movieTitle, int movieValue, int length,float rating) {
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
		return String.format(movieID + " (Movie) " + movieTitle+": "+"Value "+movieValue+"kr, "+"Length "+length+", "+"Rating "+rating+"."+"\n");
	}
	
	public String printString() {
		return String.format(movieID + " (Movie): " + movieTitle+"\n");
	}	
	public String movieCsvRecord() {
		return String.format("%d,%s,%d,%d,%.2f", movieID, movieTitle, movieValue,length,rating+"\n");
	}	
	public static Movie parseMovie(String csvRecord) {
		String[] values = csvRecord.split(",");
		int movieID = Integer.parseInt(values[0]);
		String movieTitle = values[1];
		int movieValue = Integer.parseInt(values[2]);
		int length = Integer.parseInt(values[3]);
		float rating = Float.parseFloat(values[4]);
		return new Movie(movieID, movieTitle, movieValue, length, rating);
		
	}
}