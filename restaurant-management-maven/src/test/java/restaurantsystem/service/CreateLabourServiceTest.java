/* Copyright (C) 2024 Russarin Eaimrittikrai, Supithcha Jongphoemwatthanaphon,
 * Sasasuang Pattanakitjaroenchai, Chaninan Phetpangun, Runchida Ananartyasit,
 * Phacharaphan Chalitchaiya, Pimmada Chompurat - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MUICT Echo license.
 */

/*
 This file includes test suite: testCreateLabour
*/
package restaurantsystem.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import restaurantsystem.model.Labour;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CreateLabourServiceTest {
    private LabourService labourService;
    private static final String FILE_PATH = "storage/labour.txt";
    private static final String INITIAL_CONTENT = "0111,Shahin,2500.0\n3332,Mahmud,1000.0\n";

    @Before
    public void setUp() throws IOException {
        labourService = new LabourService();
        resetFileContent();  // Ensure the file starts with initial content
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
    // Test Case T1 (C1b2, C2b2, C3b1, C4b2)
    // Creating a new Labour with not null id, empty name, and salary of zero
    // create(Labour(“0011”, "", 0.0))
    @Test
    public void testCreateLabour_EmptyNameZeroSalary() throws IOException {
        Labour labour = new Labour("0011", "", 0.0);
        labourService.create(labour);

        // Verify that this labour entry is NOT created in the file
        List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
        assertFalse("Expected labour with empty name and zero salary NOT to be created.",
                lines.contains("0011,,0.0"));
    }

    // Interface-based characteristic
    // Test Case T2 (C1b2, C2b2, C3b2, C4b3)
    // Creating a new Labour with not null id, name, and salary
    // create(Labour(“0011”, "John", 5000.0))
    @Test
    public void testCreateLabour_ValidNamePositiveSalary() throws IOException {
        Labour labour = new Labour("0011", "John", 5000.0);
        labourService.create(labour);

        // Verify that this labour entry IS created in the file
        List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
        assertTrue("Expected labour with valid name and positive salary to be created.",
                lines.contains("0011,John,5000.0"));
    }

    // Interface-based characteristic
    // Test Case T3 (C1b2, C2b2, C3b2, C4b4)
    // Creating a new Labour with not null id and valid name, but NaN salary
    // create(Labour(“0011”, "John", NaN))
    @Test
    public void testCreateLabour_ValidNameNaNSalary() throws IOException {
        Labour labour = new Labour("0011", "John", Double.NaN);
        labourService.create(labour);

        // Verify that this labour entry is NOT created in the file
        List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
        assertFalse("Expected labour with NaN salary NOT to be created.",
                lines.contains("0011,John,NaN"));
    }

    // Functionality-based characteristic
    // Test Case T4 (C1,b1)
    // Creating a new Labour with Labour’s id exists in the file
    // create(Labour(“0011”, "John Doe", 3000.0))
    // The file storage/labour.txt has data: 0111,Shahin,2500.0
    @Test
    public void testCreateLabour_IdExistsInFile() throws IOException {
        Labour labour1 = new Labour("0011", "John Doe", 3000.0);
        labourService.create(labour1);

        // Attempt to add another labour with the same id "0011"
        Labour duplicateLabour = new Labour("0011", "John Doe", 3000.0);
        labourService.create(duplicateLabour);

        // Read the file contents
        List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));

        // Verify that there is only one entry for id "0011"
        long count = lines.stream().filter(line -> line.startsWith("0011,")).count();
        assertTrue("Expected only one entry for labour id '0011'", count == 1);
    }

    // Functionality-based characteristic
    // Test Case T5 (C1,b2)
    // Creating a new Labour with Labour’s id not exists in the file
    // create(Labour(“5555”, "JanJa", 5555.0))
    // The file storage/labour.txt has data: 0111,Shahin,2500.0
    @Test
    public void testCreateLabour_IdDoesNotExistInFile() throws IOException {
        Labour labour = new Labour("5555", "JanJa", 5555.0);
        labourService.create(labour);

        // Read the file contents
        List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));

        // Verify that the entry for id "5555" was added to the file
        boolean entryExists = lines.contains("5555,JanJa,5555.0");
        assertTrue("Expected the labour with id '5555' to be added to the file.", entryExists);
    }
}