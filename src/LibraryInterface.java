import java.util.ArrayList;

public interface LibraryInterface{
	ArrayList<Product> products = new ArrayList<Product>();
	ArrayList<Customer> customers = new ArrayList<Customer>();
	
	public String inStock = "(in stock)";
	public String bookStamp = " (Book): ";
	public String movieStamp = " (Movie): ";
	
}
