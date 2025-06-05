# 2024-Bash-Coffee

# Team Members
- 6488021 Russarin Eaimrittikrai
- 6488045 Supithcha Jongphoemwatthanaphon
- 6488052 Sasasuang Pattanakitjaroenchai
- 6488061 Chaninan Phetpangun
- 6488184 Runchida Ananartyasit
- 6488203 Phacharaphan Chalitchaiya
- 6488204 Pimmada Chompurat

# Project 2
## Bash Coffee Shop - Frontend

This repository contains the frontend code for the Bash Coffee Shop web application, which provides customers with an interactive and user-friendly interface for viewing, searching, filtering, and adding items to their cart.

---

### Linked Repositories

The **Bash Coffee Shop** project is divided into two repositories:  
1. **Frontend Repository:** [Bash Coffee Shop Frontend](https://github.com/rsrfay/Bash-Frontend/tree/Backup-Echo)
- This frontend is developed by Echo and Noppo Group
    - To view test documents and safely run the test of Echo group, please use branch `Backup-Echo`
2. **Backend Repository:** [Bash Coffee Shop Backend](https://github.com/supithcha/BashCoffee-backend)
- This backend is developed by Chicky Group
---

### Project Overview

This project aims to modernize the ordering system at Bash Coffee Shop by replacing manual ordering with a dynamic web-based menu. The web application allows customers to:
- Browse and filter menu items.
- Customize products with add-ons.
- Add selected products to the cart.
- Sort menu items by price.
- Purchase using a QR code.

---

### Key Features

- **Search Functionality:** Users can search for specific menu items.
- **Filter Functionality:** Users can filter menu items by category and hot/cold options.
- **Sorting Options:** Menu items can be sorted by price (low-to-high or high-to-low).
- **Customization:** Add-ons can be selected or omitted for beverages.
- **Dynamic Cart:** Items can be added to the cart with calculated pricing.
- **Responsive Design:** Mobile-first approach for optimal user experience.

---

# Project 1
This project designed unit test cases for [Restaurant-Management-System](https://github.com/sopnopriyo/restaurant-management-system). The following contents are documented explanations of each test case. All of the test suites are located in the folder `restaurant-management-maven/src/test/java`

There is a total of 10 test suites:
1. testCreateItem
2. testDeleteItem
3. testUpdateItem
4. testReduceItemQuantityByItemName
5. testGetItemByIndex
6. testCreateLabour
7. testDeleteLabour
8. testUpdateLabour
9. testAddToCart
10. testLogin

## Item Management

### Test Suite 1: testCreateItem

- **Goal**: Test creating a new item with its name, price, and quantity

#### Interface-based characteristic

1. **Identify testable functions**

    - Testable function: `create()`

2. **Identify parameters, return types, return values, and exceptional behavior**

   - Parameters: `Item item`
   - Return type: `void`
   - Return value: -
   - Exceptional behavior: 
      - Catches `FileNotFoundException` if the file is not found or inaccessible, and logs an error message using the logger.

3. **Model the input domain**

   - **Develop characteristics**:

     - `C1`: item is null
     - `C2`: Name field of the item is empty
     - `C3`: Value of the price field of the item
     - `C4`: Value of the quantity field of the item

   - **Partition characteristic**

     | Characteristic                                 | b1           | b2           | b3           | b4                       |
     | ---------------------------------------------- | ------------ | ------------ | ------------ | ------------------------ |
     | `C1 = item is null`                            | True         | False        |              |                          |
     | `C2 = Name field of the item is empty`         | True         | False        |              |                          |
     | `C3 = Value of the price field of the item`    | Price < 0    | Price = 0    | Price > 0    | Price is not a number    |
     | `C4 = Value of the quantity field of the item` | Quantity < 0 | Quantity = 0 | Quantity > 0 | Quantity is not a number |

   - **Identify (possible) value**

     | Characteristic                                 | b1           | b2           | b3           | b4                       |
     | ---------------------------------------------- | ------------ | ------------ | ------------ | ------------------------ |
     | `C1 = item is null`                            | null         | Item("Pasta", 9.99, 10)        |              |                          |
     | `C2 = Name field of the item is empty`         | Item("", 9.99, 10)| Item("Shampoo", 20, 15)        |              |                          |
     | `C3 = Value of the price field of the item`    | Item("Pizza", -10, 10)    | Item("Pizza", 0, 10)    | Item("Pizza", 20.99, 10)    | Item("Pizza",NaN, 10)    |
     | `C4 = Value of the quantity field of the item` | Item("Pizza", 19.99, -1) | Item("Pizza", 19.99, 0) | Item("Pizza", 19.99, 1) | Item("Pizza", 19.99, NaN) |

4. **Combine partitions to define test requirements**

   - **MBCC Approach**

     - Base Choice = (C1b2, C2b2, C3b3, C4b3), (C1b2, C2b2, C3b2, C4b3)
     - Number of test cases = 2 + [ (2*(2-1)) + (2*(2-1)) + (2*(4-2)) + (2*(4-1)) ] = 16
       - (C1b2, C2b2, C3b3, C4b3) = Item("Pizza", 20.99, 10)
       - (C1b1, C2b2, C3b3, C4b3) = Infeasible
       - (C1b2, C2b1, C3b3, C4b3) = Item("", 9.99, 10)
       - (C1b2, C2b2, C3b1, C4b3) = Item("Pizza", -1.0, 10) (duplicate)
       - (C1b2, C2b2, C3b2, C4b3) = Item("Pizza", 0.0, 10) (duplicate)
       - (C1b2, C2b2, C3b3, C4b1) = Item("Pizza", 20.99, -1)
       - (C1b2, C2b2, C3b3, C4b2) = Item("Pizza", 20.99, 0.0)
       - (C1b2, C2b2, C3b3, C4b4) = Item("Pizza", 20.99, NaN)
       - (C1b2, C2b2, C3b2, C4b3) = Item("Pizza", 0.0, 10) (duplicate)
       - (C1b1, C2b2, C3b2, C4b3) = Infeasible
       - (C1b2, C2b1, C3b2, C4b3) = Item("", 0.0, 10)
       - (C1b2, C2b2, C3b1, C4b3) = Item("Pizza", -1.0, 10) (duplicate)
       - (C1b2, C2b2, C3b4, C4b3) = Item("Pizza", NaN, 10)
       - (C1b2, C2b2, C3b2, C4b1) =  Item("Pizza", 0.0, -1)
       - (C1b2, C2b2, C3b2, C4b2) = Item("Pizza", 0.0, 0.0)
       - (C1b2, C2b2, C3b2, C4b4) = Item("Pizza", 0.0, NaN)

5. **Derive test values**

     | Test Case | Item | Name | Price | Quantity | Expected Result                           |
     | --------- | --------- | ----- | -------- | ----- | --------------------------- |
     | T1 (C1b2, C2b2, C3b3, C4b3)   | Item("Pizza", 20.99, 10)| "Pizza"   | 20.99 | 10       | Item created in `storage/item.txt`        |
     | T2 (C1b2, C2b1, C3b3, C4b3)   | Item("", 9.99, 10)| ""   | 9.99 | 10       | Item is not created in `storage/item.txt`        |
     | T3 (C1b2, C2b2, C3b1, C4b3)   | Item("Pizza", -1.0, 10)| "Pizza"   | -1.0 | 10       | Item is not created in `storage/item.txt`        |
     | T4 (C1b2, C2b2, C3b2, C4b3)   | Item("Pizza", 0.0, 10)| "Pizza"   | 0.0 | 10       | Item is not created in `storage/item.txt`        |
     | T5 (C1b2, C2b2, C3b3, C4b1)   | Item("Pizza", 20.99, -1)| "Pizza"   | 20.99 | -1       | Item is not created in `storage/item.txt`        |
     | T6 (C1b2, C2b2, C3b3, C4b2)   | Item("Pizza", 20.99, 0.0)| "Pizza"   | 20.99 | 0       | Item is not created in `storage/item.txt`        |
     | T7 (C1b2, C2b2, C3b3, C4b4)   | Item("Pizza", 20.99, NaN)| "Pizza"   | 20.99 | NaN       | Item is not created in `storage/item.txt`        |
     | T8 (C1b2, C2b1, C3b2, C4b3)   | Item("", 0.0, 10)| ""   | 0.0 | 10       | Item is not created in `storage/item.txt`        |
     | T9 (C1b2, C2b2, C3b4, C4b3)   | Item("Pizza", NaN, 10)| "Pizza"   | NaN | 10       | Item is not created in `storage/item.txt`        |
     | T10 (C1b2, C2b2, C3b2, C4b1)   | Item("Pizza", 0.0, -1)| "Pizza"   | 0.0 | -1       | Item is not created in `storage/item.txt`        |
     | T11 (C1b2, C2b2, C3b2, C4b2)   | Item("Pizza", 0.0, 0)| "Pizza"   | 0.0 | 0       | Item is not created in `storage/item.txt`        |
     | T12 (C1b2, C2b1, C3b2, C4b3)   | Item("Pizza", 0.0, NaN)| "Pizza"   | 0.0 |NaN       | Item is not created in `storage/item.txt`        |
   
#### Functionality-based characteristic

1. **Identify testable functions**

   - Testable function: `create()`

2. **Identify parameters, return types, return values, and exceptional behavior**

   - Parameters: `Item item`
   - Return type: `void`
   - Return value: -
   - Exceptional behavior: 
      - Catches `FileNotFoundException` if the file is not found or inaccessible, and logs an error message using the logger.

3. **Model the input domain**

   - **Develop characteristics**:

     - `C1`: The created items already exists in the file

   - **Partition characteristic**

     | Characteristic  |      b1           | b2           | 
     | ------------ | ------------ | -------------- |
     | `C1 = The created items already exists in the file`                            | True (The created item already exists)        | False  (The created item doesn’t exist)
      |            

   - **Identify (possible) value**

     | Characteristic  |      b1           | b2           | 
     | ------------ | ------------ | -------------- |
     | `C1 = The created items already exists in the file`                            | Item(“Pizza”,9.99,10) (The item that is already in the file) | Item(“Spaghetti”,12.0,10) (The item is not in the file) | 

4. **Combine partitions to define test requirements**

   - **ACoC Approach**

     - Number of test cases = 2
       - (C1b1) = Item(“Pizza”,9.99,10)
       - (C1b2) = Item(“Spaghetti”,12.0,10)

5. **Derive test values**

     | Test Case  |      Item           | Expected Result           | 
     | ------------ | ------------ | -------------- |
     | T1 (C1b1)        | Item(“Pizza”,9.99,10) | There is more than 1 copy of this item in `storage/item.txt` |
     | T2 (C1b2) | Item(“Spaghetti”,12.0,10) | There is only 1 item in `storage/item.txt` | 

---

#### All Test Cases for `testCreateItem`

| Test Case | Item                        | Name    | Price | Quantity | Expected Result                          |
|-----------|-----------------------------|---------|-------|----------|------------------------------------------|
| T1        | Item("Pizza", 20.99, 10)    | "Pizza" | 20.99 | 10       | Item created in `storage/item.txt`       |
| T2        | Item("", 9.99, 10)          | ""      | 9.99  | 10       | Item is not created in `storage/item.txt` |
| T3        | Item("Pizza", -1.0, 10)     | "Pizza" | -1.0  | 10       | Item is not created in `storage/item.txt` |
| T4        | Item("Pizza", 0.0, 10)      | "Pizza" | 0.0   | 10       | Item is not created in `storage/item.txt` |
| T5        | Item("Pizza", 20.99, -1)    | "Pizza" | 20.99 | -1       | Item is not created in `storage/item.txt` |
| T6        | Item("Pizza", 20.99, 0.0)   | "Pizza" | 20.99 | 0.0      | Item is not created in `storage/item.txt` |
| T7        | Item("Pizza", 20.99, NaN)   | "Pizza" | 20.99 | NaN      | Item is not created in `storage/item.txt` |
| T8        | Item("", 0.0, 10)           | ""      | 0.0   | 10       | Item is not created in `storage/item.txt` |
| T9        | Item("Pizza", NaN, 10)      | "Pizza" | NaN   | 10       | Item is not created in `storage/item.txt` |
| T10       | Item("Pizza", 0.0, -1)      | "Pizza" | 0.0   | -1       | Item is not created in `storage/item.txt` |
| T11       | Item("Pizza", 0.0, 0.0)     | "Pizza" | 0.0   | 0.0      | Item is not created in `storage/item.txt` |
| T12       | Item("Pizza", 0.0, NaN)     | "Pizza" | 0.0   | NaN      | Item is not created in `storage/item.txt` |
| T13       | Item("Pizza", 9.99, 10)     | "Pizza" | 9.99  | 10       | There is more than 1 copy of this item in `storage/item.txt` |
| T14       | Item("Spaghetti", 12.0, 10) | "Spaghetti" | 12.0 | 10   | There is only 1 item in `storage/item.txt` |

---

### Test Suite 2: testDeleteItem 

- **Goal**: Test deleting an item by name from `storage/item.txt`

#### Interface-based characteristic

1. **Identify testable functions**

   - Testable function: `delete()`

2. **Identify parameters, return types, return values, and exceptional behavior**

   - Parameters: `String name`
   - Return type: `boolean`
   - Return Value: 
      - `True`: When the item is successfully deleted.
      - `False`: When no matching item is found.
   - Exceptional behavior: 
      - `IOException`: If there is an issue with deleting the file using Files. delete().
      - `FileNotFoundException`: If the file storage/item.txt cannot be found during the file update process.

3. **Model the input domain**

   - **Develop characteristics**:

     - `C1`: Name is an empty string.

   - **Partition characteristic**

      | Characteristic                 | b1                  | b2                       |
      | ------------------------------ | ------------------- | ------------------------ |
      | `C1 = name is an empty string` | True (Empty String) | False (Non-Empty String) |

   - **Identify (possible) value**

      | Characteristic                 | b1                  | b2                       |
      | ------------------------------ | ------------------- | ------------------------ |
      | `C1 = name is an empty string` | `""`                | `"Pizza"`                |


4. **Combine partitions to define test requirements**

    - **ACoC Approach**

      - Number of test cases = 2
      - (C1b1) = `""`
      - (C1b2) = `"Pizza"`

5. **Derive test values**

      | Test Case | Name    | Expected Result                     |
      | --------- | ------- | ----------------------------------- |
      | T1 (C1b1) | `""`    | False (Cannot delete with empty ID) |
      | T2 (C1b2) | `"Pizza"` | True (Item is deleted successfully) |

#### Functionality-based characteristic

1. **Identify testable functions**

   - Testable function: `delete()`

2. **Identify parameters, return types, return values, and exceptional behavior**

   - Parameters: `String name`
   - Return type: `boolean` 
   - Return value:
      - `true`: When the item is successfully deleted.
      - `false`: When no matching item is found.
   - Exceptional behavior:
      - `IOException`: If there is an issue with deleting the file using `Files.delete()`.
      - `FileNotFoundException`: If the file `storage/item.txt` cannot be found during the file update process.

3. **Model the input domain**

   - **Develop characteristics**

     - C1: `Item’s name` exists in the file
     - C2: Whitespace in `item’s name`

   - **Partition characteristic**

     | Characteristic | b1              | b2              |
     |----------------|-----------------|-----------------|
     | C1 = `Item’s name` exists in the file | True  | False  |
     | C2 = Whitespace in `item’s name` | True  | False  |

   - **Identify (possible) values**

     | Characteristic | b1       | b2        |
     |----------------|----------|-----------|
     | C1 = `Item’s name` exists in the file | `"Pizza"` (The item’s name that is currently in the file) | `"Hamburger"` (The item’s name that is not currently in the file) |
     | C2 = Whitespace in `item’s name` | `" Pizza "` | `"Pizza"` |

4. **Combine partitions to define test requirements**

   - **PWC Approach**

     - Test requirements: number of tests = 2*2 = 4
     - (C1b1, C2b1) = `delete(" Pizza ")`
     - (C1b1, C2b2) = `delete("Pizza")`
     - (C1b2, C2b1) = `delete(" Pizza ")`
     - (C1b2, C2b2) = `delete("Pizza")`
     - Eliminate redundant tests and infeasible tests

5. **Derive test values**

      | Test case        | Name         | Item’s name | Expected Result                                  |
      |------------------|--------------|-------------|--------------------------------------------------|
      | T1 (C1b1, C2b1)  | `"Pizza"`    | `delete(" Pizza ")` | `False` |
      | T2 (C1b1, C2b2)  | `"Pizza"`    | `delete("Pizza")` | `True` |
      | T3 (C1b2, C2b1)  | `"Hamburger"`| `delete(" Hamburger ")` | `False`                                            |
      | T4 (C1b2, C2b2)  | `"Hamburger"`| `delete("Hamburger")` | `False`                                            |

---

#### All Test Cases for `testDeleteItem`

| Test case          | Name       | Item’s name  | Expected Result                                             |
|--------------------|------------|--------------|-------------------------------------------------------------|
| T1 (C1b1)          | `""`       | `delete("Pizza")` | `False`                      |
| T2 (C1b2)          | `"Pizza"`  | `delete("Pizza")` | `True`                |
| T3 (C1b1, C2b1)    | `"Pizza"`  | `delete(" Pizza ")` | `False` (Item with extra whitespace does not match)           |
| T4 (C1b1, C2b2)    | `"Pizza"`  | `delete("Pizza")` | `True` (Item "Pizza" matches and is deleted successfully)     |
| T5 (C1b2, C2b1)    | `"Hamburger"` | `delete(" Hamburger ")` | `False`           |
| T6 (C1b2, C2b2)    | `"Hamburger"` | `delete("Hamburger")` | `False`         |

---

### Test Suite 3: testUpdateItem

- **Goal**: Test updating an item’s information by inputting the name of the item to be updated.

#### Interface-based characteristic

1. **Identify testable functions**

    - Testable functions: `update()`

2. **Identify parameters, return types, return values, and exceptional behavior**

  - Parameters: `srcName`, `updatedItem`
  - Return type: `boolean`
  - Return value:
      - `true`: if the item was found and updated successfully.
      - `false`: if the item was not found or update operation failed.
  - Exceptional behavior:
      - `FileNotFoundException`: If the item.txt file is not found.
      - `IOException`: During file operations (e.g., deletion, writing).
      - `NumberFormatException`: If `itemInfo` values cannot be parsed to appropriate data types.

3. **Model the input domain**

    - **Develop characteristics**

      - C1: `srcName` is empty.
      - C2: `item` is null.
      - C3: Name field of the `item` is empty.
      - C4: Value of the price field of the `item`.
      - C5: Value of the quantity field of the `item`.

- **Partition characteristic**

  | Characteristic | b1  | b2 | b3 | b4 |
  |----------------|-----|----|----|----|
  | C1 = `srcName` is empty | True | False | | |
  | C2 = `item` is null | True | False | | |
  | C3 = Name field of the `item` is empty | True | False | | |
  | C4 = Value of the price field of the `item` | Price < 0 | Price = 0 | Price > 0 | Price is not a number |
  | C5 = Value of the quantity field of the `item` | Quantity < 0 | Quantity = 0 | Quantity > 0 | Quantity is not a number |

- **Identify (possible) values**

  | Characteristic | b1  | b2 | b3 | b4 |
  |----------------|-----|----|----|----|
  | C1 = `srcName` is empty | `""` | `"Hamburger"` | | |
  | C2 = `item` is null | `null` | `Item("Hamburger", 9.99, 10)` | | |
  | C3 = Name field of the `item` is empty | `Item("", 9.99, 10)` | `Item("Shampoo", 20, 15)` | | |
  | C4 = Value of the price field of the `item` | `Item("Hamburger", -10, 10)` | `Item("Hamburger", 0, 10)` | `Item("Hamburger", 20.99, 10)` | `Item("Hamburger", NaN, 10)` |
  | C5 = Value of the quantity field of the `item` | `Item("Hamburger", 19.99, -1)` | `Item("Hamburger", 19.99, 0)` | `Item("Hamburger", 19.99, 1)` | `Item("Hamburger", 19.99, NaN)` |

4. **Combine partitions to define test requirements**

    - **BCC Approach**

      - Base Choice = (C1b2, C2b2, C3b2, C4b3, C5b3)
      - Number of test cases: 
      - `1 + [(2-1) + (2-1) + (2-1) + (4-1) + (4-1)] = 10`
        - (C1b2, C2b2, C3b2, C4b3, C5b3) = `Item("Hamburger", 9.99, 10)`
        - (C1b1, C2b2, C3b2, C4b3, C5b3) = Infeasible
        - (C1b2, C2b1, C3b2, C4b3, C5b3) = Infeasible
        - (C1b2, C2b2, C3b1, C4b3, C5b3) = `Item("", 9.99, 10)`
        - (C1b2, C2b2, C3b2, C4b1, C5b3) = `Item("Hamburger", -9.99, 10)`
        - (C1b2, C2b2, C3b2, C4b2, C5b3) = `Item("Hamburger", 0, 10)`
        - (C1b2, C2b2, C3b2, C4b4, C5b3) = `Item("Hamburger", NaN, 10)`
        - (C1b2, C2b2, C3b2, C4b3, C5b1) = `Item("Hamburger", 9.99, -1)`
        - (C1b2, C2b2, C3b2, C4b3, C5b2) = `Item("Hamburger", 9.99, 0)`
        - (C1b2, C2b2, C3b2, C4b3, C5b4) = `Item("Hamburger", 9.99, NaN)`


5. **Derive test values**

      | Test Case                                      | Src Name   | Item                  | Name        | Price | Quantity | Expected Result                       |
      |------------------------------------------------|------------|-----------------------|-------------|-------|----------|---------------------------------------|
      | T1 (C1b2, C2b2, C3b2, C4b3, C5b3)               | `"Hamburger"` | Not null              | `"Hamburger"` | 9.99  | 10       | Item is updated in `storage/item.txt` |
      | T2 (C1b2, C2b2, C3b1, C4b3, C5b3)               | `"Hamburger"` | Not Null              | `""`        | 9.99  | 10       | Item is not updated in `storage/item.txt` |
      | T3 (C1b2, C2b2, C3b2, C4b1, C5b3)               | `"Hamburger"` | Not Null              | `"Hamburger"` | -9.99 | 10       | Item is not updated in `storage/item.txt` |
      | T4 (C1b2, C2b2, C3b2, C4b2, C5b3)               | `"Hamburger"` | Not Null              | `"Hamburger"` | 0     | 10       | Item is not updated in `storage/item.txt` |
      | T5 (C1b2, C2b2, C3b2, C4b4, C5b3)               | `"Hamburger"` | Not Null              | `"Hamburger"` | NaN   | 10       | Item is not updated in `storage/item.txt` |
      | T6 (C1b2, C2b2, C3b2, C4b3, C5b1)               | `"Hamburger"` | Not Null              | `"Hamburger"` | 9.99  | -1       | Item is not updated in `storage/item.txt` |
      | T7 (C1b2, C2b2, C3b2, C4b3, C5b2)               | `"Hamburger"` | Not Null              | `"Hamburger"` | 9.99  | 0        | Item is not updated in `storage/item.txt` |
      | T8 (C1b2, C2b2, C3b2, C4b3, C5b4)               | `"Hamburger"` | Not Null              | `"Hamburger"` | 9.99  | NaN      | Item is not updated in `storage/item.txt` |

#### Functionality-based characteristic

1. **Identify testable functions**

   - Testable function: `update()`

2. **Identify parameters, return types, return values, and exceptional behavior**

   - Parameters: `srcName`, `updatedItem`
   - Return type: `boolean`
   - Return value:
      - `true`: if the item was found and updated successfully.
      - `false`: if the item was not found or the update operation failed.
   - Exceptional behavior:
      - If the underlying storage (e.g., file) cannot be accessed, an `IOException` or other file-related exceptions could occur.

3. **Model the input domain**

   - **Develop characteristics**

     - C1: `srcName` exists in the file.
     - C2: Item’s name exists in the file.

   - **Partition characteristic**

     | Characteristic | b1                         | b2                              |
     | -------------- | --------------------------|---------------------------------|
     | C1 = `srcName` exists in the file | `True` (Exists in the file) | `False` (Does not exist in the file) |
     | C2 = Item’s name exists in the file | `True` (Exists in the file) | `False` (Does not exist in the file) |

   - **Identify (possible) values**

     | Characteristic | b1                              | b2                                |
     | -------------- | --------------------------------|-----------------------------------|
     | C1 = `srcName` exists in the file | `"Pasta"` (The `srcName` that is currently in the file) | `"Chicken"` (The `srcName` that is not currently in the file) |
     | C2 = Item’s name exists in the file | `"Pasta"` (The `srcName` that is currently in the file) | `"NonExistentItem"` (The Item’s name that is not currently in the file) |

4. **Combine partitions to define test requirements**

   - **PWC Approach**

     - Test requirements: number of tests = 2 * 2 = 4
        - (C1b1, C2b1) = `Item("Pasta", 15.0, 5)`
        - (C1b1, C2b2) = `Item("NonExistentItem", 10.0, 3)`
        - (C1b2, C2b1) = `Item("Pasta", 15.0, 5)`
        - (C1b2, C2b2) = `Item("NonExistentItem", 10.0, 3)`
      - Eliminate redundant tests and infeasible tests

5. **Derive test values**

      | Test Case | Src Name  | Item                    | Expected Result                                |
      |-----------|-----------|-------------------------|------------------------------------------------|
      | T1 (C1b1, C2b1) | `"Pasta"`  | `Item("Pasta", 15.0, 5)`          | `True`  |
      | T2 (C1b1, C2b2) | `"Pasta"`  | `Item("NonExistentItem", 10.0, 3)` | `False`  |
      | T3 (C1b2, C2b1) | `"Chicken"`| `Item("Pasta", 15.0, 5)`          | `False` |
      | T4 (C1b2, C2b2) | `"Chicken"`| `Item("NonExistentItem", 10.0, 3)` | `False` |

---

#### All Test Cases for `testUpdateItem`

| Test Case | srcName | updatedItem                | Expected Result                              |
|-----------|---------|----------------------------|----------------------------------------------|
| T1 (C1b2, C2b2, C3b2, C4b3, C5b3) | `"Hamburger"` | `Item("Hamburger", 9.99, 10)` | Item is updated in `storage/item.txt` |
| T2 (C1b2, C2b2, C3b1, C4b3, C5b3) | `"Hamburger"` | `Item("", 9.99, 10)` | Item is not updated in `storage/item.txt` |
| T3 (C1b2, C2b2, C3b2, C4b1, C5b3) | `"Hamburger"` | `Item("Hamburger", -9.99, 10)` | Item is not updated in `storage/item.txt` |
| T4 (C1b2, C2b2, C3b2, C4b2, C5b3) | `"Hamburger"` | `Item("Hamburger", 0, 10)` | Item is not updated in `storage/item.txt` |
| T5 (C1b2, C2b2, C3b2, C4b4, C5b3) | `"Hamburger"` | `Item("Hamburger", NaN, 10)` | Item is not updated in `storage/item.txt` |
| T6 (C1b2, C2b2, C3b2, C4b3, C5b1) | `"Hamburger"` | `Item("Hamburger", 9.99, -1)` | Item is not updated in `storage/item.txt` |
| T7 (C1b2, C2b2, C3b2, C4b3, C5b2) | `"Hamburger"` | `Item("Hamburger", 9.99, 0)` | Item is not updated in `storage/item.txt` |
| T8 (C1b2, C2b2, C3b2, C4b3, C5b4) | `"Hamburger"` | `Item("Hamburger", 9.99, NaN)` | Item is not updated in `storage/item.txt` |
| T9 (C1b1, C2b1, C3b2) | `"Pasta"` | `Item("Pasta", 15.0, 5)` | `True` (indicates that both `srcName` and the item exist in the file) |
| T10 (C1b1, C2b2, C3b2) | `"Pasta"` | `Item("NonExistentItem", 10.0, 3)` | `False` (indicates that the item does not exist in the file) |
| T11 (C1b2, C2b1, C3b2) | `"Chicken"` | `Item("Pasta", 15.0, 5)` | `False` (indicates that the `srcName` does not exist in the file) |
| T12 (C1b2, C2b2, C3b2) | `"Chicken"` | `Item("NonExistentItem", 10.0, 3)` | `False` (indicates that neither `srcName` nor the item exists in the file) |

---

### Test Suite 4: testReduceItemQuantityByItemName

- **Goal**: Test reducing the quantity of an item to ensure it correctly reduces in persistent storage (`storage/item.txt`) based on the specified `itemName` and `reduceNumber`.

#### Interface-based characteristic

1. **Identify testable functions**

   - Testable function: `ReduceItemQuantityByItemName()`

2. **Identify parameters, return types, return values, and exceptional behavior**

   - Parameters:
     - `String itemName`: The name of the item.
     - `int quantity`: The quantity to reduce.
   - Return Type: `boolean`
   - Return Values:
     - `True`: if the reduction of item quantity is successful.
     - `False`: if the reduction of item quantity fails.
   - Exceptional Behavior:
     - Throws exceptions like `IOException` or `FileNotFoundException` if the system fails to access item storage (e.g., database issues).

3. **Model the input domain**

   - **Develop characteristics**:

     - `C1`: `itemName` is empty.
     - `C2`: `itemName` is null.
     - `C3`: Value of `quantity`.

   - **Partition Characteristic**

     | Characteristic               | b1           | b2           | b3               | b4                             |
     |------------------------------|--------------|--------------|------------------|--------------------------------|
     | `C1 = itemName is empty`     | Empty        | Not empty    |                  |                                |
     | `C2 = itemName is null`      | Null         | Not null     |                  |                                |
     | `C3 = Value of quantity`     | Quantity < 0 | Quantity = 0 | Quantity > 0     | Quantity is larger than list size |

   - **Possible Values**

     | Characteristic               | b1             | b2       | b3   | b4                  |
     |------------------------------|----------------|----------|------|---------------------|
     | `C1 = itemName is empty`     | ""             | "Pizza"  |      |                     |
     | `C2 = itemName is null`      | null           | "Pizza"  |      |                     |
     | `C3 = Value of quantity`     | -1             | 0        | 5    | `Integer.MAX_VALUE` |

4. **Combine partitions to define test requirements**

   - **ECC Approach**: 

     - Number of test cases = 4

5. **Derive test values**

     | Test Case | itemName | reduceNumber       | Expected Result               |
     |-----------|----------|--------------------|-------------------------------|
     | T1        | "Pizza"  | 0                  | False                         |
     | T2        | "Pizza"  | 5                  | True                          |
     | T3        | "Pizza"  | `Integer.MAX_VALUE`| False                         |

#### Functionality-based characteristic

1. **Identify testable functions**

   - Testable function: `ReduceItemQuantityByItemName()`

2. **Identify parameters, return types, return values, and exceptional behavior**
   - Parameters:
     - `String itemName`: The name of the item.
     - `int quantity`: The quantity to reduce.
   - Return Type: `void`
   - Return values: -
   - Exceptional Behavior:
     - Throws exceptions like `IOException` or `FileNotFoundException` if the system fails to access item storage (e.g., database issues).

3. **Model the input domain**

    - **Develop characteristics**:

     - `C1`: `itemName` is matched with the name in the `itemList`.
     - `C2`: Value of the original quantity of the item compared to the reduced quantity.

   - **Partition Characteristic**

     | Characteristic                    | b1                               | b2                         | b3                    |
     |-----------------------------------|----------------------------------|----------------------------|-----------------------|
     | `C1 = itemName is matched`        | True (matches)                  | False (does not match)     |                       |
     | `C2 = Original vs reduced quantity` | Original < Reduced              | Original = Reduced         | Original > Reduced    |

   - **Possible Values**

     | Characteristic                    | b1           | b2           | b3                          |
     |-----------------------------------|--------------|--------------|-----------------------------|
     | `C1 = itemName is matched`        | "Pizza"      | "Banana"     |                             |
     | `C2 = Original vs reduced quantity` | `2 < 1`     | `2 = 2`      | `2 > 1`                     |

4. **Combine partitions to define test requirements**

   - **ACoC Approach**: 

      - Number of test cases = 6

5. **Derive test values**

     | Test Case | itemName | reduceNumber | Expected Result                             |
     |-----------|----------|--------------|---------------------------------------------|
     | T1        | "Pizza"  | 1            | Quantity of item is reduced                |
     | T2        | "Pizza"  | 2            | Quantity of item is reduced                |
     | T3        | "Pizza"  | 1           | Quantity of item is not reduced (exceeds available quantity) |
     | T4        | "Banana" | 1            | Quantity of item is not reduced (item does not match) |
     | T5        | "Banana" | 2            | Quantity of item is not reduced (item does not match) |
     | T6        | "Banana" | 1           | Quantity of item is not reduced (exceeds available quantity) |

---

#### All Test Cases for`testReduceItemQuantityByItemName`

| Test Case                         | `itemName` | `reduceNumber`         | Expected Result                                 |
|-----------------------------------|------------|------------------------|-------------------------------------------------|
| T1 (C1b2, C2b2, C3b2)             | `"Pizza"`  | 0                      | `False`                                         |
| T2 (C1b2, C2b2, C3b3)             | `"Pizza"`  | 5                      | `True`                                          |
| T3 (C1b2, C2b2, C3b4)             | `"Pizza"`  | `Integer.MAX_VALUE`    | `False`                                         |
| T4 (C1b1, C2b1)                   | `"Pizza"`  | 1                      | `True` - The quantity of the item is reduced    |
| T5 (C1b1, C2b2)                   | `"Pizza"`  | 2                      | `True` - The quantity of the item is reduced    |
| T6 (C1b1, C2b3)                   | `"Pizza"`  | 1                      | `False` - The quantity of the item is not reduced |
| T7 (C1b2, C2b1)                   | `"Banana"` | 1                      | `False` - The quantity of the item is not reduced |
| T8 (C1b2, C2b2)                   | `"Banana"` | 2                      | `False` - The quantity of the item is not reduced |
| T9 (C1b2, C2b3)                   | `"Banana"` | 1                      | `False` - The quantity of the item is not reduced |

---

## Labour Management

### Test Suite 5: testGetItemByIndex

- **Goal**: Test getting an item by inputting the index of the item

#### Interface-based characteristic

1. **Identify testable functions**

   - Testable function: `getItemByIndex()`

2. **Identify parameters, return types, return values, and exceptional behavior**

   - Parameters: `int index`
   - Return type: `Item`
   - Return value:
     - `item`
     - `null`
   - Exceptional behavior: -

3. **Model the input domain**

   - **Develop characteristics**

     - C1: Value of the index

   - **Partition characteristic**

     | Characteristic | b1       | b2       | b3      | b4                        |
     | -------------- | -------- | -------- | ------- | ------------------------- |
     | C1 = Value of the index | Index < 0 | Index = 0 | Index > 0 | Index is larger than list size |

   - **Identify (possible) value**

     | Characteristic | b1 | b2 | b3 | b4                      |
     | -------------- | -- | -- | -- | ----------------------- |
     | C1 = Value of the index | -1 | 0  | 1  | `Integer.MAX_VALUE` |

4. **Combine partitions to define test requirements**

   - **ECC Approach**

     - Test requirements: number of tests = 4
       - (C1b1): `index = -1`
       - (C1b2): `index = 0`
       - (C1b3): `index = 1`
       - (C1b4): `index = Integer.MAX_VALUE`
     - Eliminate redundant tests and infeasible tests

5. **Derive test values**

      | Test Case | Index                  | Expected Result               |
      |-----------|------------------------|-------------------------------|
      | T1 (C1b1) | -1                     | `null`                        |
      | T2 (C1b2) | 0                      | `null`                        |
      | T3 (C1b3) | 1                      | `Item`                        |
      | T4 (C1b4) | `Integer.MAX_VALUE`    | `null`                        |

#### Functionality-based characteristic

1. **Identify testable functions**

   - Testable function: `getItemByIndex()`

2. **Identify parameters, return types, return values, and exceptional behavior**

   - Parameters: `int index`
   - Return type: `Item`
   - Return value:
     - `item`
     - `null`
   - Exceptional behavior: -

3. **Model the input domain**

   - **Develop characteristics**

     - C1: Valid index access
     - C2: List is empty

   - **Partition characteristic**

     | Characteristic | b1  | b2  |
     | -------------- | --- | --- |
     | C1 = Valid index access | True | False |
     | C2 = List is empty       | True | False |

   - **Identify (possible) value**

     | Characteristic | b1 | b2 |
     | -------------- | -- | -- |
     | C1 = Valid index access | `1` | `10` (if the size of item is 8) |
     | C2 = List is empty       | `[]` | `[Item1, Item2]` |

4. **Combine partitions to define test requirements**

   - **MBCC Approach**

     - Base Choice: (C1b1, C2b2), (C1b2, C2b2)
     - Test requirements: number of tests = 2 + [(2 * (2 - 2)) + (2 * (2 - 1))] = 4
       - (C1b1, C2b2) = `1` (If the index is valid and the list is not empty)
       - (C1b2, C2b2) = `13` (If the index is not valid and the list is not empty)
       - (C1b1, C2b1) = Infeasible
       - (C1b2, C2b1) = `1` (If the index is not valid and the list is empty)
     - Eliminate redundant tests and infeasible tests

5. **Derive test values**

      | Test Case           | Index                  | Expected Result                           |
      |---------------------|------------------------|-------------------------------------------|
      | T1 (C1b1, C2b2)     | 1 (If the index is valid and the list is not empty) | `Item` |
      | T2 (C1b2, C2b2)     | 13 (If the index is not valid and the list is not empty) | `null` |
      | T3 (C1b2, C2b1)     | 1 (If the index is not valid and the list is empty) | `null` |

---

#### All Test Cases for `testGetItemByIndex`

| Test Case           | Index                  | Expected Result                           |
|---------------------|------------------------|-------------------------------------------|
| T1 (C1b1)           | -1                     | `null`                                    |
| T2 (C1b2)           | 0                      | `null`                                    |
| T3 (C1b3)           | 1                      | `Item`                                    |
| T4 (C1b4)           | `Integer.MAX_VALUE`    | `null`                                    |
| T5 (C1b1, C2b2)     | 2 (If the index is valid and the list is not empty) | `Item` |
| T6 (C1b2, C2b2)     | 13 (If the index is not valid and the list is not empty) | `null` |
| T7 (C1b2, C2b1)     | 1 (If the index is not valid and the list is empty) | `null` |

---

### Test Suite 6: testCreateLabour

- **Goal**: Test appending a new `Labour` object’s details (such as ID, name, and salary) to a file (`storage/labour.txt`).

#### Interface-based characteristic

1. **Identify testable functions**

   - Testable function: `create(Labour labour)`

2. **Identify parameters, return types, return values, and exceptional behavior**

   - Parameters: `Labour labour`
   - Return type: `void`
   - Return value: `None`
   - Exceptional behavior:
     - `FileNotFoundException`: If `storage/labour.txt` is missing or cannot be accessed, the method logs the error and does not write to the file.

3. **Model the input domain**

   - **Develop characteristics**

     - C1: `Labour` object is `null`
     - C2: The `id` field in the `Labour` object is empty
     - C3: The `name` field in the `Labour` object is empty
     - C4: The salary of the `Labour` object is `0`
   - **Partition characteristic**

     | Characteristic | b1 | b2 | b3 | b4 |
     | -------------- | -- | -- | -- | -- |
     | C1 = `Labour` object is `null` | `True` | `False` | | |
     | C2 = The `id` field in the `Labour` object is empty | `True` | `False` | | |
     | C3 = The `name` field in the `Labour` object is empty | `True` | `False` | | |
     | C4 = The salary of the `Labour` object is `0` | `salary < 0` | `salary = 0` | `salary > 0` | `salary is not a number` |

   - **Identify (possible) values**

     | Characteristic | b1 | b2 | b3 | b4 |
     | -------------- | -- | -- | -- | -- |
     | C1 = `Labour` object is `null` | `null` | `Labour("0011", "John", 5000)` | | |
     | C2 = The `id` field in the `Labour` object is empty | `Labour("", "John", 5000)` | `Labour("0011", "John", 5000)` | | |
     | C3 = The `name` field in the `Labour` object is empty | `Labour("0011", "", 5000)` | `Labour("0011", "John", 5000.0)` | | |
     | C4 = The salary of the `Labour` object is `0` | `Labour("0011", "John", -5000.0)` | `Labour("0011", "John", 0)` | `Labour("0011", "John", 5000.0)` | `Labour("0011", "John", NaN)` |

4. **Combine partitions to define test requirements**

   - **ECC Approach**

     - Number of test cases: 4
       - (C1b1, C2b1, C3b1, C4b1) = Infeasible
       - (C1b2, C2b2, C3b1, C4b2) = `Labour("0011", "", 0.0)`
       - (C1b2, C2b2, C3b2, C4b3) = `Labour("0011", "John", 5000.0)`
       - (C1b2, C2b2, C3b2, C4b4) = `Labour("0011", "John", NaN)`
     - Eliminate redundant tests and infeasible tests

5. **Derive test values**

      | Test Case | Labour                | id      | name   | salary | Expected Result                                |
      |-----------|-----------------------|---------|--------|--------|------------------------------------------------|
      | T1        | Empty                 | Empty   | Empty  | -5000.0| Infeasible                                     |
      | T2        | `Not null`            | Not null| Empty  | 0.0    | Labour is not created in `storage/labour.txt`   |
      | T3        | `Not null`            | Not null| Not null| 5000.0 | Labour is created in `storage/labour.txt`       |
      | T4        | `Not null`            | Not null| Not null| NaN    | Labour is created in `storage/labour.txt`       |

#### Functionality-based characteristic

1. **Identify testable functions**

   - Testable function: `create()`

2. **Identify parameters, return types, return values, and exceptional behavior**

   - Parameters: `Labour labour`
   - Return type: `void`
   - Return value: -
   - Exceptional behavior: `FileNotFoundException` (if `storage/labour.txt` is missing or cannot be accessed)

3. **Model the input domain**

   - **Develop characteristics**

     - C1: The `Labour`'s `id` exists in the file
   - **Partition characteristic**

     | Characteristic | b1 | b2 |
     | -------------- | -- | -- |
     | C1 = The `Labour`'s `id` exists in the file | `True` | `False` |

   - **Identify (possible) value**

     | Characteristic | b1                          | b2                          |
     | -------------- | ---------------------------|-----------------------------|
     | C1 = The `Labour`'s `id` exists in the file | `Labour("0111","Shahin",2500.0)` | `Labour("5555", "JanJa", 5555.0)` |

4. **Combine partitions to define test requirements**

   - **ACoC Approach**

     - Test requirements: number of tests = 2
       - (C1b1) = `Labour("0111","Shahin",2500.0)`
       - (C2b2) = `Labour("5555", "JanJa", 5555.0)`
     - Eliminate redundant tests and infeasible tests

5. **Derive test values**:

      | Test Case | Labour                        | Expected Result                                |
      |-----------|-------------------------------|------------------------------------------------|
      | T1 (C1b1) | `Labour("0111","Shahin",2500.0)` | Labour is not created in `storage/labour.txt`   |
      | T2 (C2b2) | `Labour("5555", "JanJa", 5555.0)` | Labour is created in `storage/labour.txt`       |

---

#### All Test Cases for `testCreateLabour`

| Test Case | Labour          | id    | name   | salary | Expected Result                      |
|-----------|-----------------|-------|--------|--------|--------------------------------------|
| T1 (C1b2, C2b2, C3b1, C4b2) | Not null | Not null | Empty | 0.0    | Labour is not created in `storage/labour.txt` |
| T2 (C1b2, C2b2, C3b2, C4b3) | Not null | Not null | Not null | 5000.0 | Labour is created in `storage/labour.txt`    |
| T3 (C1b2, C2b2, C3b2, C4b4) | Not null | Not null | Not null | NaN    | Labour is not created in `storage/labour.txt` |
| T4 (C1b1) | `Labour("0111", "Shahin", 2500.0)` |  |  |  |  Labour is not created in `storage/labour.txt` |
| T5 (C2b2) | `Labour("5555", "JanJa", 5555.0)` |  |  |  |  Labour is created in `storage/labour.txt` |

---

### Test Suite 7: testDeleteLabour 

- **Goal**: Test functionality of deleting a labour by id

#### Interface-based characteristic

1. **Identify testable functions**

   - Testable function: `delete()`

2. **Identify parameters, return types, return values, and exceptional behavior**

   - Parameters: `String id` (The ID of the labor to be deleted)
   - Return type: `void` (The function does not return any value. Instead, deletion is verified by checking the state of the system, such as the labour list or file content).
   - Return value: -
   - Exceptional behavior:
     - `IOException`: If there is an issue with deleting the file using `Files.delete()`.
     - `FileNotFoundException`: If the file `storage/labour.txt` cannot be found during the file update process.

3. **Model the input domain**

   - **Develop characteristics**

     - C1: ID is an empty string.

   - **Partition characteristic**

     | Characteristic | b1              | b2             |
     | -------------- | --------------- | -------------- |
     | C1 = ID is an empty string. | True (Empty String) | False (Non-Empty String) |

   - **Identify (possible) values**

     | Characteristic | b1 | b2      |
     | -------------- | -- | ------- |
     | C1 = ID is an empty string. | `""` | `"L1"` |

4. **Combine partitions to define test requirements**

   - **ACoC Approach**

     - Test requirements: number of tests = 2
       - (C1b1) = `""`
       - (C1b2) = `"L1"`
     - Eliminate redundant tests and infeasible tests

5. **Derive test values**

      | Test Case | ID  | Expected Result                                    |
      |-----------|-----|----------------------------------------------------|
      | T1 (C1b1) | `""` | Unable to delete labour (Cannot delete with empty ID) |
      | T2 (C1b2) | `"L1"` | Labour is deleted (Labour `"L1"` is deleted successfully) |

#### Functionality-based characteristic

1. **Identify testable functions**

   - Testable function: `delete()`

2. **Identify parameters, return types, return values, and exceptional behavior**

   - Parameters: `String id` (The ID of the labor to be deleted)
   - Return type: `void` (The function does not return any value. Instead, deletion is verified by checking the state of the system, such as the labour list or file content).
   - Return value: -
   - Exceptional behavior:
     - `IOException`: If there is an issue with deleting the file using `Files.delete()`.
     - `FileNotFoundException`: If the file `storage/labour.txt` cannot be found during the file update process.

3. **Model the input domain**

   - **Develop characteristics**

     - C1: Labour exists in the file

   - **Partition characteristic**

     | Characteristic | b1                        | b2                         |
     | -------------- | ------------------------- | -------------------------- |
     | C1 = Labour exists in the file | True (Labour with the given ID exists.) | False (Labour with the given ID does not exist.) |

   - **Identify (possible) values**

     | Characteristic | b1     | b2     |
     | -------------- | ------ | ------ |
     | C1 = Labour exists in the file | `"L2"` | `"9999"` |

4. **Combine partitions to define test requirements**

   - **ECC Approach**

     - Test requirements: number of tests = 2
       - (C1b1)
       - (C1b2)
     - Eliminate redundant tests and infeasible tests

5. **Derive test values**

      | Test Case | ID     | Expected Result                                   |
      |-----------|--------|---------------------------------------------------|
      | T1 (C1b1) | `"L2"` | Labour with ID `"L2"` is deleted (Labour `"L2"` exists and is deleted successfully) |
      | T2 (C1b2) | `"9999"` | Unable to delete labour (Labour `"9999"` does not exist in the file, so no deletion occurs) |

---

#### All Test Cases for `testDeleteLabour`

| Test Case       | ID       | Expected Result                                               |
|-----------------|----------|---------------------------------------------------------------|
| T1 (C1b1)       | `""`     | Unable to delete labour (Cannot delete with empty ID)         |
| T2 (C1b2)       | `"L1"`   | Labour with ID `"L1"` is deleted (Labour `"L1"` is deleted successfully) |
| T3 (C1b1)       | `"L2"`   | Labour with ID `"L2"` is deleted (Labour `"L2"` exists and is deleted successfully) |
| T4 (C2b2)       | `"9999"` | Unable to delete labour (Labour `"9999"` does not exist in the file, so no deletion occurs) |

---

### Test Suite 8: testUpdateLabour

- **Goal**: Test updating Labour’s information by inputting the ID of the labour and a new `Labour` object.

#### Interface-based characteristic

1. **Identify testable functions**

   - Testable function: `update()`

2. **Identify parameters, return types, return values, and exceptional behavior**

   - Parameters: `sourceId`, `Labour updatedLabour`
   - Return type: `boolean`
   - Return value:
     - `true`: If the `sourceId` is found and the update is successful.
     - `false`: If the `sourceId` is not found and the update fails.
   - Exceptional behavior:
     - `IOException`: If the file deletion or writing process encounters an issue (e.g., if the file cannot be found, read, or written to).
     - `FileNotFoundException`: If the system is unable to find or open the file to write the updated data.
     - The function catches these exceptions and logs them using the `Logger`, but the exception doesn't change the return value, meaning the function will still return `true` unless the `sourceId` was not found.

3. **Model the input domain**

   - **Develop characteristics**

     - C1: `sourceId` is empty
     - C2: `Labour` object is `null`
     - C3: The `id` field in the `Labour` object is empty
     - C4: The `name` field in the `Labour` object is empty
     - C5: The value of the `salary` of the `Labour` object

   - **Partition characteristic**

     | Characteristic                          | b1  | b2 | b3 | b4               |
     |-----------------------------------------|-----|----|----|------------------|
     | C1: `sourceId` is empty                 | True | False |    |                 |
     | C2: `Labour` object is `null`           | True | False |    |                 |
     | C3: The `id` field in the `Labour` object is empty | True | False |    |         |
     | C4: The `name` field in the `Labour` object is empty | True | False |    |     |
     | C5: The `salary` of the `Labour` object | `salary < 0` | `salary = 0` | `salary > 0` | `salary is not a number` |

   - **Identify (possible) value**

     | Characteristic                          | b1  | b2 | b3 | b4               |
     |-----------------------------------------|-----|----|----|------------------|
     | C1: `sourceId` is empty                 | `""` | `"L007"` |    |             |
     | C2: `Labour` object is `null`           | `null` | `Labour("L007", "James Bond", 25000.0)` |    |    |
     | C3: The `id` field in the `Labour` object is empty | `Labour("", "James Bond", 25000.0)` | `Labour("L007", "James Bond", 25000.0)` |    | |
     | C4: The `name` field in the `Labour` object is empty | `Labour("L007", "", 25000.0)` | `Labour("L007", "James Bond", 25000.0)` |    | |
     | C5: The `salary` of the `Labour` object | `Labour("L007", "James Bond", -25000.0)` | `Labour("L007", "James Bond", 0.0)` | `Labour("L007", "James Bond", 25000.0)` | `Labour("L007", "James Bond", NaN)` |

4. **Combine partitions to define test requirements**

   - **BCC Approach**

     - Test requirements:
       - Base Choice = (C1b2, C2b2, C3b2, C4b2, C5b3)
       - Number of Test = 1 + [(2-1)+(2-1)+(2-1)+(2-1)+(4-1)] = 8
     - Test cases:
       - T1 (C1b2, C2b2, C3b2, C4b2, C5b3) = `Labour("007", "James Bond", 25000.0)`
       - T2 (C1b1, C2b2, C3b2, C4b2, C5b3) = infeasible
       - T3 (C1b2, C2b1, C3b2, C4b2, C5b3) = infeasible
       - T4 (C1b2, C2b2, C3b1, C4b2, C5b3) = `Labour("", "James Bond", 25000.0)`
       - T5 (C1b2, C2b2, C3b2, C4b1, C5b3) = `Labour("007", "", 25000.0)`
       - T6 (C1b2, C2b2, C3b2, C4b2, C5b1) = `Labour("007", "James Bond", -25000.0)`
       - T7 (C1b2, C2b2, C3b2, C4b2, C5b2) = `Labour("007", "James Bond", 0.0)`
       - T8 (C1b2, C2b2, C3b2, C4b2, C5b4) = `Labour("007", "James Bond", NaN)`
     - Eliminate redundant tests and infeasible tests

      5. **Derive test values**
  
      | Test Case                          | `sourceId` | `Labour` | ID | Name | Salary | Expected Output                                 |
      |------------------------------------|------------|----------|-------------|---------------|----------------|------------------------------------------------|
      | T1 (C1b2, C2b2, C3b2, C4b2, C5b3)  | `"L1"`     | Not null | `"L007"`    | `"James Bond"`| 25000.0        | Labour is updated in `storage/labour.txt`       |
      | T2 (C1b1, C2b2, C3b2, C4b2, C5b3)  | `""`       | Not null | `"L007"`    | `"James Bond"`| 25000.0        | Infeasible                                      |
      | T3 (C1b2, C2b1, C3b2, C4b2, C5b3)  | `"L1"`     | null     | `"L007"`    | `"James Bond"`| 25000.0        | Infeasible                                      |
      | T4 (C1b2, C2b2, C3b1, C4b2, C5b3)  | `"L1"`     | Not null | `""`        | `"James Bond"`| 25000.0        | Labour is not updated in `storage/labour.txt`   |
      | T5 (C1b2, C2b2, C3b2, C4b1, C5b3)  | `"L1"`     | Not null | `"L007"`    | `""`          | 25000.0        | Labour is not updated in `storage/labour.txt`   |
      | T6 (C1b2, C2b2, C3b2, C4b2, C5b1)  | `"L1"`     | Not null | `"L007"`    | `"James Bond"`| -25000.0       | Labour is not updated in `storage/labour.txt`   |
      | T7 (C1b2, C2b2, C3b2, C4b2, C5b2)  | `"L1"`     | Not null | `"L007"`    | `"James Bond"`| 0.0            | Labour is not updated in `storage/labour.txt`   |
      | T8 (C1b2, C2b2, C3b2, C4b2, C5b4)  | `"L1"`     | Not null | `"L007"`    | `"James Bond"`| NaN            | Labour is not updated in `storage/labour.txt`   |

#### Functionality-based characteristic

1. **Identify testable functions**

   - Testable function: `update()`

2. **Identify parameters, return types, return values, and exceptional behavior**

   - Parameters: `sourceId`, `Labour updatedLabour`
   - Return type: `Boolean`
   - Return value:
     - `True`: If the `sourceId` is found and the update is successful.
     - `False`: If the `sourceId` is not found and the update fails.
   - Exceptional behavior:
     - `IOException`: Occurs if there is a problem deleting or writing to the `labour.txt` file.
     - `FileNotFoundException`: If the file `labour.txt` is missing or cannot be found.

3. **Model the input domain**

   - **Develop characteristics**

     - C1: The `ID` of the Labour exists in the file

   - **Partition characteristic**

     | Characteristic                        | b1   | b2                          |
     |---------------------------------------|------|-----------------------------|
     | C1 = The `ID` of the Labour exists    | Exists | Not exists                 |

   - **Identify (possible) value**

     | Characteristic                        | b1         | b2                          |
     |---------------------------------------|------------|-----------------------------|
     | C1 = The `ID` of the Labour exists    | `"L1"`     | `"L099"`                    |

4. **Combine partitions to define test requirements**

   - **ACoC Approach**

     - Test requirements:
       - Number of Test = 2
       - (C1b1) = `"L1"` (This labour ID exists in the file)
       - (C1b2) = `"L99"` (This labour ID doesn’t exist in the file)
     - Eliminate redundant tests and infeasible tests

5. **Derive test values**

      | Test Case | `sourceId` | Expected Output                                      |
      |-----------|------------|-----------------------------------------------------|
      | (C1b1)    | `"L1"`     | Labour is updated in `storage/labour.txt`            |
      | (C1b2)    | `"L99"`    | Labour is not updated in `storage/labour.txt`        |

---

#### All Test Cases for `testUpdateLabour`

   | Test Case                                    | `sourceId` | `UpdateLabour`                | Expected Output                            |
   |----------------------------------------------|------------|-------------------------------|--------------------------------------------|
   | T1 (C1b2, C2b2, C3b2, C4b2, C5b3)             | `"L1"`     | `Labour("L007", "James Bond", 25000.0)` | Labour is updated in `storage/labour.txt` |
   | T2 (C1b2, C2b2, C3b1, C4b2, C5b3)             | `"L2"`     | `Labour("", "James Bond", 25000.0)`     | Labour is updated in `storage/labour.txt` |
   | T3 (C1b2, C2b2, C3b2, C4b1, C5b3)             | `"L3"`     | `Labour("L007", "", 25000.0)`           | Labour is updated in `storage/labour.txt` |
   | T4 (C1b2, C2b2, C3b2, C4b2, C5b1)             | `"L4"`     | `Labour("L007", "James Bond", -25000.0)`| Labour is not updated in `storage/labour.txt` |
   | T5 (C1b2, C2b2, C3b2, C4b2, C5b2)             | `"L5"`     | `Labour("L007", "James Bond", 0.0)`     | Labour is not updated in `storage/labour.txt` |
   | T6 (C1b2, C2b2, C3b2, C4b2, C5b4)             | `"L6"`     | `Labour("L007", "James Bond", NaN)`     | Labour is not updated in `storage/labour.txt` |
   | T7 (C1b1) Exists                                    | `"L8"`    | `Labour("L014", "Yuki Ishikawa", 65000.0)` | Labour is updated in `storage/labour.txt` |
   | T8 (C1b2) Not Exists                                  | `"L99"`  | `Labour("L014", "Yuki Ishikawa", 65000.0)` | Labour is not updated in `storage/labour.txt` |

---

## Order Management

### Test Suite 9: testAddToCard

- **Goal**: Test functionality of adding an item to cart

#### Interface-based characteristic

1. **Identify testable functions**

   - Testable function: `addItemToCart()`

2. **Identify parameters, return types, return values, and exceptional behavior**

   - Parameters: `CartItem cartItem`
   - Return type: `void`
   - Return value: -
   - Exceptional behavior: -

3. **Model the input domain**

   - **Develop characteristics**

     - C1: `cartItem` is null.
     - C2: Item in the `cartItem` is null
     - C3: Value of the price field of the `cartItem`
     - C4: Value of the quantity field of the `cartItem`
     - C5: Value of the price field of the `item`
     - C6: Value of the quantity field of the `item`
     - C7: Name of the `item` is empty

   - **Partition characteristic**

     | Characteristic | b1  | b2 | b3 | b4 |
     | -------------- | --- | -- | -- | -- |
     | C1 = `cartItem` is null | True | False | | |
     | C2 = Item in the `cartItem` is null | True | False | | |
     | C3 = Value of the price field of the `cartItem` | Price < 0 | Price = 0 | Price > 0 | Price is not a number |
     | C4 = Value of the quantity field of the `cartItem` | Quantity < 0 | Quantity = 0 | Quantity > 0 | Quantity is not a number |
     | C5 = Value of the price field of the `item` | Price < 0 | Price = 0 | Price > 0 | Price is not a number |
     | C6 = Value of the quantity field of the `item` | Quantity < 0 | Quantity = 0 | Quantity > 0 | Quantity is not a number |
     | C7 = Name of the `item` is empty | True | False | | |

   - **Identify (possible) values**

     | Characteristic | b1  | b2 | b3 | b4 |
     | -------------- | --- | -- | -- | -- |
     | C1 = `cartItem` is null | `null` | `CartItem(Item("Pizza", 10.0, 1), 1, 10.0)` | | |
     | C2 = Item in the `cartItem` is null | `CartItem(null, 1, 10.0)` | `CartItem(Item("Pizza", 10.0, 1), 1, 10.0)` | | |
     | C3 = Value of the price field of the `cartItem` | `CartItem(Item("Pizza", 10.0, 1), 1, -10.0)` | `CartItem(Item("Pizza", 10.0, 1), 1, 0.0)` | `CartItem(Item("Pizza", 10.0, 1), 1, 10.0)` | `CartItem(Item("Pizza", 10.0, 1), 1, NaN)` |
     | C4 = Value of the quantity field of the `cartItem` | `CartItem(Item("Pizza", 10.0, 1), -1, 10.0)` | `CartItem(Item("Pizza", 10.0, 1), 0, 10.0)` | `CartItem(Item("Pizza", 10.0, 1), 1, 10.0)` | `CartItem(Item("Pizza", 10.0, 1), NaN, 10.0)` |
     | C5 = Value of the price field of the `item` | `CartItem(Item("Pizza", -10.0, 1), 1, 10.0)` | `CartItem(Item("Pizza", 0.0, 1), 1, 10.0)` | `CartItem(Item("Pizza", 10.0, 1), 1, 10.0)` | `CartItem(Item("Pizza", NaN, 1), 1, 10.0)` |
     | C6 = Value of the quantity field of the `item` | `CartItem(Item("Pizza", 10.0, -1), 1, 10.0)` | `CartItem(Item("Pizza", 10.0, 0), 1, 10.0)` | `CartItem(Item("Pizza", 10.0, 1), 1, 10.0)` | `CartItem(Item("Pizza", 10.0, NaN), 1, 10.0)` |
     | C7 = Name of the `item` is empty | `CartItem(Item("", 10.0, 1), 1, 10.0)` | `CartItem(Item("Pizza", 10.0, 1), 1, 10.0)` | | |

4. **Combine partitions to define test requirements**

   - **ECC Approach**

     - Test requirements:
       - Number of tests = 4
       - (C1b1, C2b1, C3b1, C4b1, C5b1, C6b1, C7b1) = Infeasible
       - (C1b2, C2b2, C3b2, C4b2, C5b2, C6b2, C7b2) = `CartItem(Item("Pizza", 0.0, 0), 0, 0.0)`
       - (C1b2, C1b2, C3b3, C4b3, C5b3, C6b3, C7b2) = `CartItem(Item("Pizza", 10.0, 1), 1, 10.0)`
       - (C1b2, C1b2, C3b4, C4b4, C5b4, C6b4, C7b2) = `CartItem(Item("Pizza", NaN, NaN), NaN, NaN)`
     - Eliminate redundant tests and infeasible tests

5. **Derive test values**

      | Test Case | `cartItem`                          | Item                | `cartItem`'s price | `cartItem`'s quantity | Item's price | Item's quantity | Expected result                          |
      |-----------|-------------------------------------|---------------------|--------------------|----------------------|--------------|-----------------|------------------------------------------|
      | T1 (C1b2, C2b2, C3b2, C4b2, C5b2, C6b2, C7b2)       | `CartItem(Item("Pizza", 0.0, 0), 0, 0.0)` | `Item("Pizza", 0.0, 0)` | 0.0                | 0                    | 0.0          | 0               | Item added to cart                       |
      | T2 (C1b2, C1b2, C3b3, C4b3, C5b3, C6b3, C7b2)       | `CartItem(Item("Pizza", 10.0, 1), 1, 10.0)` | `Item("Pizza", 10.0, 1)` | 10.0               | 1                    | 10.0         | 1               | Item added to cart                       |
      | T3 (C1b2, C1b2, C3b4, C4b4, C5b4, C6b4, C7b2)       | `CartItem(Item("Pizza", NaN, NaN), NaN, NaN)` | `Item("Pizza", NaN, NaN)` | NaN                | NaN                  | NaN          | NaN             | Item is not added to cart                |

#### Functionality-based characteristic

1. **Identify testable functions**

   - Testable function: `addItemToCart()`

2. **Identify parameters, return types, return values, and exceptional behavior**

   - Parameters: `CartItem cartItem`
   - Return type: `void`
   - Return value: -
   - Exceptional behavior: -

3. **Model the input domain**

   - **Develop characteristics**

     - C1: Value of the quantity of `cartItem` and the quantity of the product
     - C2: Value of the `cartItem`’s price is the product of `cartItem`’s quantity and `item`’s price

   - **Partition characteristic**

     | Characteristic | b1  | b2 | b3 |
     | -------------- | --- | -- | -- |
     | C1 = Value of the quantity of `cartItem` and the quantity of the item | `cartItem.quantity < item.quantity` | `cartItem.quantity = item.quantity` | `cartItem.quantity > item.quantity` |
     | C2 = Value of the `cartItem`’s price is the product of `cartItem`’s quantity and `item`’s price | True (`cartItem.price = cartItem.quantity * item.price`) | False (`cartItem.price != cartItem.quantity * item.price`) | |

   - **Identify (possible) values**

     | Characteristic | b1  | b2 | b3 |
     | -------------- | --- | -- | -- |
     | C1 = Value of the quantity of `cartItem` and the quantity of the item | `5 < 10` | `10 = 10` | `10 > 5` |
     | C2 = Value of the `cartItem`’s price is the product of `cartItem`’s quantity and `item`’s price | `10 = 2 * 5.0` | `15 = 2 * 5.0` | |

4. **Combine partitions to define test requirements**

   - **BCC Approach**

     - Base choice = (C1b2, C2b1)
     - Number of tests = 1 + [(3-1) + (2-1)] = 4
       - (C1b2, C2b1) = `CartItem(Item("Pizza", 10.0, 5), 5, 50.0)`
       - (C1b1, C2b1) = `CartItem(Item("Pizza", 10.0, 10), 5, 50.0)`
       - (C1b3, C2b1) = `CartItem(Item("Pizza", 10.0, 5), 10, 100.0)`
       - (C1b2, C2b2) = `CartItem(Item("Pizza", 10.0, 5), 5, 1.0)`
     - Eliminate redundant tests and infeasible tests

5. **Derive test values**

      | Test Case | `cartItem`’s Price | `cartItem`’s Quantity | Item’s Price | Item’s Quantity | Expected Result                             |
      |-----------|--------------------|----------------------|--------------|-----------------|---------------------------------------------|
      | T1 (C1b2, C2b1) | 50.0 | 5 | 10.0 | 5 | Item added to cart                            |
      | T2 (C1b1, C2b1) | 50.0 | 5 | 10.0 | 10 | Item added to cart                            |
      | T3 (C1b3, C2b1) | 100.0 | 10 | 10.0 | 5 | Item is not added to cart                     |
      | T4 (C1b2, C2b2) | 1.0 | 5 | 10.0 | 5 | Item is not added to cart                     |

---

#### All Test Cases for `testAddToCart`

| Test Case                                      | cartItem                                       | Item                      | cartItem’s price | cartItem’s quantity | Item’s price | Item’s quantity | Expected result                      |
|------------------------------------------------|------------------------------------------------|---------------------------|------------------|---------------------|--------------|-----------------|--------------------------------------|
| T1 (C1b2, C2b2, C3b2, C4b2, C5b2, C6b2, C7b2)   | `CartItem(Item("Pizza", 0.0, 0), 0, 0.0)`       | `Item("Pizza", 0.0, 0)`   | 0.0              | 0                   | 0.0          | 0               | Item is not added to cart            |
| T2 (C1b2, C1b2, C3b3, C4b3, C5b3, C6b3, C7b2)   | `CartItem(Item("Pizza", 10.0, 1), 1, 10.0)`     | `Item("Pizza", 10.0, 1)`  | 10.0             | 1                   | 10.0         | 1               | Item added to cart                   |
| T3 (C1b2, C1b2, C3b4, C4b4, C5b4, C6b4, C7b2)   | `CartItem(Item("Pizza", NaN, NaN), NaN, NaN)`   | `Item("Pizza", NaN, NaN)` | NaN              | NaN                 | NaN          | NaN             | Item is not added to cart            |
| T4 (C1b2, C2b1)                                | `CartItem(Item("Pizza", 10.0, 5), 10, 100.0)`   | `Item("Pizza", 10.0, 5)`  | 50.0             | 5                   | 10.0         | 5               | Item is added to the cart            |
| T5 (C1b1, C2b1)                                | `CartItem(Item("Pizza", 10.0, 10), 5, 50.0)`    | `Item("Pizza", 10.0, 10)` | 50.0             | 5                   | 10.0         | 10              | Item is added to the cart            |
| T6 (C1b3, C2b1)                                | `CartItem(Item("Pizza", 10.0, 5), 10, 100.0)`   | `Item("Pizza", 10.0, 5)`  | 100.0            | 10                  | 10.00        | 5               | Item is not added to the cart        |
| T7 (C1b2, C2b2)                                | `CartItem(Item("Pizza", 10.0, 5), 5, 1.0)`      | `Item("Pizza", 10.0, 5)`  | 1.0              | 5                   | 10.0         | 5               | Item is not added to the cart        |


---
## Authentication 

### Test Suite 10: testLogin

- **Goal**: Test correctness of login function.

#### Interface-based characteristic

1. **Identify testable functions**

    - Testable function: `loginButtonActionPerformed()`

2. **Identify parameters, return types, return values, and exceptional behavior**

    - Parameters: `java.awt.event.ActionEvent evt`
    - Return type: `void`
    - Return value: -
    - Exceptional behavior: -

3. **Model the input domain**

    - **Develop characteristics**

      - C1: `userNameField` is null
      - C2: `userNameField`

- **Partition characteristic**

  | Characteristic | b1  | b2 | b3 |
  | -------------- | --- | -- | -- |
  | C1 = `userNameField` is null | True | False | |
  | C2 = `userNameField` | Correct username | Incorrect username | Empty username |

- **Identify (possible) value**

  | Characteristic | b1  | b2 | b3 |
  | -------------- | --- | -- | -- |
  | C1 = `userNameField` is null | `null` | Not null | |
  | C2 = `userNameField` | “Shahin” | “wrongUser” | “” |

4. **Combine partitions to define test requirements**

  - **ECC Approach**

    - Test requirements: number of test = 3
        - T1 (C1b1, C2b2) = infeasible
        - T2 (C1b2, C2b1) = `userNameField(“Shahin”)`
        - T3 (C1b2, C2b3) = `userNameField(“”)`
    - Eliminate redundant tests and infeasible tests.

5. **Derive test values**

      | Test Case | `userNameField` | Expected result         |
      |-----------|-----------------|------------------------|
      | T1        | “Shahin”        | "Access granted"       |
      | T2        | “”              | "Access Denied"        |

#### Functionality-based characteristic

1. **Identify testable functions**

- Testable function: `loginButtonActionPerformed()`

2. **Identify parameters, return types, return values, and exceptional behavior**

- Parameters: `java.awt.event.ActionEvent evt`
- Return type: `void`
- Return value: -
- Exceptional behavior: -

3. **Model the input domain**

    - **Develop characteristics**
      - C1: `userNameField` matches the correct username

- **Partition characteristic**

  | Characteristic | b1  | b2 |
  | -------------- | --- | -- |
  | C1 = `userNameField` matches the correct username | True | False |

- **Identify (possible) value**

  | Characteristic | b1       | b2         |
  | -------------- | -------- | ---------- |
  | C1 = `userNameField` matches the correct username | “Shahin” | “wrongUser” |

4. **Combine partitions to define test requirements**

    - **ACoC Approach**

      - Test requirements: number of test = 2
        - T1 (C1b1) = `userNameField(“Shahin”)`
        - T2 (C1b2) = `userNameField(“wrongUser”)`
      - Eliminate redundant tests and infeasible tests.

5. **Derive test values**

      | Test Case | `userNameField` | Expected result   |
      |-----------|-----------------|------------------|
      | T1 (C1,b1)       | “Shahin”        | "Access granted" |
      | T2 (C1,b2)      | “wrongUser”     | "Access Denied"  |

---

#### All Test Cases for `testLogin`

| Test Case | `userNameField` | Expected result   |
|-----------|-----------------|------------------|
| T1 (C1b2, C2b1) | “Shahin” | "Access granted" |
| T2 (C1b2, C2b3) | “” | "Access Denied" |
| T3 (C1b1) | “Shahin” | "Access granted" |
| T4 (C1b2) | “wrongUser” | "Access Denied" |

---

