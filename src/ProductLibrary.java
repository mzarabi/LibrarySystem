import java.io.*;
import java.util.*;

public class ProductLibrary {

	ArrayList<Product> products;

	public ProductLibrary() {
		products = new ArrayList<Product>();

	}

	public void addProduct(Product product) throws IOException {

		for (Product items : products) {
			if (items.getId() == product.getId()) {
				System.out.println("Error: Product with ID " + product.getId() + " is already registered.");
				return;
			}
		}
		products.add(product);
		Collections.sort(products);
		writeBookToFile();
		System.out.println("Successfully registered " + product.getTitle());

	}

	public void removeProduct(int id) throws IOException {
		boolean found = false;
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == id) {
				System.out.println("Successfully deregistered " + products.get(i).getTitle());
				products.remove(i);
				writeBookToFile();
				found = true;

			}

		}if(!found) {
			System.out.println("Error! No product with id " + id + " registered");
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
			System.out.println("Error! No product with id " + id + " registered");
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

	public void writeBookToFile() throws IOException {

		String filePath = "library.csv";
		FileWriter fileWriter = new FileWriter(filePath);

		for (Product products : products) {
			fileWriter.write(products.csvRecord());
		}
		fileWriter.close();

	}

	public Product readFile() throws IOException {

		FileReader fr = new FileReader("library.csv");
		Scanner scanner = new Scanner(fr);
		while (scanner.hasNextLine()) {
			String csvRecord = scanner.nextLine();
			if (csvRecord.contains("book")) {
				products.add(Book.parseProduct(csvRecord));
			} else {
				products.add(Movie.parseProduct(csvRecord));
			}

		}

		return null;

	}
}