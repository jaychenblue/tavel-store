package ca.concordia.soen343;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.web.bind.annotation.PathVariable;

import ca.concordia.soen343.repository.CartRepository;
import ca.concordia.soen343.controller.CartController;
import ca.concordia.soen343.model.Cart;
import ca.concordia.soen343.model.CartItem;
import ca.concordia.soen343.model.Product;

public class CartControllerTest 
{

	@Test
	public void testAddItemToCartNoItems()
	{
		CartController Controller = new CartController();
		CartItem Item = new CartItem(1, 0); 
		Cart UserCart = null;
		int itemQty = 0;
		
		try 
		{
			Controller.addItemToCart(1234, Item);
			UserCart = Controller.getCartById(1234);
			itemQty = UserCart.getItemsList().size();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
 
		assertEquals("Test of submitting a cart with no items in it (this should fail)", 1, itemQty);
	}
	
	@Test
	public void testAddItemToCartOneItem()
	{
		CartController Controller = new CartController();
		CartItem Item = new CartItem(1, 4);
		Cart UserCart = null;
		int itemQty = 0;
		
		try 
		{
			Controller.addItemToCart(1234, Item);
			UserCart = Controller.getCartById(1234);
			itemQty = UserCart.getItemsList().size();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
 
		assertEquals("Test of submitting a cart with only one item in it", 1, itemQty);
	}
	
	@Test
	public void testAddItemToCartManyItems() 
	{
		CartController Controller = new CartController();
		Cart UserCart = null;
		int itemQty = 0;
		
		for(int i=0; i<500; i++)
		{
			CartItem Item = new CartItem(i, 4+i);
			try 
			{
				Controller.addItemToCart(1234, Item);
				UserCart = Controller.getCartById(1234);
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		itemQty = UserCart.getItemsList().size();
 
		assertEquals("Test of submitting a cart with hundreds of items in it", 500, itemQty);
	}
}
