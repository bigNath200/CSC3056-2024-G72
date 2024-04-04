package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RangeTest {
	
	private Range rangeObjectUnderTest;
	
	@Before
	public void setUp() throws Exception {;
		rangeObjectUnderTest = new Range(1, 5);
	}
	
	@Test
    public void testEqualsMethodReturnsTrueGivenIdenticalRangeObject() {
		assertTrue("Failure: Returns False when should return True.", rangeObjectUnderTest.equals(new Range(1, 5)));
	}
	
	@Test
    public void testEqualsMethodReturnsFalseGivenNonIdenticalRangeObject() {
		assertFalse("Failure: Returns True when should return False.", rangeObjectUnderTest.equals(new Range(1, 4)));

	}
	
	@Test
    public void testEqualsMethodReturnsFalseGivenDifferentJavaObject() {
		assertFalse("Failure: Returns True when should return False.", rangeObjectUnderTest.equals(new String("test")));
	}
	
	@Test
    public void testEqualsMethodReturnsFalseGivenNullValue() {
		assertFalse("Failure: Returns True when should return False.", rangeObjectUnderTest.equals(null));
	}
	
	@Test
    public void testEqualsMethodReturnsFalseGivenSameUpperValueDifferentLowerValue() {
		assertFalse("Failure: Returns True when should return False.", rangeObjectUnderTest.equals(new Range(3, 5)));
	}
	
	@Test
    public void testEqualsMethodReturnsFalseGivenSameLowerValueDifferentUpperValue() {
		rangeObjectUnderTest = new Range(3, 5);
		assertFalse("Failure: Returns True when should return False.", rangeObjectUnderTest.equals(new Range(3, 6)));
	}
	
	@Test
    public void testConstrainMethodReturnsClosestValueGivenValueWithinRange() {
		assertEquals("Failure: Returns incorrect value when should return 4.", 
				4.0, rangeObjectUnderTest.constrain(4), 0.0000001d);
	}
	@Test
    public void testConstrainMethodReturnsClosestValueGivenValueBelowRange() {
		assertEquals("Failure: Returns incorrect value when should return 1.", 
				1.0, rangeObjectUnderTest.constrain(0), 0.0000001d);
	}
	@Test
    public void testConstrainMethodReturnsClosestValueGivenValueAboveRange() {
		assertEquals("Failure: Returns incorrect value when should return 5.", 
				5.0, rangeObjectUnderTest.constrain(6), 0.0000001d);
	}
	
	@Test
    public void testConstrainMethodReturnsClosestValueGivenValueOnLowerRangeBoundary() {
		assertEquals("Failure: Returns incorrect value when should return 1.", 
				1.0, rangeObjectUnderTest.constrain(1), 0.0000001d);
	}
	
	@Test
    public void testConstrainMethodReturnsClosestValueGivenValueOnUpperRangeBoundary() {
		assertEquals("Failure: Returns incorrect value when should return 5.", 
				5.0, rangeObjectUnderTest.constrain(5), 0.0000001d);
	}
	
	@Test
    public void testConstrainMethodReturnsClosestValueGivenNegativeRangeValueAboveRange() {
		rangeObjectUnderTest = new Range(-5, -1);
		assertEquals("Failure: Returns incorrect value when should return -1.", 
				-1.0, rangeObjectUnderTest.constrain(1), 0.0000001d);
	}
	
	@Test
    public void testConstrainMethodReturnsClosestValueGivenNegativeRangeValueBelowRange() {
		rangeObjectUnderTest = new Range(-5, -1);
		assertEquals("Failure: Returns incorrect value when should return -5.", 
				-5.0, rangeObjectUnderTest.constrain(-6), 0.0000001d);
	}
	
	@Test
    public void testConstrainMethodReturnsClosestValueGivenNegativeRangeValueInRange() {
		rangeObjectUnderTest = new Range(-5, -1);
		assertEquals("Failure: Returns incorrect value when should return -3.", 
				-3.0, rangeObjectUnderTest.constrain(-3), 0.0000001d);
	}
	
	
	@Test
    public void testGetLowerBoundMethodReturnsCorrectOutputGivenRangeWithLowerLessThanUpper() {
		assertEquals("Failure: Returns incorrect value when should return 1.", 
				1.0, rangeObjectUnderTest.getLowerBound(), 0.0000001d);
	}
	
	@Test
    public void testGetLowerBoundMethodReturnsCorrectOutputGivenRangeWithLowerEqualToUpper() {
		rangeObjectUnderTest = new Range(1, 1);
		assertEquals("Failure: Returns incorrect value when should return 1.", 
				1.0, rangeObjectUnderTest.getLowerBound(), 0.0000001d);
	}
	
	@Test
    public void testGetLowerBoundMethodReturnsCorrectOutputGivenRangeWithNegativeLower() {
		rangeObjectUnderTest = new Range(-1, 5);
		assertEquals("Failure: Returns incorrect value when should return -1.", 
				-1.0, rangeObjectUnderTest.getLowerBound(), 0.0000001d);
	}
	
	
	@Test
    public void testGetUpperBoundMethodReturnsCorrectOutputGivenRangeWithLowerLessThanUpper() {
		assertEquals("Failure: Returns incorrect value when should return 5.", 
				5.0, rangeObjectUnderTest.getUpperBound(), 0.0000001d);
	}
	
	@Test
    public void testGetUpperBoundMethodReturnsCorrectOutputGivenRangeWithLowerEqualToUpper() {
		rangeObjectUnderTest = new Range(1, 1);
		assertEquals("Failure: Returns incorrect value when should return 1.", 
				1.0, rangeObjectUnderTest.getUpperBound(), 0.0000001d);
	}
	
	@Test
    public void testGetUpperBoundMethodReturnsCorrectOutputGivenRangeWithNegativeUpper() {
		rangeObjectUnderTest = new Range(-5, -1);
		assertEquals("Failure: Returns incorrect value when should return -1.", 
				-1.0, rangeObjectUnderTest.getUpperBound(), 0.0000001d);
	}
	
	@Test
    public void testToStringMethodReturnsCorrectOutputGivenRangeWithLowerLessThanUpper() {
		assertEquals("Failure: Returns incorrect value when should return Range[1.0,5.0].", 
				"Range[1.0,5.0]", rangeObjectUnderTest.toString());
	}
	
	@Test
    public void testToStringMethodReturnsCorrectOutputGivenRangeWithLowerEqualToUpper() {
		rangeObjectUnderTest = new Range(1, 1);
		assertEquals("Failure: Returns incorrect value when should return Range[1.0,1.0].", 
				"Range[1.0,1.0]", rangeObjectUnderTest.toString());
	}
	
	@Test
    public void testToStringMethodReturnsCorrectOutputGivenRangeWithNegativeNumbers() {
		rangeObjectUnderTest = new Range(-5, -1);
		assertEquals("Failure: Returns incorrect value when should return Range[-5.0,-1.0].", 
				"Range[-5.0,-1.0]", rangeObjectUnderTest.toString());
	}
	
	@Test
    public void testToStringMethodReturnsCorrectOutputGivenRangeWithNegativeLowerPositiveUpper() {
		rangeObjectUnderTest = new Range(-5, 1);
		assertEquals("Failure: Returns incorrect value when should return Range[-5.0,1.0].", 
				"Range[-5.0,1.0]", rangeObjectUnderTest.toString());
	}
	
	@Test
    public void testToStringMethodReturnsCorrectOutputGivenRangeWithBothNegativeAndEqual() {
		rangeObjectUnderTest = new Range(-1, -1);
		assertEquals("Failure: Returns incorrect value when should return Range[-1.0,-1.0].", 
				"Range[-1.0,-1.0]", rangeObjectUnderTest.toString());
	}
	
	@After
	public void tearDown() throws Exception {
		rangeObjectUnderTest = null;

	}


}
