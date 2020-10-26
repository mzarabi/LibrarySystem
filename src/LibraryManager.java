import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

enum Command {
	LIST, CHECKOUT, CHECKIN, REGISTER, DEREGISTER, INFO, QUIT, UNKNOWN
}

public class LibraryManager {

	BookLibrary bookLibrary = new BookLibrary();
	MovieLibrary movieLibrary = new MovieLibrary();
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		LibraryManager manager = new LibraryManager();
		manager.start();

	}

	public void start() throws IOException {

		boolean running = true;

		while (running) {

			String userInput = sc.nextLine();
			Command command = parseCommand(userInput);

			String[] arguments = parseArguments(userInput);

			switch (command) {
			case REGISTER:
				addProduct();
				break;
			case CHECKOUT:
				removeCommand(arguments);
				break;
			case INFO:
				infoCommand(arguments);
				break;
			case LIST:
				displayInventory();
				break;
			case QUIT:
				running = false;
				System.out.println("Good bye!");
				break;
			default:
				System.out.println("Unknown command");
			}
		}

		sc.close();
	}

	public void addProduct() throws IOException {
		System.out.println("What are you registering? Book (b), Movie (m)");
		String userInput = sc.nextLine();

		switch (userInput) {
		case "m":
			addMovieCommand();
			break;
		case "b":
			addBookCommand();
			break;
		default:
			System.out.println("Unknown command");
			return;
		}
	}

	private void addBookCommand() throws IOException {

		int bookID;
		String bookTitle;
		int bookValue;
		int pages;
		String author;

		System.out.println("\nEnter product ID: ");
		bookID = sc.nextInt();
		sc.nextLine();
		System.out.println("\nEnter title:  ");
		bookTitle = sc.nextLine();

		System.out.println("\nEnter value:  ");
		bookValue = sc.nextInt();

		System.out.println("\nEnter pages: ");
		pages = sc.nextInt();
		sc.nextLine();
		System.out.println("\nEnter author: ");
		author = sc.nextLine();

		Book newBook = new Book(bookID, bookTitle, bookValue, pages, author);
		bookLibrary.addBook(newBook);
		System.out.println("Successfully registered " + bookTitle);

	}

	private void removeCommand(String[] arguments) {
		int ID;
		try {
			ID = Integer.parseInt(arguments[0]);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Failed to parse");
			return;

		}
		bookLibrary.removeBook(ID);
		movieLibrary.removeMovie(ID);

	}

	private void infoCommand(String[] arguments) {
		int bookID;
		try {
			bookID = Integer.parseInt(arguments[0]);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Syntax error");
			return;

		}
		bookLibrary.bookInfo(bookID);
	}

	private void addMovieCommand() throws FileNotFoundException {

		int movieID;
		String movieTitle;
		int movieValue;
		int length;
		float rating;

		System.out.println("\nEnter product ID: ");
		movieID = sc.nextInt();
		sc.nextLine();
		System.out.println("\nEnter title:  ");
		movieTitle = sc.nextLine();

		System.out.println("\nEnter value:  ");
		movieValue = sc.nextInt();

		System.out.println("\nEnter length: ");
		length = sc.nextInt();

		System.out.println("\nEnter rating: ");
		rating = sc.nextFloat();
		sc.nextLine();

		System.out.println("Successfully registered " + movieTitle);
		Movie newMovie = new Movie(movieID, movieTitle, movieValue, rating, length);
		movieLibrary.addMovie(newMovie);

	}

	private void displayInventory() {
		System.out.println(bookLibrary);
		System.out.println(movieLibrary);
	}

	private Command parseCommand(String userInput) {
		String commandString = userInput.split(" ")[0];
		switch (commandString) {
		case "register":
			return Command.REGISTER;
		case "list":
			return Command.LIST;
		case "quit":
			return Command.QUIT;
		case "checkout":
			return Command.CHECKOUT;
		case "info":
			return Command.INFO;
		default:
			return Command.UNKNOWN;
		}
	}

	private String[] parseArguments(String userInput) {
		String[] commandAndArguments = userInput.split(" ");
		String[] arguments = new String[commandAndArguments.length - 1];
		for (int i = 1; i < commandAndArguments.length; i++) {
			arguments[i - 1] = commandAndArguments[i];
		}
		return arguments;
	}
}