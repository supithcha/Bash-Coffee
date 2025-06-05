/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantsystem.service;

import restaurantsystem.component.item.UpdateItem;
import restaurantsystem.model.Item;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sopnopriyo
 */
public class ItemService {

    public ItemService() {
    }

    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream("storage/item.txt"))) {
            while (scanner.hasNextLine()) {
                String itemLine = scanner.nextLine();

                String itemInfo[] = itemLine.split(",");

                Item item = new Item(itemInfo[0], Double.parseDouble(itemInfo[1]),
                        Integer.parseInt(itemInfo[2]));

                items.add(item);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }

    public Item getItemByIndex(int index) {
        List<Item> listOfItem = getAll();

        if (listOfItem.size() >= index) {
            return listOfItem.get(index - 1);
        }

        return null;
    }

    public void create(Item item) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream("storage/item.txt", true))) {
            pw.println(item.getName() + "," + item.getPrice() + "," + item.getQuantity());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized boolean delete(String name) {

        List<Item> itemList = getAll();

        int indexToBeDeleted = -1;
        // find the item to be deleted
        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);

            if (item.getName().equalsIgnoreCase(name)) {
                indexToBeDeleted = i;
            }
        }

        if (indexToBeDeleted == -1) {
            return false;
        }
        itemList.remove(indexToBeDeleted);

        try {
            // Delete the entire file
            Files.delete(Paths.get("storage/item.txt"));
        } catch (IOException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Create a new file and write new data into the file
        try (PrintWriter pw = new PrintWriter(new FileOutputStream("storage/item.txt"))) {
            itemList.forEach(item -> {
                pw.println(item.getName() + "," + item.getPrice() + "," + item.getQuantity());
            });
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    public synchronized boolean update(String srcName, Item updatedItem) {
        List<Item> itemList = new ArrayList<>();

        // Read all the items
        try (Scanner scanner = new Scanner(new FileInputStream("storage/item.txt"))) {
            while (scanner.hasNextLine()) {
                String itemLine = scanner.nextLine();

                String itemInfo[] = itemLine.split(",");

                Item item = new Item(itemInfo[0], Double.parseDouble(itemInfo[1]),
                        Integer.parseInt(itemInfo[2]));
                itemList.add(item);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UpdateItem.class.getName()).log(Level.SEVERE, null, ex);
        }

        int itemIndexToUpdate = -1;

        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);

            if (item.getName().equalsIgnoreCase(srcName)) {
                itemIndexToUpdate = i;
            }
        }

        if (itemIndexToUpdate == -1) {
            return false;
        }

        itemList.set(itemIndexToUpdate, updatedItem);

        try {
            Files.delete(Paths.get("storage/item.txt"));
        } catch (IOException ex) {
            Logger.getLogger(UpdateItem.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (PrintWriter pw = new PrintWriter(new FileOutputStream("storage/item.txt"))) {
            itemList.forEach(item -> {
                pw.println(item.getName() + "," + item.getPrice() + "," + item.getQuantity());
            });
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UpdateItem.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    public synchronized boolean reduceItemQuantityByItemName(String itemName, int reduceNumber) {
        if (reduceNumber <= 0) {
            return false; // Invalid reduction number
        }

        List<Item> itemList = getAll();
        boolean itemFound = false;

        for (int i = 0; i < itemList.size(); i++) {
            Item item = itemList.get(i);

            if (item.getName().equalsIgnoreCase(itemName)) {
                if (item.getQuantity() < reduceNumber) {
                    return false; // Insufficient quantity
                }
                item.setQuantity(item.getQuantity() - reduceNumber); // Reduce the quantity
                itemList.set(i, item); // Update the list
                itemFound = true;
                break; // Exit the loop once item is found and updated
            }
        }

        if (!itemFound) {
            return false; // Item not found
        }

        // Rewrite the updated item list back to the file
        try {
            Files.delete(Paths.get("storage/item.txt")); // Delete the old file
        } catch (IOException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (PrintWriter pw = new PrintWriter(new FileOutputStream("storage/item.txt"))) {
            for (Item item : itemList) {
                pw.println(item.getName() + "," + item.getPrice() + "," + item.getQuantity());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true; // Reduction was successful
    }


    public void clearAll() {
        try {
            // Delete the entire file or overwrite it with an empty file
            Files.deleteIfExists(Paths.get("storage/item.txt"));
            // Alternatively, overwrite the file with no content to "clear" it
            new PrintWriter(new FileOutputStream("storage/item.txt")).close();
        } catch (IOException ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, "Error clearing all items", ex);
        }
    }
    public Item getItemByName(String name) {
        List<Item> items = getAll(); // Retrieve all items

        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item; // Return the item if the name matches
            }
        }

        return null; // Return null if no item is found with the given name
    }

}
