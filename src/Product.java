
public class Product implements ProductInterface, Comparable<Product> {
	protected int id;
	protected String title;
	protected int value;

	public Product(int id, String title, int value) {
		super();
		this.id = id;
		this.title = title;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String printInfo() {
		return String.format(id + " (type): " + title + "." + " (in stock)" + "\n");

	}

	public int compareTo(Product product) {
		return Integer.compare(id, product.id);
	}

	@Override
	public String csvRecord() {

		return null;
	}

}