/* Copyright (C) 2024 Russarin Eaimrittikrai, Supithcha Jongphoemwatthanaphon,
 * Sasasuang Pattanakitjaroenchai, Chaninan Phetpangun, Runchida Ananartyasit,
 * Phacharaphan Chalitchaiya, Pimmada Chompurat - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MUICT Echo license.
 */

 /*
 This file includes test suites: testGetItemByIndex, testDeleteItem, testUpdateItem
 */

package restaurantsystem.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import restaurantsystem.model.Item;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class ItemServiceTest {

    private ItemService itemService;
    private static final String FILE_PATH = "storage/item.txt";
    private static final String INITIAL_CONTENT = "Pizza,11.0,7";

    @Before
    public void setUp() {
        itemService = new ItemService();
        itemService.clearAll();

        Item item1 = new Item("Pasta", 9.99, 10);
        Item item2 = new Item("Pizza", 18.90, 10);
        Item item3 = new Item("Spaghetti", 3.99, 10);
        Item item4 = new Item("Smoothie", 3.59, 10);

        itemService.create(item1);
        itemService.create(item2);
        itemService.create(item3);
        itemService.create(item4);
    }

    @After
    public void tearDown() throws IOException {
        resetFileContent();  // Reset file content after each test
    }

    private void resetFileContent() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(INITIAL_CONTENT);
        }
    }

    // Interface-based characteristic
    // Test Case T1 (C1b1)
    // Test with a negative index
    // getItemByIndex(-1)
    @Test
    public void testGetItemByNegativeIndex() {
        Item resultNegativeIndex = null;
        try {
            resultNegativeIndex = itemService.getItemByIndex(-1);
        } catch (IndexOutOfBoundsException ignored) {
            // Exception is ignored; testing if index is out of bounds and returns null
        }
        assertNull("Expected null for a negative index (out of bounds)", resultNegativeIndex);
    }

    // Interface-based characteristic
    // Test Case T2 (C1b2)
    // Test with an index zero
    // getItemByIndex(0)
    @Test
    public void testGetItemByZeroIndex() {
        Item resultZeroIndex = null;
        try {
            resultZeroIndex = itemService.getItemByIndex(0);
        } catch (IndexOutOfBoundsException ignored) {
            // Exception is ignored; testing if index zero returns null
        }
        assertNull("Expected null for an index of zero (out of bounds)", resultZeroIndex);
    }

    // Interface-based characteristic
    // Test Case T3 (C1b3)
    // Test with a positive index
    // getItemByIndex(1)
    @Test
    public void testGetItemByValidIndexOne() {
        Item resultIndex1 = itemService.getItemByIndex(1);
        Item expectedItem = new Item("Pasta", 9.99, 10);
        assertEquals("Expected first item name to match", expectedItem.getName(), resultIndex1.getName());
        assertEquals("Expected first item price to match", expectedItem.getPrice(), resultIndex1.getPrice(), 0.01);
        assertEquals("Expected first item quantity to match", expectedItem.getQuantity(), resultIndex1.getQuantity());
    }

    // Interface-based characteristic
    // Test Case T4 (C1b4)
    // Test with an extremely large index
    // getItemByIndex(Integer.MAX_VALUE)
    @Test
    public void testGetItemByExtremelyLargeIndex() {
        Item resultLargeIndex = itemService.getItemByIndex(Integer.MAX_VALUE);
        assertNull("Expected null for an extremely large index (out of bounds)", resultLargeIndex);
    }

    // Functionality-based characteristic
    // Test Case T5 (C1b1, C2b2)
    // Test with a valid index when the list is not empty
    // getItemByIndex(2)
    @Test
    public void testGetItemByValidIndexTwo() {
        Item resultIndex2 = itemService.getItemByIndex(2);
        Item expectedItem = new Item("Pizza", 18.90, 10);
        assertEquals("Expected second item name to match", expectedItem.getName(), resultIndex2.getName());
        assertEquals("Expected second item price to match", expectedItem.getPrice(), resultIndex2.getPrice(), 0.01);
        assertEquals("Expected second item quantity to match", expectedItem.getQuantity(), resultIndex2.getQuantity());
    }

    // Functionality-based characteristic
    // Test Case T6 (C1b2, C2b2)
    // Test with an index that exceeds the list size
    // getItemByIndex(10)
    @Test
    public void testGetItemByExceedingIndex() {
        Item resultOutOfBoundsIndex = itemService.getItemByIndex(10);
        assertNull("Expected null for an index exceeding the list size", resultOutOfBoundsIndex);
    }

    // Functionality-based characteristic
    // Test Case T7 (C1b2, C2b2)
    // Test with an empty file
    // getItemByIndex(1)
    @Test
    public void testGetItemByEmptyList() {
        itemService.clearAll();
        Item resultEmptyList = itemService.getItemByIndex(1);
        assertNull("Expected null when retrieving from an empty list", resultEmptyList);
    }

    // Interface-based characteristic
    // Test Case T1 (C1b1)
    // Test with an empty name
    // delete("")
    @Test
    public void testDeleteItemByNameIsEmpty() {
        itemService.clearAll();

        Item itemToDelete = new Item("Pizza", 8.99, 5);
        itemService.create(itemToDelete);

        boolean result = itemService.delete("");
        assertFalse("Expected delete() to return true even when name is empty, indicating deletion attempt completed", result);
    }

    // Interface-based characteristic
    // Test Case T2 (C1b2)
    // Test with a not empty name
    // delete("Pizza")

    // Functionality-based characteristic
    // Test Case T4 (C1b1, C2b2)
    // delete("Pizza")
    @Test
    public void testDeleteItemByNameIsNotEmpty() {
        itemService.clearAll();

        Item itemToDelete = new Item("Pizza", 8.99, 5);
        itemService.create(itemToDelete);

        boolean result = itemService.delete("Pizza");
        assertTrue("Expected item 'Pizza' to be deleted successfully", result);
    }

    // Functionality-based characteristic
    // Test case T3 (C1b1, C2b1)
    // delete(" Pizza ")
    @Test
    public void testDeleteItemWithWhitespaceMismatch() {
        itemService.clearAll();

        Item itemToDelete = new Item("Pizza", 9.99, 10);
        itemService.create(itemToDelete);

        boolean result = itemService.delete(" Pizza ");
        assertFalse("Expected delete() to return false, indicating failure to delete due to whitespace mismatch.", result);
    }

    // Functionality-based characteristic
    // Test case T5 (C1b2, C2b1)
    // delete(" Hamburger ")
    @Test
    public void testDeleteNonExistentItemWithWhitespaceMismatch() {
        itemService.clearAll();

        Item itemToCreate = new Item("Hamburger", 5.99, 8);
        itemService.create(itemToCreate);

        boolean result = itemService.delete(" Hamburger ");
        assertFalse("Expected delete() to return false, indicating failure to delete due to non-existence and whitespace mismatch.", result);
    }

    // Functionality-based characteristic
    // Test case T6 (C1b2, C2b2)
    // delete("Hamburger")
    @Test
    public void testDeleteNonExistentItemExactMatch() {
        itemService.clearAll();

        Item itemToCreate = new Item("Hamburger", 5.99, 8);
        itemService.create(itemToCreate);

        boolean result = itemService.delete("Hamburger");
        assertFalse("Expected delete() to return false, indicating failure to delete due to non-existence of the item.", result);
    }

    // Interface-based characteristic
    // Test Case T1 (C1b2, C2b2, C3b2, C4b3, C5b3)
    // Update non-null item with valid data → update should succeed
    // Item("Hamburger", 8.99, 10)

    //Functionality-based characteristic
    // Test Case T9 (C1b1, C2b1, C3b2)
    // Update with matching srcName and item in file → update should succeed
    // Item("Hamburger", 9.99, 10)
    @Test
    public void testUpdateItem() {
        Item originalItem = new Item("Hamburger", 8.99, 5);
        itemService.create(originalItem);

        Item updatedItem = new Item("Hamburger", 9.99, 10);
        boolean isUpdated = itemService.update("Hamburger", updatedItem);
        Assert.assertTrue("Item update failed", isUpdated);

        List<Item> allItems = itemService.getAll();
        Item resultItem = null;
        for (Item item : allItems) {
            if (item.getName().equals("Hamburger")) {
                resultItem = item;
                break;
            }
        }
        assertNotNull("Updated item not found", resultItem);
        assertEquals("Item name should remain the same", "Hamburger", resultItem.getName());
        assertEquals("Item price was not updated correctly", 9.99, resultItem.getPrice(), 0.01);
        assertEquals("Item quantity was not updated correctly", 10, resultItem.getQuantity());
    }

    // Interface-based characteristic
    // Test Case T1 (C1b2, C2b2, C3b2, C4b3, C5b3)
    // Update non-null item with valid data → update should succeed
    // Item("Hamburger", 8.99, 10)

    // Functionality-based characteristic
    // Test Case T10 (C1b1, C2b2, C3b2)
    // Update with matching srcName but non-matching item → update should fail (False)
    // Item("NonExistentItem", 10.0, 3)
    @Test
    public void testUpdateItem_NonMatchingItem() {
        Item originalItem = new Item("Hamburger", 8.99, 5);
        itemService.create(originalItem);

        Item updatedItem = new Item("NonExistentItem", 10.0, 3);
        boolean isUpdated = itemService.update("Hamburger", updatedItem);
        Assert.assertFalse("Update should fail if item names do not match", isUpdated);

        List<Item> allItems = itemService.getAll();
        Item resultItem = allItems.stream().filter(item -> item.getName().equals("Hamburger")).findFirst().orElse(null);

        assertNotNull("Original item should still exist after failed update", resultItem);
        assertEquals("Original item name should remain unchanged", "Hamburger", resultItem.getName());
        assertEquals("Original item price should remain unchanged", 8.99, resultItem.getPrice(), 0.01);
        assertEquals("Original item quantity should remain unchanged", 5, resultItem.getQuantity());
    }

    //Functionality-based characteristic
    // Test Case T11 (C1b2, C2b1, C3b2)
    // Update with non-existent srcName but matching item → update should fail (False)
    // Item("Pasta", 15.0, 5)
    @Test
    public void testUpdateItem_NonExistentSrcNameAndItem() {
        Item nonExistentItem = new Item("Pasta", 15.0, 5);
        boolean isUpdated = itemService.update("Chicken", nonExistentItem);
        Assert.assertFalse("Update should fail if srcName does not exist", isUpdated);
    }

    //Functionality-based characteristic
    // Test Case T12 (C1b2, C2b2, C3b2)
    // Update with non-existent srcName and item → update should fail (False)
    // Item("NonExistentItem", 10.0, 3)
    @Test
    public void testUpdateItem_NonExistentItem() {
        Item nonExistentItem = new Item("NonExistentItem", 10.0, 3);
        boolean isUpdated = itemService.update("Chicken", nonExistentItem);
        Assert.assertFalse("Update should fail if item does not exist", isUpdated);
    }

    // Interface-based characteristic
    // Test Case T2 (C1b2, C2b2, C3b1, C4b3, C5b3)
    // Update with empty name in updatedItem → update should fail
    // Item("", 9.99, 10)
    @Test
    public void testUpdateItem_EmptyNameInUpdatedItem() {
        Item originalItem = new Item("Hamburger", 8.99, 5);
        itemService.create(originalItem);

        Item emptyNameItem = new Item("", 9.99, 10);
        boolean isUpdated = itemService.update("Hamburger", emptyNameItem);
        Assert.assertFalse("Update should fail if updatedItem name is empty", isUpdated);
    }

    // Interface-based characteristic
    // Test Case T3 (C1b2, C2b2, C3b2, C4b1, C5b3)
    // Update with negative price in updatedItem → update should fail
    // Item("Hamburger", -9.99, 10)
    @Test
    public void testUpdateItem_NegativePriceInUpdatedItem() {
        Item originalItem = new Item("Hamburger", 8.99, 5);
        itemService.create(originalItem);

        Item negativePriceItem = new Item("Hamburger", -9.99, 5);
        boolean isUpdated = itemService.update("Hamburger", negativePriceItem);
        Assert.assertFalse("Update should fail if updatedItem has a negative price", isUpdated);
    }

    // Interface-based characteristic
    // Test Case T4 (C1b2, C2b2, C3b2, C4b2, C5b3)
    // Update with zero price in updatedItem → update should fail
    // Item("Hamburger", 0, 10)
    @Test
    public void testUpdateItem_ZeroPriceInUpdatedItem() {
        Item originalItem = new Item("Hamburger", 8.99, 5);
        itemService.create(originalItem);

        Item zeroPriceItem = new Item("Hamburger", 0, 10);
        boolean isUpdated = itemService.update("Hamburger", zeroPriceItem);
        Assert.assertFalse("Update should fail if updatedItem has a price of zero", isUpdated);

        List<Item> allItems = itemService.getAll();
        Item resultItem = allItems.stream().filter(item -> item.getName().equals("Hamburger")).findFirst().orElse(null);
        assertNotNull("Original item should still exist after failed update", resultItem);
        assertEquals("Original item name should remain unchanged", "Hamburger", resultItem.getName());
        assertEquals("Original item price should remain unchanged", 8.99, resultItem.getPrice(), 0.01);
        assertEquals("Original item quantity should remain unchanged", 5, resultItem.getQuantity());
    }

    // Interface-based characteristic
    // Test Case T5 (C1b2, C2b2, C3b2, C4b4, C5b3)
    // Update with NaN price in updatedItem → update should fail
    // Item("Hamburger", NaN, 10)
    @Test
    public void testUpdateItem_NaNPriceInUpdatedItem() {
        Item originalItem = new Item("Hamburger", 8.99, 5);
        itemService.create(originalItem);

        Item nanPriceItem = new Item("Hamburger", Double.NaN, 10);
        boolean isUpdated = itemService.update("Hamburger", nanPriceItem);
        Assert.assertFalse("Update should fail if updatedItem has NaN as price", isUpdated);
    }

    // Interface-based characteristic
    // Test Case T6 (C1b2, C2b2, C3b2, C4b3, C5b1)
    // Update with negative quantity in updatedItem → update should fail
    // Item("Hamburger", 9.99, -1)
    @Test
    public void testUpdateItem_NegativeQuantityInUpdatedItem() {
        Item originalItem = new Item("Hamburger", 8.99, 5);
        itemService.create(originalItem);

        Item negativeQuantityItem = new Item("Hamburger", 9.99, -1);
        boolean isUpdated = itemService.update("Hamburger", negativeQuantityItem);
        Assert.assertFalse("Update should fail if updatedItem has a negative quantity", isUpdated);
    }

    // Interface-based characteristic
    // Test Case T7 (C1b2, C2b2, C3b2, C4b3, C5b2)
    // Update with zero quantity in updatedItem → update should fail
    // Item("Hamburger", 9.99, 0)
    @Test
    public void testUpdateItem_ZeroQuantityInUpdatedItem() {
        Item originalItem = new Item("Hamburger", 8.99, 5);
        itemService.create(originalItem);

        Item zeroQuantityItem = new Item("Hamburger", 10.99, 0);
        boolean isUpdated = itemService.update("Hamburger", zeroQuantityItem);
        Assert.assertFalse("Update should fail if updatedItem has a quantity of zero", isUpdated);

        List<Item> allItems = itemService.getAll();
        Item resultItem = allItems.stream().filter(item -> item.getName().equals("Hamburger")).findFirst().orElse(null);

        assertNotNull("Original item should still exist after failed update", resultItem);
        assertEquals("Original item name should remain unchanged", "Hamburger", resultItem.getName());
        assertEquals("Original item price should remain unchanged", 8.99, resultItem.getPrice(), 0.01);
        assertEquals("Original item quantity should remain unchanged", 5, resultItem.getQuantity());
    }

    // Interface-based characteristic
    // Test Case T8 (C1b2, C2b2, C3b2, C4b3, C5b4)
    // Update with NaN quantity → update should fail
    // Item("Hamburger", 9.99, NaN)
    @Test
    public void testUpdateItem_NaNQuantityInUpdatedItem() {
        Item originalItem = new Item("Hamburger", 8.99, 5);
        itemService.create(originalItem);

        Item largeQuantityItem = new Item("Hamburger", 9.99, Integer.MAX_VALUE);
        boolean isUpdated = itemService.update("Hamburger", largeQuantityItem);
        Assert.assertFalse("Update should fail if updatedItem has an extremely large quantity (simulating NaN)", isUpdated);
    }

}

