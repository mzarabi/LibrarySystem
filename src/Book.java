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
		return String.format(bookID+" (Book) " + bookTitle+": "+"Value "+bookValue+"kr, "+"Pages "+pages+"st, "+"Author "+author+"."+"\n");
	}
	
	public String bookString() {
		return String.format(bookID+" (Book): " + bookTitle+"."+"\n");
	}
	
	public String bookCsvRecord() {
		return String.format("%d,%s,%d,%d,%s", bookID,bookTitle,bookValue,pages,author+"\n");
	}
	public static Book parseBook(String csvRecord) {
		String[] values = csvRecord.split(",");
		int bookID = Integer.parseInt(values[0]);
		String bookTitle = values[1];
		int bookValue = Integer.parseInt(values[2]);
		int pages = Integer.parseInt(values[3]);
		String author = values[4];
		return new Book(bookID,bookTitle,bookValue,pages,author);
		
		
	}
}