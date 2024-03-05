package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.Values2D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataUtilitiesTest extends DataUtilities {
    
    private Values2D values2D;
    private double[] doubleArray;
    private double[][] doubleArray2D;

    @Before
    public void setUp() {
        initializeValues();
        initializeArrays();
    }

    private void initializeValues() {
        DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
        values2D = testValues;
        testValues.addValue(1, 0, 0);
        testValues.addValue(4, 1, 0);
        testValues.addValue(7, 0, 1);
        testValues.addValue(9, 1, 1);
    }

    private void initializeArrays() {
        double[] testArray = {1, 2, 3};
        doubleArray = testArray;

        double[][] testArray2D = {
                {1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {7.0, 8.0, 9.0}
        };
        doubleArray2D = testArray2D;
    }

    @After
    public void tearDown() {
        values2D = null;
        doubleArray = null;
        doubleArray2D = null;
    }

    @Test
    public void testValidDataAndColumnTotal() {
        assertEquals("Wrong sum returned for column total.", 5.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
    }

    @Test
    public void testNullDataColumnTotal() {
        try {
            DataUtilities.calculateColumnTotal(null, 0);
            fail("Expected IllegalArgumentException for null data.");
        } catch (IllegalArgumentException e) {
            assertTrue("Incorrect exception type thrown", true);
        }
    }

    @Test
    public void testValidDataAndRowTotal() {
        assertEquals("Wrong sum returned for row total.", 13, DataUtilities.calculateRowTotal(values2D, 1), 0.0000001d);
    }

    @Test
    public void testNullDataRowTotal() {
        try {
            DataUtilities.calculateRowTotal(null, 0);
            fail("Expected IllegalArgumentException for null data.");
        } catch (IllegalArgumentException e) {
            assertTrue("Incorrect exception type thrown", true);
        }
    }

    @Test
    public void testCreateNumberArrayValidData() {
        Number[] noArray = DataUtilities.createNumberArray(doubleArray);
        assertDoubleAndNumberArraysEqual("Number and Double arrays do not match", doubleArray, noArray);
    }

    @Test
    public void testCreateNumberArrayNoData() {
        try {
            DataUtilities.createNumberArray(null);
            fail("Expected IllegalArgumentException for null data.");
        } catch (IllegalArgumentException e) {
            assertTrue("Incorrect exception type thrown", true);
        }
    }

    @Test
    public void testNumberArray2DCreationFrom2DDoubleArray() {
        Number[][] noArray2D = DataUtilities.createNumberArray2D(doubleArray2D);
        assert2DDoubleAndNumberArraysEqual("Number and Double arrays do not match", doubleArray2D, noArray2D);
    }

    @Test
    public void testCreateNumberArray2DNoData() {
        try {
            DataUtilities.createNumberArray2D(null);
            fail("Expected IllegalArgumentException for null data.");
        } catch (IllegalArgumentException e) {
            assertTrue("Incorrect exception type thrown", true);
        }
    }

    @Test
    public void testGetCulminativePercentagesValidData() {
        DefaultKeyedValues keyValues = new DefaultKeyedValues();
        keyValues.addValue("0", 5.0);
        keyValues.addValue("1", 9.0);
        keyValues.addValue("2", 2.0);

        assertEquals("Wrong cumulative percentage returned.", 0.3125,
                DataUtilities.getCumulativePercentages(keyValues).getValue("0").doubleValue(), 0.0000001d);
    }

    @Test
    public void testGetCulminativePercentagesNullData() {
        try {
            DataUtilities.getCumulativePercentages(null);
            fail("Expected IllegalArgumentException for null data.");
        } catch (IllegalArgumentException e) {
            assertTrue("Incorrect exception type thrown", true);
        }
    }

    private static void assertDoubleAndNumberArraysEqual(String message, double[] doubles, Number[] numbers) {
        assertTrue("Arrays do not have the same length.", doubles.length == numbers.length);

        try {
            for (int i = 0; i < doubles.length; i++) {
                assertEquals(message, doubles[i], numbers[i].doubleValue(), 0.0000001d);
            }
        } catch (Exception e) {
            throw new AssertionError(message);
        }
    }

    private static void assert2DDoubleAndNumberArraysEqual(String message, double[][] doubles, Number[][] numbers) {
        assertTrue("Arrays do not have the same number of rows.", doubles.length == numbers.length);

        try {
            for (int i = 0; i < doubles.length; i++) {
                assertTrue("Row " + i + " does not have the same length.", doubles[i].length == numbers[i].length);

                for (int j = 0; j < doubles[i].length; j++) {
                    if (numbers[i][j] == null || doubles[i][j] != numbers[i][j].doubleValue()) {
                        throw new AssertionError(message + " Mismatch found at row " + i + ", column " + j + ".");
                    }
                }
            }
        } catch (Exception e) {
            throw new AssertionError(message);
        }
    }
}
