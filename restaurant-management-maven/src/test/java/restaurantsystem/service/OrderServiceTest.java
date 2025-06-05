/* Copyright (C) 2024 Russarin Eaimrittikrai, Supithcha Jongphoemwatthanaphon,
 * Sasasuang Pattanakitjaroenchai, Chaninan Phetpangun, Runchida Ananartyasit,
 * Phacharaphan Chalitchaiya, Pimmada Chompurat - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MUICT Echo license.
*/

/*
 This file includes test suite: testAddToCart
*/

package restaurantsystem.service;

import org.junit.Before;
import org.junit.Test;
import restaurantsystem.model.CartItem;
import restaurantsystem.model.Item;

import java.util.List;

import static java.lang.Double.NaN;
import static org.junit.Assert.*;

public class OrderServiceTest {

    private OrderService orderService;

    @Before
    public void setUp() {
        orderService = new OrderService();
    }

    // Interface-based characteristic
    // Test Case T1 (C1b2, C2b2, C3b2, C4b2, C5b2, C6b2, C7b2)
    // Adding a non-null cartItem + item with 0 properties
    // CartItem(Item("Pizza", 0.00, 0), 0, 0.00)
    @Test
    public void testAddToCart_ZeroCartItem() {
        // Initialize a CartItem with zero price and quantity
        Item zeroItem = new Item("Pizza", 0.0, 0);
        CartItem zeroCartItem = new CartItem(zeroItem, 0, 0.0);

        // Get the cart size before adding the item
        int initialCartSize = orderService.getCart().getCartItems().size();

        // Add the item to the cart
        orderService.addToCart(zeroCartItem);

        // Get the cart size after attempting to add the item
        int finalCartSize = orderService.getCart().getCartItems().size();

        // Verify that the item was not added to the cart (size should remain the same)
        assertEquals("Cart size should not increase after adding an item with zero price and quantity",
                initialCartSize, finalCartSize);
    }

    // Interface-based characteristic
    // Test Case T2 (C1b2, C1b2, C3b3, C4b3, C5b3, C6b3, C7b2)
    // Adding a non-null cartItem + valid item
    // CartItem(Item("Pizza", 10.00, 1), 1, 10.00)
    @Test
    public void testAddToCart_ValidCartItem() {
        Item validItem = new Item("Pizza", 10.0, 1);
        CartItem validCartItem = new CartItem(validItem, 1, 10.0);

        // Get the cart size before adding the item
        int initialCartSize = orderService.getCart().getCartItems().size();

        // Add the valid item to the cart
        orderService.addToCart(validCartItem);

        // Get the cart size after attempting to add the item
        int finalCartSize = orderService.getCart().getCartItems().size();

        // Verify that the item was added to the cart
        List<CartItem> cartItems = orderService.getCart().getCartItems();
        assertTrue("Valid item should be added to the cart",
                cartItems.stream().anyMatch(item -> item.getItem().getName().equals("Pizza")));

        // Verify that the item was added to the cart (size should increase by 1)
        assertEquals("Cart size should be increased after adding an item",
                initialCartSize + 1, finalCartSize);
    }


    // Interface-based characteristic
    // Test Case T3 (C1b2, C1b2, C3b4, C4b4, C5b4, C6b4, C7b2)
    // Attempting to add a CartItem and item with NaN values of price and quantity
    // CartItem(Item("Pizza", NaN, NaN), NaN, NaN)
    @Test
    public void testAddToCart_NullCartItem() {
        Item nanItem = new Item("Pizza", NaN, (int) NaN);
        CartItem nanCartItem = new CartItem(nanItem, (int) NaN, NaN);

        // Get the cart size before attempting to add
        int initialCartSize = orderService.getCart().getCartItems().size();

        // Attempt to add the null item
        orderService.addToCart(nanCartItem);

        // Get the cart size after attempting to add
        int finalCartSize = orderService.getCart().getCartItems().size();

        // Assert that the cart size did not increase
        assertEquals("Cart size should not change after attempting to add a invalid cartItem and item",
                initialCartSize, finalCartSize);
    }

    // Functionality-based characteristic
    // Test Case T4 (C1b2, C2b1)
    // Adding a CartItem and an item with valid values of price and quantity
    // CartItem(Item("Pizza", 10.0, 5), 10, 100.0)
    @Test
    public void testAddToCart_validPriceAndQuantity1() {

        Item validItem = new Item("Pizza", 10.0, 5);
        CartItem validCartItem = new CartItem(validItem, 10, 100.0);

        // Get the cart size before attempting to add
        int initialCartSize = orderService.getCart().getCartItems().size();

        // Attempt to add the invalid item
        orderService.addToCart(validCartItem);

        // Get the cart size after attempting to add
        int finalCartSize = orderService.getCart().getCartItems().size();

        // Assert that the cart size did not increase
        assertEquals("Cart size should change after adding a valid item",
                initialCartSize + 1, finalCartSize);
    }

    // Functionality-based characteristic
    // Test Case T5 (C1b1, C2b1)
    // Adding a CartItem and an item with valid values of price and quantity
    // CartItem(Item("Pizza", 10.0, 10), 5, 50.0)
    @Test
    public void testAddToCart_validPriceAndQuantity2() {

        Item validItem = new Item("Pizza", 10.0, 10);
        CartItem validCartItem = new CartItem(validItem, 5, 50.0);

        // Get the cart size before attempting to add
        int initialCartSize = orderService.getCart().getCartItems().size();

        // Attempt to add the invalid item
        orderService.addToCart(validCartItem);

        // Get the cart size after attempting to add
        int finalCartSize = orderService.getCart().getCartItems().size();

        // Assert that the cart size did not increase
        assertEquals("Cart size should change after adding a valid item",
                initialCartSize + 1, finalCartSize);
    }

    // Functionality-based characteristic
    // Test Case T6 (C1b3, C2b1)
    // Adding a CartItem and an item with invalid values of price and quantity
    // CartItem(Item("Pizza", 10.0, 5), 10, 100.0)
    @Test
    public void testAddToCart_invalidPriceAndQuantity1() {

        Item invalidItem = new Item("Pizza", 10.0, 5);
        CartItem invalidCartItem = new CartItem(invalidItem, 10, 100.0);

        // Get the cart size before attempting to add
        int initialCartSize = orderService.getCart().getCartItems().size();

        // Attempt to add the invalid item
        orderService.addToCart(invalidCartItem);

        // Get the cart size after attempting to add
        int finalCartSize = orderService.getCart().getCartItems().size();

        // Assert that the cart size did not increase
        assertEquals("Cart size should not change after adding an invalid CartItem quantity",
                initialCartSize, finalCartSize);
    }

    // Functionality-based characteristic
    // Test Case T7 (C1b2, C2b2)
    // Adding a CartItem and an item with invalid values of price and quantity
    // CartItem(Item("Pizza", 10.0, 5), 5, 1.0)
    @Test
    public void testAddToCart_invalidPriceAndQuantity2() {

        Item invalidItem = new Item("Pizza", 10.0, 5);
        CartItem invalidCartItem = new CartItem(invalidItem, 5, 1.0); // Price is intentionally incorrect

        // Get the cart size before attempting to add
        int initialCartSize = orderService.getCart().getCartItems().size();

        // Attempt to add the invalid item
        orderService.addToCart(invalidCartItem);

        // Get the cart size after attempting to add
        int finalCartSize = orderService.getCart().getCartItems().size();

        // Assert that the cart size did not increase
        assertEquals("Cart size should not change after adding an invalid CartItem price",
                initialCartSize, finalCartSize);
    }
}