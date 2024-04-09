package org.jfree.data;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.Values2D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataUtilitiesTest extends DataUtilities {
    
    private Values2D values;
    private double[] numbersArray;
    private double[][] doubleArrayNumbers;

    @Before
    public void setUp() {
        initializeValues();
        initializeArrays();
    }

    private void initializeValues() {
        DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
        values = testValues;
        testValues.addValue(1, 0, 0);
        testValues.addValue(4, 1, 0);
        testValues.addValue(7, 0, 1);
        testValues.addValue(5, 1, 1);
    }

    private void initializeArrays() {
        double[] testArray = {1, 2, 3};
        numbersArray = testArray;

        double[][] testArray2D = {
                {1.0, 2.0},
                {3.0, 4.0},
                
        };
        doubleArrayNumbers = testArray2D;
    }

    @After
    public void tearDown() {
        values = null;
        doubleArrayNumbers = null;
        numbersArray = null;
    }

    @Test
    public void testValidDataAndColumnTotal() {
        assertEquals("Wrong sum returned for column total.", 12, DataUtilities.calculateColumnTotal(values, 1), 0.0000001d);
    }
    
    
    @Test
    public void testValidDataWithNonNullValuesColumnTotal() {
        // Add non-null values in the column
        ((DefaultKeyedValues2D) values).setValue(1, 0, 0);
        ((DefaultKeyedValues2D) values).setValue(4, 1, 0);
        assertEquals("Wrong sum returned for column total with non-null values.", 5, DataUtilities.calculateColumnTotal(values, 0), 0.0000001d);
    }

    @Test
    public void testValidDataWithNullValuesColumnTotal() {
        // Add null values in the column
        ((DefaultKeyedValues2D) values).setValue(null, 0, 0);
        ((DefaultKeyedValues2D) values).setValue(null, 1, 0);
        assertEquals("Wrong sum returned for column total with null values.", 0, DataUtilities.calculateColumnTotal(values, 0), 0.0000001d);
    }
    
    @Test
    public void testValidDataAndRowTotalWithNonNullValues() {
        // Add non-null values in the row
        ((DefaultKeyedValues2D) values).setValue(1, 0, 0);
        ((DefaultKeyedValues2D) values).setValue(4, 0, 1);
        assertEquals("Wrong sum returned for row total with non-null values.", 5, DataUtilities.calculateRowTotal(values, 0), 0.0000001d);
    }

    @Test
    public void testValidDataAndRowTotalWithNullValues() {
        // Add null values in the row
        ((DefaultKeyedValues2D) values).setValue(null, 0, 0);
        ((DefaultKeyedValues2D) values).setValue(null, 0, 1);
        assertEquals("Wrong sum returned for row total with null values.", 0, DataUtilities.calculateRowTotal(values, 0), 0.0000001d);
    }

    @Test
    public void testNullDataColumnTotal() {
        try {
            DataUtilities.calculateColumnTotal(null, 0);
            fail("Expected IllegalArgumentException for null data.");
        } catch (Exception e) {
            assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
        }
    }

    @Test
    public void testValidDataAndRowTotal() {
        assertEquals("Wrong sum returned for row total.", 9, DataUtilities.calculateRowTotal(values, 1), 0.0000001d);
    }

    @Test
    public void testNullDataRowTotal() {
        try {
            DataUtilities.calculateRowTotal(null, 0);
            fail("Expected IllegalArgumentException for null data.");
        } catch (Exception e) {
            assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
        }
    }

    @Test
    public void testCreateNumberArrayValidData() {
        Number[] noArray = DataUtilities.createNumberArray(numbersArray);
        assertArrayEquals("Number and Double arrays do not match", numbersArray, noArray);
    }

    @Test
    public void testCreateNumberArrayNoData() {
        try {
            DataUtilities.createNumberArray(null);
            fail("Expected IllegalArgumentException for null data.");
        } catch (Exception e) {
            assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
        }
    }

    @Test
    public void testNumberArray2DCreationFrom2DDoubleArray() {
        Number[][] noArray = DataUtilities.createNumberArray2D(doubleArrayNumbers);
        assert2DArraysEqual("Number and Double arrays do not match", doubleArrayNumbers, noArray);
    }

    @Test
    public void testCreateNumberArray2DNoData() {
        try {
            DataUtilities.createNumberArray2D(null);
            fail("Expected IllegalArgumentException for null data.");
        } catch (Exception e) {
            assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
        }
    }

    @Test
    public void testGetCumulativePercentagesWithNullValue() {
        DefaultKeyedValues keyValues = new DefaultKeyedValues();
        keyValues.addValue("0", 5.0);
        keyValues.addValue("1", null); // Add a null value
        keyValues.addValue("2", 2.0);

        KeyedValues cumulativePercentages = DataUtilities.getCumulativePercentages(keyValues);

        assertEquals("Wrong cumulative percentage returned for key '0'.", 0.625,
                cumulativePercentages.getValue("0").doubleValue(), 0.0000001d);
        assertEquals("Wrong cumulative percentage returned for key '1'.", 0.625,
                cumulativePercentages.getValue("1").doubleValue(), 0.0000001d); // Branch with null value
        assertEquals("Wrong cumulative percentage returned for key '2'.", 1.0,
                cumulativePercentages.getValue("2").doubleValue(), 0.0000001d);
    }


    @Test
    public void testGetCumulativePercentagesWithNullData() {
        try {
            DataUtilities.getCumulativePercentages(null);
            fail("Expected IllegalArgumentException for null data.");
        } catch (Exception e) {
            assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
        }
    }

    private static void assertArrayEquals(String message, double[] doubles, Number[] numbers) {
        assertTrue("Arrays do not have the same length.", doubles.length == numbers.length);

        try {
            for (int i = 0; i < doubles.length; i++) {
                assertEquals(message, doubles[i], numbers[i].doubleValue(), 0.0000001d);
            }
        } catch (Exception e) {
            throw new AssertionError(message);
        }
    }

    private static void assert2DArraysEqual(String message, double[][] doubles, Number[][] numbers) {
        assertTrue("Arrays aren't equal.", doubles.length == numbers.length);

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
