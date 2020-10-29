
public class Movie extends Product implements ProductInterface {

	private float rating;
	private int length;

	public Movie(int id, String title, int value, int length, float rating) {
		super(id, title, value);
		this.rating = rating;
		this.length = length;
	}

	public float getRating() {
		return rating;
	}

	public int getLength() {
		return length;
	}

	public String toString() {
		return String.format(id + " (Movie): " + title + "." + " (in stock)" + "\n");
	}

	@Override
	public String printInfo() {
		return String.format(id + " (Movie): " + title + ": " + "Value " + value + "kr, " + "Length " + length + "m, "
				+ "Rating " + rating + "." + "\n");

	}

	@Override
	public String csvRecord() {
		return String.format("movie," + "%d,%s,%d,%d,%s", id, title, value, length, rating + "\n");
	}

	public static Movie parseProduct(String csvRecord) {
		String[] values = csvRecord.split(",");
		int movieID = Integer.parseInt(values[1]);
		String movieTitle = values[2];
		int movieValue = Integer.parseInt(values[3]);
		int length = Integer.parseInt(values[4]);
		float rating = Float.parseFloat(values[5]);
		return new Movie(movieID, movieTitle, movieValue, length, rating);
	}
}
