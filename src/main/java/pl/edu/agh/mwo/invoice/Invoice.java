package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
	private HashMap<Product, Integer> products = new HashMap<>();;

	public void addProduct(Product product) {
		this.addProduct(product, 1);
	}

	public void addProduct(Product product, Integer quantity) {
		if (quantity == 0) {
			throw new IllegalArgumentException("Product quantity cannot be zero");
		}
		if (quantity < 0) {
			throw new IllegalArgumentException("Product quantity cannot be negative");
		}
		this.products.put(product, quantity);

	}

	public BigDecimal getNetPrice() {
		BigDecimal sum = new BigDecimal(0);
		for(Product  product : this.products.keySet()) {
			Integer quantity = this.products.get(product);
			sum = sum.add(product.getPrice().multiply(new BigDecimal(quantity)));
		}
		return sum;
		//		return this.getGrossPrice().subtract(this.getNetPrice());
	}


	public BigDecimal getTax() {
		BigDecimal sum = new BigDecimal(0);
		for(Product  product : this.products.keySet()) {
			Integer quantity = this.products.get(product);
			sum = sum.add(product.getPriceWithTax().multiply(new BigDecimal(quantity)));
		}
		return sum.subtract(getNetPrice());
	}

	public BigDecimal getGrossPrice() {
		BigDecimal sum = new BigDecimal(0);
		for(Product  product : this.products.keySet()) {
			Integer quantity = this.products.get(product);
			sum = sum.add(product.getPriceWithTax().multiply(new BigDecimal(quantity)));
		}
		return sum;
	}
		
}
 