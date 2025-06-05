/* Copyright (C) 2024 Russarin Eaimrittikrai, Supithcha Jongphoemwatthanaphon,
 * Sasasuang Pattanakitjaroenchai, Chaninan Phetpangun, Runchida Ananartyasit,
 * Phacharaphan Chalitchaiya, Pimmada Chompurat - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MUICT Echo license.
 */

// This file include test suites: testReduceItemQuantityByItemName
package restaurantsystem.service;

import org.junit.Before;
import org.junit.Test;
import restaurantsystem.model.Item;

import static org.junit.Assert.*;

public class ReduceItemQuantityByItemNameTest {
    private ItemService itemService;

    @Before
    public void setUp() {
        itemService = new ItemService();
        itemService.clearAll();

        // Adding some test items
        itemService.create(new Item("Pizza", 18.90, 10));
        itemService.create(new Item("Burger", 12.50, 5));
    }

    // Interface-based characteristic
    // Test Case T1 (C1b2, C2b2, C3b2)
    // Test for reducing quantity with valid item but reduce number is zero
    // reduceItemQuantityByItemName(Pizza, 0)
    @Test
    public void testReduceItemQuantity_ValidItem_QuantityZero() {
        String itemName = "Pizza";
        int reduceNumber = 0;
        assertFalse("The quantity should remain unchanged since reduction is 0",
                itemService.reduceItemQuantityByItemName(itemName, reduceNumber));
        assertEquals(10, itemService.getItemByName(itemName).getQuantity());
    }

    // Interface-based characteristic
    // Test Case T2 (C1b2, C2b2, C3b3)
    // Test for reducing quantity with valid item and valid reduction number
    // reduceItemQuantityByItemName(Pizza, 5)
    @Test
    public void testReduceItemQuantity_ValidItem_ValidQuantity() {
        String itemName = "Pizza";
        int reduceNumber = 5;
        assertTrue("The quantity should be reduced by 5",
                itemService.reduceItemQuantityByItemName(itemName, reduceNumber));
        assertEquals(5, itemService.getItemByName(itemName).getQuantity());
    }

    // Interface-based characteristic
    // Test Case T3 (C1b2, C2b2, C3b4)
    // Test for exceeding maximum available quantity.
    // This test ensures that when attempting to reduce the quantity of an item by a number larger than the available stock,
    // the operation fails and returns false.
    // reduceItemQuantityByItemName(Pizza, Integer.MAX_VALUE)
    @Test
    public void testReduceItemQuantity_Int() {
        String itemName = "Pizza";
        assertFalse("The quantity should not larger than the list size",
                itemService.reduceItemQuantityByItemName(itemName, Integer.MAX_VALUE));
    }

    // Functionality-based characteristic
    // Test Case T4 (C1b1, C2b1)
    // Test for valid reduction of 1 unit
    // reduceItemQuantityByItemName(Pizza, 1)
    @Test
    public void testReduceItemQuantity_ValidItem_One() {
        String itemName = "Pizza";
        int reduceNumber = 1;
        assertTrue("The quantity should be reduced by 1",
                itemService.reduceItemQuantityByItemName(itemName, reduceNumber));
        assertEquals(9, itemService.getItemByName(itemName).getQuantity());
    }

    // Functionality-based characteristic
    // T5 (C1b1, C2b2)
    // Test for reducing quantity of a valid item by 2
    // reduceItemQuantityByItemName(Pizza, 2)
    @Test
    public void testReduceItemQuantity_ValidItem_Two() {
        String itemName = "Pizza";
        int reduceNumber = 2;
        assertTrue("The quantity should be reduced by 2",
                itemService.reduceItemQuantityByItemName(itemName, reduceNumber));
        assertEquals(8, itemService.getItemByName(itemName).getQuantity());
    }

    // Functionality-based characteristic
    // T6 (C1b1, C2b3)
    // Test for reducing quantity greater than available
    // reduceItemQuantityByItemName(Pizza, 1)
    @Test
    public void testReduceItemQuantity_InsufficientQuantity() {
        String itemName = "Pizza";
        assertFalse("The quantity should not be reduced if it's insufficient",
                itemService.reduceItemQuantityByItemName(itemName, 1));
        assertEquals(10, itemService.getItemByName(itemName).getQuantity());
    }

    // Functionality-based characteristic
    //T7 (C1b2, C2b1)
    // Test for non-existent item with a valid reduction number
    // reduceItemQuantityByItemName(Banana, 1)
    @Test
    public void testReduceItemQuantity_NonExistentItem_One() {
        String itemName = "Banana";
        assertFalse("The item should not exist",
                itemService.reduceItemQuantityByItemName(itemName, 1));
        assertNull(itemService.getItemByName(itemName));
    }

    // Functionality-based characteristic
    // T8 (C1b2, C2b2)
    // Test for non-existent item with a different reduction number
    // reduceItemQuantityByItemName(Banana, 2)
    @Test
    public void testReduceItemQuantity_NonExistentItem_Two() {
        String itemName = "Banana";
        assertFalse("The item should not exist",
                itemService.reduceItemQuantityByItemName(itemName, 2));
        assertNull(itemService.getItemByName(itemName));
    }

    // Functionality-based characteristic
    // T9 (C1b2, C2b3)
    // Test for non-existent item, reducing by 1
    // reduceItemQuantityByItemName(Banana, 1)
    @Test
    public void testReduceItemQuantity_NonExistentItem_OneReduce() {
        String itemName = "Banana";
        assertFalse("The item should not exist",
                itemService.reduceItemQuantityByItemName(itemName, 1));
        assertNull(itemService.getItemByName(itemName));
    }
}
