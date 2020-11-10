import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

enum Command {

	LIST, CHECKOUT, CHECKIN, REGISTER, DEREGISTER, INFO, QUIT, UNKNOWN, HELP
}

public class LibraryManager implements LibraryInterface {

	ProductLibrary productLibrary = new ProductLibrary();
	CustomerLibrary customerLibrary = new CustomerLibrary();

	Scanner sc = new Scanner(System.in);

	public void start() {

		boolean running = true;
		System.out.println("Welcome! Please go ahead and use the Library management program.");
		File bookFile = new File("library.csv");
		File customerFile = new File("customer.csv");

		if (bookFile.exists()) {
			productLibrary.readFile();
			if (customerFile.exists()) {
				customerLibrary.readCustomerFile();
			}
		}

		System.out.println("\nTo check your current inventory, please type 'list'.");
		System.out.println("Type 'help' to see available commands.\n");

		while (running) {
			System.out.print("Enter a command > ");
			String userInput = sc.nextLine();
			Command command = parseCommand(userInput);

			String[] arguments = parseArguments(userInput);

			switch (command) {
			case REGISTER:
				addProductCommand();
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
				checkOutCommand(arguments);
				break;
			case CHECKIN:
				checkInCommand(arguments);
				break;
			case HELP:
				helpCommand();
				break;
			case QUIT:
				running = false;
				System.out.println("Exiting Library program. Thank you for using this software");
				break;
			case UNKNOWN:
				System.out.println("Unknown command, please try again. If you need a command list. Please type 'help'\n");

			}
		}

		sc.close();
	}

	private void addProductCommand() {
		System.out.println("What are you registering? Book (b), Movie (m)");
		String userInput = sc.nextLine();
		if (userInput.equals("b")) {
			addBookCommand();
		} else if (userInput.equals("m")) {
			addMovieCommand();
		} else {
			System.out.println("Unknown command, please try again.\n");
			addProductCommand();
		}
	}

	private void addBookCommand() {
		int bookID;
		String bookTitle;
		int bookValue;
		int pages;
		String author;

		try {
			System.out.println("\nEnter product ID: ");
			bookID = Integer.parseInt(sc.nextLine());

			System.out.println("\nEnter title:  ");
			bookTitle = sc.nextLine();

			System.out.println("\nEnter value:  ");
			bookValue = Integer.parseInt(sc.nextLine());

			System.out.println("\nEnter pages: ");
			pages = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Error! Please enter an Integer, type register to try again.\n");
			return;
		}
		System.out.println("\nEnter author: ");
		author = sc.nextLine();

		Book newBook = new Book(bookID, bookTitle, bookValue, pages, author, inStock);
		productLibrary.addProduct(newBook);

	}

	private void addMovieCommand() {

		int movieID;
		String movieTitle;
		int movieValue;
		int length;
		float rating;

		try {
			System.out.println("\nEnter product ID: ");
			movieID = Integer.parseInt(sc.nextLine());

			System.out.println("\nEnter title:  ");
			movieTitle = sc.nextLine();

			System.out.println("\nEnter value:  ");
			movieValue = Integer.parseInt(sc.nextLine());

			System.out.println("\nEnter length: ");
			length = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Error! ID has to be an Integer, type register to try again.\n");
			return;
		}
		System.out.println("\nEnter rating: ");
		try {
			rating = sc.nextFloat();
			sc.nextLine();
		} catch (InputMismatchException e) {
			System.out.println("Error! Rating has to be 2 digits comma-separated (e.ghel 7,3), type register to try again.\n");
			sc.nextLine();
			return;
		}

		Movie newMovie = new Movie(movieID, movieTitle, movieValue, length, rating, inStock);
		productLibrary.addProduct(newMovie);

	}

	public void checkOutCommand(String[] arguments) {
		int ID;
		try {
			ID = Integer.parseInt(arguments[0]);
		} catch (Exception e) {
			System.out.println("Syntax error! Please enter a valid ID number\n");
			return;

		}
		boolean found = false;
		for (int i = 0; i < LibraryInterface.products.size(); i++) {
			if (LibraryInterface.products.get(i).getId() == ID) {
				customerLibrary.checkOutProduct(ID);
				found = true;
			}
		}
		if (!found) {
			System.out.println("Error! No product with ID " + ID + " is registered.\n");

		}
	}

	public void checkInCommand(String[] arguments) {
		int ID;
		try {
			ID = Integer.parseInt(arguments[0]);
		} catch (Exception e) {
			System.out.println("Syntax error! Please enter a valid ID number\n");
			return;
		}
		boolean found = false;
		for (int i = 0; i < LibraryInterface.products.size(); i++) {
			if (LibraryInterface.products.get(i).getId() == ID) {
				customerLibrary.checkInProduct(ID);
				found = true;
			}
		}
		if (!found) {
			System.out.println("Error! No product with ID " + ID + " is registered.\n");

		}
	}

	private void removeCommand(String[] arguments) {
		int ID;
		try {
			ID = Integer.parseInt(arguments[0]);
		} catch (Exception e) {
			System.out.println("Syntax error! Please enter a valid ID number\n");
			return;

		}

		productLibrary.removeProduct(ID);
		customerLibrary.removeCustomer(ID);

	}

	private void infoCommand(String[] arguments) {
		int ID;
		try {
			ID = Integer.parseInt(arguments[0]);
		} catch (Exception e) {
			System.out.println("Syntax error! Please enter a valid ID number\n");
			return;

		}
		productLibrary.productInfo(ID);
	}

	private void displayInventory() {

		if (LibraryInterface.products.isEmpty()) {
			System.out.println("Library is empty, type register to add a product.\n");
		} else {
			System.out.println(productLibrary);
		}
	}

	public void helpCommand() {
		System.out.println("The following commands are available: \n");
		System.out.println("'register' - Add a product.");
		System.out.println("'deregister' + (ID) - Remove a product from the library.");
		System.out.println("'list' - Show a list of products in the library.");
		System.out.println("'info' + (ID) - See full info about a product.");
		System.out.println("'checkout' + (ID) - Check out a product from the library to a customer.");
		System.out.println("'checkin' + (ID)- Return a borrowed product from a customer.");
		System.out.println("'quit' - Exit the program.\n");
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
		case "checkout":
			return Command.CHECKOUT;
		case "checkin":
			return Command.CHECKIN;
		case "help":
			return Command.HELP;
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