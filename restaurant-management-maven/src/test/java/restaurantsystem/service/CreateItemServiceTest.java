/* Copyright (C) 2024 Russarin Eaimrittikrai, Supithcha Jongphoemwatthanaphon,
 * Sasasuang Pattanakitjaroenchai, Chaninan Phetpangun, Runchida Ananartyasit,
 * Phacharaphan Chalitchaiya, Pimmada Chompurat - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MUICT Echo license.
 */

/*
 This file includes test suite: testCreateItem
*/
package restaurantsystem.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import restaurantsystem.model.Item;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class CreateItemServiceTest {
    private ItemService itemService;
    private File file;
    private final String filePath = "storage/item.txt";
    private final String initialContent = "Pizza,18.9,8\nBurger,12.5,5\nPizza,9.99,10\n";

    @Before
    public void setUp() throws Exception {
        itemService = new ItemService();
        file = new File(filePath);

        // Ensure the file exists
        if (!file.exists()) {
            file.createNewFile();
        }

        // Write initial data to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(initialContent);
        }
    }

    @After
    public void tearDown() throws Exception {
        // Restore the initial content after each test
        resetFileContent();
    }

    // Method to reset file content to initial values after each test
    private void resetFileContent() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Pizza,11.0,7");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Interface-based characteristic
    // Test Case T1 (C1b2, C2b2, C3b3, C4b3)
    // Creating an item with valid name, price, and quantity
    // createItem("Pizza", 20.99, 10)
    @Test
    public void testCreateItemValid() throws Exception {
        Item item = new Item("Pizza", 20.99, 10);
        itemService.create(item);
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        assertTrue(content.contains("Pizza,20.99,10"));
    }

    // Interface-based characteristic
    // Test Case T2 (C1b2, C2b1, C3b3, C4b3)
    // Creating an item with empty name, valid price, and quantity
    // createItem("", 9.99, 10)
    @Test
    public void testCreateItemEmptyName() throws Exception {
        Item item = new Item("", 9.99, 10);
        itemService.create(item);
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        assertFalse("An item with an empty name should not be created.",content.contains(",9.99,10"));
    }

    // Interface-based characteristic
    // Test Case T3 (C1b2, C2b2, C3b1, C4b3)
    // Creating an item with negative price
    // createItem("Pizza", -1.0, 10)
    @Test
    public void testCreateItemNegativePrice() throws Exception {
        Item item = new Item("Pizza", -1.0, 10);
        itemService.create(item);
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        assertFalse("An item with a negative price should not be created.",content.contains("Pizza,-1.0,10"));
    }

    // Interface-based characteristic
    // Test Case T4 (C1b2, C2b2, C3b2, C4b3)
    // Creating an item with zero price
    // createItem("Pizza", 0.0, 10)
    @Test
    public void testCreateItemZeroPrice() throws Exception {
        Item item = new Item("Pizza", 0.0, 10);
        itemService.create(item);
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        assertFalse("An item with zero price should not be created.",content.contains("Pizza,0.0,10"));
    }

    // Interface-based characteristic
    // Test Case T5 (C1b2, C2b2, C3b3, C4b1)
    // Creating an item with valid price but negative quantity
    // createItem("Pizza", 20.99, -1)
    @Test
    public void testCreateItemNegativeQuantity() throws Exception {
        Item item = new Item("Pizza", 20.99, -1);
        itemService.create(item);
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        assertFalse("An item with a negative quantity should not be created.",content.contains("Pizza,20.99,-1"));
    }

    // Interface-based characteristic
    // Test Case T6 (C1b2, C2b2, C3b3, C4b2)
    // Creating an item with valid price but zero quantity
    // createItem("Pizza", 20.99, 0)
    @Test
    public void testCreateItemZeroQuantity() throws Exception {
        Item item = new Item("Pizza", 20.99, 0);
        itemService.create(item);
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        assertFalse("An item with a zero quantity should not be created.",content.contains("Pizza,20.99,0"));
    }

    // Interface-based characteristic
    // Test Case T7 (C1b2, C2b2, C3b3, C4b4)
    // Creating an item with valid name and price but NaN quantity
    // createItem("Pizza", 20.99, NaN)
    @Test
    public void testCreateItemNaNQuantity() throws Exception {
        Item item = new Item("Pizza", 20.99, (int) Double.NaN);
        itemService.create(item);
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        assertFalse("An item with an invalid quantity should not be created.",content.contains("Pizza,20.99,NaN"));
    }

    // Interface-based characteristic
    // Test Case T8 (C1b2, C2b1, C3b2, C4b3)
    // Creating an item with empty name and zero price
    // createItem("", 0.0, 10)
    @Test
    public void testCreateItemEmptyNameZeroPrice() throws Exception {
        Item item = new Item("", 0.0, 10);
        itemService.create(item);
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        assertFalse("An item with an empty name and zero price should not be created.",content.contains(",0.0,10"));
    }

    // Interface-based characteristic
    // Test Case T9 (C1b2, C2b2, C3b4, C4b3)
    // Creating an item with NaN price and valid quantity
    // createItem("Pizza", NaN, 10)
    @Test
    public void testCreateItemNaNPrice() throws Exception {
        Item item = new Item("Pizza", Double.NaN, 10);
        itemService.create(item);
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        assertFalse("An item with an invalid price should not be created.",content.contains("Pizza,NaN,10"));
    }

    // Interface-based characteristic
    // Test Case T10 (C1b2, C2b2, C3b2, C4b1)
    // Creating an item with zero price and negative quantity
    // createItem("Pizza", 0.0, -1)
    @Test
    public void testCreateItemZeroPriceNegativeQuantity() throws Exception {
        Item item = new Item("Pizza", 0.0, -1);
        itemService.create(item);
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        assertFalse("An item with a zero price and negative quantity should not be created.",content.contains("Pizza,0.0,-1"));
    }

    // Interface-based characteristic
    // Test Case T11 (C1b2, C2b2, C3b2, C4b2)
    // Creating an item with zero price and zero quantity
    // createItem("Pizza", 0.0, 0)
    @Test
    public void testCreateItemZeroPriceZeroQuantity() throws Exception {
        Item item = new Item("Pizza", 0.0, 0);
        itemService.create(item);
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        assertFalse("An item with a zero price and quantity should not be created.",content.contains("Pizza,0.0,0"));
    }

    // Interface-based characteristic
    // Test Case T12 (C1b2, C2b2, C3b2, C4b4)
    // Creating an item with zero price and NaN quantity
    // createItem("Pizza", 0.0, NaN)
    @Test
    public void testCreateItemZeroPriceNaNQuantity() throws Exception {
        Item item = new Item("Pizza", 0.0, (int) Double.NaN);
        itemService.create(item);
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        assertFalse("An item with a zero price and invalid quantity should not be created.",content.contains("Pizza,0.0,NaN"));
    }

    // Functionality-based characteristic
    // Test Case T13 (C1b1)
    // Creating an item that already exists in the file (duplicate)
    // createItem("Pizza", 9.99, 10)
    @Test
    public void testCreateDuplicateItem() throws Exception {
        Item item = new Item("Pizza", 9.99, 10);
        itemService.create(item);

        // Verify that the duplicate item is added by checking that there are multiple instances of it
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        int count = content.split("Pizza,9.99,10").length - 1;  // Count occurrences of "Pizza,9.99,10"
        assertTrue("There should be more than one copy of the item 'Pizza' in the file", count > 1);
    }

    // Functionality-based characteristic
    // Test Case T14 (C1b2)
    // Creating a unique item that does not exist in the file (not duplicate)
    // createItem("Spaghetti", 12.0, 10)
    @Test
    public void testCreateUniqueItem() throws Exception {
        Item item = new Item("Spaghetti", 12.0, 10);
        itemService.create(item);

        // Verify that only one instance of the new item is in the file
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        int count = content.split("Spaghetti,12.0,10").length - 1;  // Count occurrences of "Spaghetti,12.0,10"
        assertEquals("There should be only one copy of the item 'Spaghetti' in the file", 1, count);
    }
}