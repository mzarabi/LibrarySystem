import java.io.*;
import java.util.*;

public class ProductLibrary implements LibraryInterface {

	public void addProduct(Product product) {

		for (Product items : products) {
			if (items.getId() == product.getId()) {
				System.out.println("Error: Product with ID " + product.getId() + " is already registered.\n");
				return;
			}
		}
		products.add(product);
		Collections.sort(products);
		writeProductToFile();
		System.out.println("Successfully registered " + product.getTitle() + "\n");

	}

	public void removeProduct(int id) {
		boolean found = false;
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == id) {
				System.out.println("Successfully deregistered " + products.get(i).getTitle() + "\n");
				products.remove(i);
				writeProductToFile();
				found = true;

			}
		}
		if (!found) {
			System.out.println("Error! No product with id " + id + " registered\n");
		}
	}

	public void productInfo(int id) {
		boolean found = false;

		for (Product product : products) {
			if (product.getId() == id) {
				System.out.println(product.printInfo());
				found = true;
			}

		}
		if (!found) {
			System.out.println("Error! No product with id " + id + " registered\n");
		}
	}

	public String toString() {
		String print = "";
		for (int i = 0; i < products.size(); i++) {
			Product product = products.get(i);
			print = print + product.toString();

		}
		return print;
	}

	public void writeProductToFile() {

		String filePath = "library.csv";
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(filePath);
		} catch (IOException e) {
			System.out.println("Could not create file");
		}
		for (Product products : products) {
			try {
				fileWriter.write(products.csvRecord());
			} catch (IOException e) {
				System.out.println("Could not write to file");
			}
		}
		try {
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Problem closing writer");
		}
	}

	public Product readFile() {
		FileReader fr = null;
		try {
			fr = new FileReader("library.csv");
		} catch (IOException e) {
			System.out.println("Could not find file");
		}
		Scanner scanner = new Scanner(fr);
		while (scanner.hasNextLine()) {
			String csvRecord = scanner.nextLine();
			if (csvRecord.contains(bookStamp)) {
				products.add(Book.parseProduct(csvRecord));
			} else if (csvRecord.contains(movieStamp)){
				products.add(Movie.parseProduct(csvRecord));
			}

		}
		return null;
	}

}