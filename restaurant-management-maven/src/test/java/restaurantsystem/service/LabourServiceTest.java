/* Copyright (C) 2024 Russarin Eaimrittikrai, Supithcha Jongphoemwatthanaphon,
 * Sasasuang Pattanakitjaroenchai, Chaninan Phetpangun, Runchida Ananartyasit,
 * Phacharaphan Chalitchaiya, Pimmada Chompurat - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MUICT Echo license.
 */

 /*
 This file includes test suites: testUpdateLabour and testDeleteLabour
 */

package restaurantsystem.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import restaurantsystem.model.Labour;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class LabourServiceTest {
    private LabourService labourService;
    private static final String FILE_PATH = "storage/labour.txt";
    private static final String INITIAL_CONTENT = "0111,Shahin,2500.0\n3332,Mahmud,1000.0\n";

    @Before
    public void setUp() {
        labourService = new LabourService();
        initializeLabourFile();
    }

    private void initializeLabourFile() {
        String filePath = "storage/labour.txt";
        List<Labour> initialLabours = Arrays.asList(
                new Labour("L1", "John Doe", 3000.00),
                new Labour("L2", "Jane Smith", 3200.00),
                new Labour("L3", "Alice Johnson", 2800.00),
                new Labour("L4", "Bob Brown", 3500.00),
                new Labour("L5", "Charlie Black", 3000.00),
                new Labour("L6", "Diana Green", 3100.00),
                new Labour("L8", "Edward White", 3400.00)
        );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Labour labour : initialLabours) {
                writer.write(labour.getId() + "," + labour.getName() + "," + labour.getSalary());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    //Interface-based characteristic
    //Test case T1 (C1b2, C2b2, C3b2, C4b2, C5b3)
    //Update with complete all value -> Pass
    @Test
    public void testUpdateLabour_FullyUpdate() {
        Labour updatedLabour = new Labour("L007", "Jame Bond", 1000.00);
        boolean updated = labourService.update("L1", updatedLabour);
        assertTrue("Labour update failed", updated);
        Labour resultLabour = null;
        List<Labour> allLabours = labourService.getAll();
        for (Labour l : allLabours) {
            if (l.getId().equals("L007")) {
                resultLabour = l;
                break;
            }
        }
        assertNotNull(resultLabour);
        assertEquals("Incorrect updated salary", 1000.00, resultLabour.getSalary(), 0.01);
    }

    //Interface-based characteristic
    //Test case T2 (C1b2, C2b2, C3b1, C4b2, C5b3)
    //Update name and salary -> Pass
    @Test
    public void testUpdateLabour_NotUpdateId() {
        Labour originalLabour = null;
        List<Labour> allLabours = labourService.getAll();
        for (Labour l : allLabours) {
            if (l.getId().equals("L2")) {
                originalLabour = l;
                break;
            }
        }
        assertNotNull("Original labour not found", originalLabour);
        String originalId = originalLabour.getId();
        Labour updatedLabour = new Labour(originalId, "Jame Bond", 2000.00);
        boolean updated = labourService.update(originalId, updatedLabour);
        assertTrue("Labour update failed", updated);
        Labour resultLabour = null;
        allLabours = labourService.getAll();
        for (Labour l : allLabours) {
            if (l.getId().equals(originalId)) {
                resultLabour = l;
                break;
            }
        }
        assertNotNull(resultLabour);
        assertEquals("Incorrect updated salary", 2000.00, resultLabour.getSalary(), 0.01);
        assertEquals("Incorrect name", "Jame Bond", resultLabour.getName());
    }

    //Interface-based characteristic
    //Test case T3 (C1b2, C2b2, C3b2, C4b1, C5b3)
    //Update id and salary -> Pass
    @Test
    public void testUpdateLabour_NotUpdateName() {
        Labour originalLabour = null;
        List<Labour> allLabours = labourService.getAll();
        for (Labour l : allLabours) {
            if (l.getId().equals("L3")) {
                originalLabour = l;
                break;
            }
        }
        assertNotNull("Original labour not found", originalLabour);
        String originalName = originalLabour.getName();
        double originalSalary = originalLabour.getSalary();
        Labour updatedLabour = new Labour("L007", originalName, originalSalary);
        boolean updated = labourService.update("L3", updatedLabour);
        assertTrue("Labour update failed", updated);
        Labour resultLabour = null;
        allLabours = labourService.getAll();
        for (Labour l : allLabours) {
            if (l.getId().equals("L007")) {
                resultLabour = l;
                break;
            }
        }
        assertNotNull(resultLabour);
        assertEquals("Labour name should not have changed", originalName, resultLabour.getName());
        assertEquals("Incorrect updated salary", originalSalary, resultLabour.getSalary(), 0.01);
    }

    //Interface-based characteristic
    //Test case T4 (C1b2, C2b2, C3b2, C4b2, C5b1)
    //Update salary with negative value -> Fail
    @Test
    public void testUpdateLabour_SalaryNegativevalue() {
        Labour invalidLabour = new Labour("L007", "Jame Bond", -4000.00);
        boolean updated = labourService.update("L4", invalidLabour);
        assertFalse("Labour update should have failed for negative salary", updated);
        Labour resultLabour = null;
        List<Labour> allLabours = labourService.getAll();
        for (Labour l : allLabours) {
            if (l.getId().equals("L4")) {
                resultLabour = l;
                break;
            }
        }
        assertNotNull(resultLabour);
        assertEquals("Labour name should not have changed", "Bob Brown", resultLabour.getName());
        assertEquals("Salary should remain unchanged", 3500.00, resultLabour.getSalary(), 0.01);
    }

    //Interface-based characteristic
    // Test case T5 (C1b2, C2b2, C3b2, C4b2, C5b2)
    //Update salary with zero value -> Fail
    @Test
    public void testUpdateLabour_SalaryZerovalue() {
        Labour invalidLabour = new Labour("L007", "Jame Bond", 0.00);
        boolean updated = labourService.update("L5", invalidLabour);
        assertFalse("Labour update should have failed for zero salary", updated);
        Labour resultLabour = null;
        List<Labour> allLabours = labourService.getAll();
        for (Labour l : allLabours) {
            if (l.getId().equals("L5")) {
                resultLabour = l;
                break;
            }
        }
        assertNotNull(resultLabour);
        assertEquals("Labour name should not have changed", "Charlie Black", resultLabour.getName());
        assertEquals("Salary should remain unchanged", 3000.00, resultLabour.getSalary(), 0.01);
    }

    //Interface-based characteristic
    //Test case T6 (C1b2, C2b2, C3b2, C4b2, C5b4)
    //Update salary with not number value -> Fail
    @Test
    public void testUpdateLabour_SalaryisNotnumber() {
        double free = Double.NaN;
        Labour invalidLabour = new Labour("L007", "Jame Bond", free);
        boolean updated = labourService.update("L6", invalidLabour);
        assertFalse("Labour update should have failed for Nan salary", updated);
        Labour resultLabour = null;
        List<Labour> allLabours = labourService.getAll();
        for (Labour l : allLabours) {
            if (l.getId().equals("L6")) {
                resultLabour = l;
                break;
            }
        }
        assertNotNull(resultLabour);
        assertEquals("Labour name should not have changed", "Diana Green", resultLabour.getName());
        assertEquals("Salary should remain unchanged", 3100.00, resultLabour.getSalary(), 0.01);
    }

    //Functionality-based characteristic
    //Test case T7 (C1b1)
    //Update Labour Object with sourceId Exist -> Pass
    @Test
    public void testUpdateLabour_Exist() {
        Labour originalLabour = null;
        List<Labour> allLabours = labourService.getAll();
        for (Labour l : allLabours) {
            if (l.getId().equals("L8")) {
                originalLabour = l;
                break;
            }
        }
        assertNotNull("Original labour L8 not found", originalLabour);
        Labour updatedLabour = new Labour("L014", "Yuki Ishikawa", 65000.00);
        boolean updated = labourService.update("L8", updatedLabour);
        assertTrue("Labour update failed", updated);
        Labour resultLabour = null;
        allLabours = labourService.getAll();
        for (Labour l : allLabours) {
            if (l.getId().equals("L014")) {
                resultLabour = l;
                break;
            }
        }
        assertNotNull(resultLabour);
        assertEquals("Incorrect name after update", "Yuki Ishikawa", resultLabour.getName());
        assertEquals("Incorrect salary after update", 65000.00, resultLabour.getSalary(), 0.01);
        assertEquals("Incorrect ID after update", "L014", resultLabour.getId());
    }

    //Functionality-based characteristic
    //Test case T8 (C1b2)
    //Update Labour Object with sourceId Not Exist -> Fail
    @Test
    public void testUpdateLabour_NotExist() {
        Labour nonExistentLabour = new Labour("L021", "Takahashi Run", 65000.00);
        boolean updated = labourService.update("L099", nonExistentLabour);
        assertFalse("Update should fail for non-existent labour ID", updated);
        List<Labour> allLabours = labourService.getAll();
        boolean labourExists = false;
        for (Labour l : allLabours) {
            if (l.getId().equals("L021")) {
                labourExists = true;
                break;
            }
        }

        assertTrue("Labour with ID L099 not exist in storage", labourExists);
    }

    //Interface-based characteristic
    //Test case T1 (C1b1)
    //Deleting labour with an empty ID -> Unable to delete labour
    //""
    @Test
    public void testDeleteLabour_EmptyId() {
        List<Labour> initialLabours = labourService.getAll();
        int initialSize = initialLabours.size();
        labourService.delete("");
        List<Labour> laboursAfterDeletion = labourService.getAll();
        assertEquals("No labour should be deleted when ID is empty", initialSize, laboursAfterDeletion.size());
    }


    //Interface-based characteristic
    //Test case T2 (C1b2)
    //Deleting labour that exists -> Labour is deleted
    //"L1"
    @Test
    public void testDeleteLabour_ExistingId() {
        List<Labour> initialLabours = labourService.getAll();
        int initialSize = initialLabours.size();
        labourService.delete("L1");
        List<Labour> laboursAfterDeletion = labourService.getAll();
        assertEquals("Size of labour list should decrease by 1 after deletion", initialSize - 1, laboursAfterDeletion.size());
        boolean exists = laboursAfterDeletion.stream().anyMatch(l -> l.getId().equals("L1"));
        assertFalse("Labour with ID L1 should not exist after deletion", exists);
    }

    //Functionality-based characteristic
    //Test case T3 (C1b1)
    //Deleting a labour that exists after verifying its existence -> Expected Labour is deleted
    //"L2"
    @Test
    public void testDeleteLabour_ExistingAndThenDelete() {
        List<Labour> initialLabours = labourService.getAll();
        int initialSize = initialLabours.size();
        boolean existsBeforeDeletion = initialLabours.stream().anyMatch(l -> l.getId().equals("L2"));
        assertTrue("Labour with ID L2 should exist before deletion", existsBeforeDeletion);
        labourService.delete("L2");
        List<Labour> laboursAfterDeletion = labourService.getAll();
        assertEquals("Size of labour list should decrease by 1 after deletion", initialSize - 1, laboursAfterDeletion.size());
        boolean existsAfterDeletion = laboursAfterDeletion.stream().anyMatch(l -> l.getId().equals("L2"));
        assertFalse("Labour with ID L2 should not exist after deletion", existsAfterDeletion);
    }

    //Functionality-based characteristic
    //Test case T4 (C2b2)
    //Deleting a labour that does not exist -> Unable to delete labour
    //"9999"
    @Test
    public void testDeleteLabour_NonExistentId() {
        List<Labour> initialLabours = labourService.getAll();
        int initialSize = initialLabours.size();
        boolean existsBeforeDeletion = initialLabours.stream().anyMatch(l -> l.getId().equals("9999"));
        assertFalse("Labour with ID 9999 should not exist before deletion", existsBeforeDeletion);
        labourService.delete("9999");
        List<Labour> laboursAfterDeletion = labourService.getAll();
        assertEquals("Size of labour list should remain the same after attempting to delete a non-existent ID", initialSize, laboursAfterDeletion.size());
    }

}