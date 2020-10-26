import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class BookLibrary {

	ArrayList<Book> books;

	public BookLibrary() {
		books = new ArrayList<Book>();
	}

	public void addBook(Book book) throws IOException {
		books.add(book);
		writeBookToFile();

	}

	public void removeBook(int id) {
		for (int i = 0; i < books.size(); i++) {
			if ((int) books.get(i).getBookID() == id) {
				books.remove(i);
				System.out.println("Successfully lended");

			}
		}

	}

	public void bookInfo(int id) {

		for (Book book : books)
			if (book.getBookID() == id) {
				System.out.println(book.toString());
			} else
				System.out.println("Error. No book with ID registered");
		return;

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
		FileWriter fileWriter = new FileWriter(filePath, true);

		for (Book book : books) {
			String csvRecord = book.bookCsvRecord();
			fileWriter.write(csvRecord);
		}
		fileWriter.close();

	}
}
