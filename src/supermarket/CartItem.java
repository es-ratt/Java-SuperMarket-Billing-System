package supermarket;

public class CartItem {

    // Product added to cart
    Product product;

    // Quantity of that product in cart
    int quantity;

    // Constructor to initialize cart item
    public CartItem(Product product, int quantity) {
        this.product = product;     // Set product
        this.quantity = quantity;    // Set quantity
    }

    // Calculate total price for this cart item
    public double getTotal() {
        return product.price * quantity;
    }
}