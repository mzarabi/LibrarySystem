public class Book {
	
	private int bookID;
	private String bookTitle;
	private int bookValue;
	private int pages;
	private String author;

	public Book(int bookID, String bookTitle, int bookValue, int pages, String author) {
		super();
		this.bookID = bookID;
		this.bookTitle = bookTitle;
		this.bookValue = bookValue;
		this.pages = pages;
		this.author = author;

	}

	public int getBookID() {
		return bookID;
	}

	public String getTitle() {
		return bookTitle;
	}

	public int getBookValue() {
		return bookValue;
	}

	public int getPages() {
		return pages;
	}

	public String getAuthor() {
		return author;
	}

	public String toString() {
		return String.format("\n"+bookID+" (Book) " + bookTitle+": "+"Value "+bookValue+"kr, "+"Pages "+pages+"st, "+"Author "+author+".");
	}
	
	public String bookString() {
		return String.format("\n"+bookID+" (Book): " + bookTitle+".");
	}
	
	public String bookCsvRecord() {
		return String.format("%d,%s,%d,%d,%s", bookID,bookTitle,bookValue,pages,author+"\n");
	}
}