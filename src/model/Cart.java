package model;

import java.util.HashMap;

public class Cart {

	HashMap<String, Double> cartItems;

	public Cart() {
		cartItems = new HashMap<>();
	}

	@SuppressWarnings("rawtypes")
	public HashMap getCartItems() {
		return cartItems;
	}

	public void addToCart(String itemId, Double price) {
		cartItems.put(itemId, price);
	}
	public void deleteFromCart(String itemId){
        cartItems.remove(itemId);
    }

}
