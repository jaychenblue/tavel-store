package ca.concordia.soen343;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.concordia.soen343.model.CartItem;
import ca.concordia.soen343.repository.CartRepository;

public class CartRepositoryTest 
{
	@Test
	public void testAddItem() 
	{
		CartRepository UserCart = new CartRepository();
		CartItem Item = new CartItem(404, 5);
		
		UserCart.addItem(1234, Item);
		int itemQty = UserCart.getCartItemQty(1234, 404).getQty();
		assertEquals("Test single item added to cart", 5, itemQty);
	}
	
	
	@Test
	public void testAddItemMultiple() 
	{
		CartRepository UserCart = new CartRepository();
		
		for(int i = 0; i < 1500; i++)
		{
			CartItem Item = new CartItem(i, i+1);
			UserCart.addItem(1234, Item);
		}
		
		int itemQty = UserCart.getCartByUserId(1234).getItemsList().size();
		assertEquals("Test thousands of items added to cart", 1500, itemQty);
	}
}
