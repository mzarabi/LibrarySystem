import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class BookLibrary implements Serializable {

	ArrayList<Book> books;


	public BookLibrary() {
		books = new ArrayList<Book>();
	}

	public void addBook(Book book) throws IOException {
		books.add(book);
		writeBookToFile();
		

	}

	public void removeBook(int id) throws IOException {
		for (int i = 0; i < books.size(); i++) {
			if ((int) books.get(i).getBookID() == id) {
				books.remove(i);
				System.out.println("Successfully removed book");
				writeBookToFile();

			}
		}

	}

	public void bookInfo(int id) {
		boolean found = false;

		for (Book book : books) {
			if (book.getBookID() == id) {
				System.out.println(book.toString());
				found = true;
			}
		}
		if (!found) {
			System.out.println("Error! No book with id " + id + " registered");
		}
	}

	public String toString() {
		String total = "";
		for (int i = 0; i < books.size(); i++) {
			Book newBook = books.get(i);
			total = total + newBook.bookString();

		}
		return total;
	}

	public void writeBookToFile() throws IOException {

		String filePath = "library.csv";
		FileWriter fileWriter = new FileWriter(filePath);

		for (Book book : books) {
			fileWriter.write(book.bookCsvRecord());
		}
		fileWriter.close();

	}

	public Book readFile() throws IOException {
		books = new ArrayList<Book>();
		
		FileReader fr  = new FileReader("/Users/marcuszarabi/eclipse-workspace/LibrarySystem/library.csv");
		Scanner scanner = new Scanner(fr);
		while (scanner.hasNextLine()) {
			String csvRecord = scanner.nextLine();
			books.add(Book.parseBook(csvRecord));
					
		}
		return null;
		
	}
}