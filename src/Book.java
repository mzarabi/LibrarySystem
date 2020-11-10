public class Book extends Product {

	private int pages;
	private String author;

	public Book(int id, String title, int value, int pages, String author, String status) {
		super(id, title, value, status);
		this.pages = pages;
		this.author = author;
	}

	public int getPages() {
		return pages;
	}

	public String getAuthor() {
		return author;
	}

	@Override
	public String toString() {
		return String.format(id + " (Book): " + title + ". " + status + "\n");
	}

	@Override
	public String printInfo() {
		return String.format(id + " (Book): " + title + ": " + "Value " + value + "kr, " + "Pages " + pages + "st, "
				+ "Author " + author + "." + "\n");
	}

	@Override
	public String csvRecord() {
		return String.format("book;" + "%d;%s;%d;%d;%s;%s", id, title, value, pages, author, status + "\n");
	}

	public static Book parseProduct(String csvRecord) {
		String[] values = csvRecord.split(";");
		int bookID = Integer.parseInt(values[1]);
		String bookTitle = values[2];
		int bookValue = Integer.parseInt(values[3]);
		int pages = Integer.parseInt(values[4]);
		String author = values[5];
		String status = values[6];
		return new Book(bookID, bookTitle, bookValue, pages, author, status);
	}
}