import java.io.File;
import java.io.IOException;
import java.util.Scanner;

enum Command {
	LIST, CHECKOUT, CHECKIN, REGISTER, DEREGISTER, INFO, QUIT, UNKNOWN, MOVIE, BOOK
}

public class LibraryManager {

	ProductLibrary productLibrary = new ProductLibrary();
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		LibraryManager manager = new LibraryManager();
		manager.start();

	}

	public void start() throws IOException {

		boolean running = true;
		System.out.println("Welcome!");
		File bookFile = new File("library.csv");

		if (bookFile.exists()) {
			productLibrary.readFile();
		}

		System.out.println("Succesfully initialized system state from files.");
		System.out.println("\nCurrent inventory:");
		System.out.println(productLibrary);

		while (running) {
			System.out.print("> ");
			String userInput = sc.nextLine();
			Command command = parseCommand(userInput);

			String[] arguments = parseArguments(userInput);

			switch (command) {
			case REGISTER:
				System.out.println("What are you registering? Book (b), Movie (m)");
				break;
			case BOOK:
				addBookCommand();
				break;
			case MOVIE:
				addMovieCommand();
				break;
			case DEREGISTER:
				removeCommand(arguments);
				break;
			case INFO:
				infoCommand(arguments);
				break;
			case LIST:
				displayInventory();
				break;
			case CHECKOUT:
				lendingCommand(arguments);
				break;
			case CHECKIN:
				returnLentCommand(arguments);
				break;
			case QUIT:
				running = false;
				System.out.println("Good bye!");
				break;
			case UNKNOWN:
				System.out.println("Unknown command, please try again");

			}
		}

		sc.close();
	}

	private void addBookCommand() throws IOException {

		int bookID = 0;
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
		productLibrary.addProduct(newBook);
		System.out.println("Successfully registered " + bookTitle);

	}

	private void addMovieCommand() throws IOException {

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
		sc.nextLine();
		System.out.println("\nEnter rating: ");
		rating = sc.nextFloat();
		sc.nextLine();
		System.out.println("Successfully registered " + movieTitle);
		Movie newMovie = new Movie(movieID, movieTitle, movieValue, length, rating);
		productLibrary.addProduct(newMovie);

	}
	

	private void removeCommand(String[] arguments) throws IOException {
		int ID;
		try {
			ID = Integer.parseInt(arguments[0]);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Failed to parse");
			return;

		}

		productLibrary.removeProduct(ID);

	}

	private void infoCommand(String[] arguments) {
		int ID;
		try {
			ID = Integer.parseInt(arguments[0]);
		} catch (NumberFormatException e) {
			System.out.println("Please enter a id");
			return;

		}
		productLibrary.productInfo(ID);
	}

	private void displayInventory() throws IOException {

		System.out.println(productLibrary);

	}

	private void lendingCommand(String[] arguments) throws IOException {

		String lendingStatus = "(L)";
		int ID;
		try {
			ID = Integer.parseInt(arguments[0]);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Not a valid ID number");
			return;
		}

	}

	private void returnLentCommand(String[] arguments) throws IOException {

		String lendingStatus = "(L)";
		int ID;
		try {
			ID = Integer.parseInt(arguments[0]);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Not a valid ID number");
			return;
		}

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
		case "deregister":
			return Command.DEREGISTER;
		case "info":
			return Command.INFO;
		case "m":
			return Command.MOVIE;
		case "b":
			return Command.BOOK;
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