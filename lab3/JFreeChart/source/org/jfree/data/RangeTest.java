package org.jfree.data;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RangeTest {

	private Range rangeObjectUnderTest;
	private Range expandRangeObjectUnderTest;
	
	@Before
	public void setUp() throws Exception {;
		rangeObjectUnderTest = new Range(3, 5);
		expandRangeObjectUnderTest = new Range(2, 6);
	}
	
	@Test
	public void testRangeConstructorReturnsExpectedErrorGivenLowerGreaterThanUpper() {
		try{
			rangeObjectUnderTest = new Range(5, 3);
			fail("No exception thrown. The expected outcome was an exception of type IllegalArgumentException.");
		}
		catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test
    public void testGetLengthMethodReturnsCorrectLength() {
		assertEquals("Failure: Returns incorrect value when should return 3.", 
				3.0, rangeObjectUnderTest.getLength(), 0.0000001d);
	}
	
	@Test
    public void testGetCentralValueMethodReturnsCorrectValue() {
		assertEquals("Failure: Returns incorrect value when should return 4.", 
				4.0, rangeObjectUnderTest.getCentralValue(), 0.0000001d);
	}
	
	@Test
    public void testExpandMethodWorksWithValidParameters() {
		Range.expand(expandRangeObjectUnderTest, 0.25, 0.5);
		assertTrue("Failure: Expanded range does not match expected range of (1, 8)", expandRangeObjectUnderTest.equals(new Range(1,8)));
	}
    @Test
    public void testExpandMethodThrowsExpectedExceptionWithNullRange() {
        try {
            Range.expand(null, 0.25, 0.5);
            fail("No exception thrown. The expected outcome was an exception of type: InvalidParameterException");
        } catch (Exception e) {
            assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
        }
    }
    
	@Test 
	public void testExpandMethodThrowsExpectedExceptionWithInvalidLowerParameters() {

		try {
			Range.expand(expandRangeObjectUnderTest, -0.5, 0.5);
			fail("No exception thrown. The expected outcome was an exception of type: InvalidParameterException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
		}

	}
    @Test
    public void testExpandMethodThrowsExpectedExceptionWithInvalidUpperParameters() {

        try {
            Range.expand(expandRangeObjectUnderTest, 0.5, -0.5);
            fail("No exception thrown. The expected outcome was an exception of type: InvalidParameterException");
        } catch (Exception e) {
            assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
        }
    }

	@Test 
	public void testExpandMethodReturnsCorrectOutputWithMininumLowerParameter() {
		Range.expand(expandRangeObjectUnderTest, 0, 0.5);
		assertTrue("Failure: Expanded range does not match expected range of (2, 8)", expandRangeObjectUnderTest.equals(new Range(2,8)));
	}
	
	@Test
	public void testExpandMethodReturnsCorrectOutputWithMininumUpperParameter() {
		Range.expand(expandRangeObjectUnderTest, 0.0, 0.0);
		assertTrue("Failure: Expanded range does not match expected range of (2, 6)", expandRangeObjectUnderTest.equals(new Range(2, 6)));
	}

	@Test 
	public void testExpandMethodReturnsCorrectOutputWithMaximumLowerParameter() {
		Range.expand(expandRangeObjectUnderTest, 1.0, 0.5);
		assertTrue("Failure: Expanded range does not match expected range of (-2, 8)", expandRangeObjectUnderTest.equals(new Range(-2, 8)));
	}
	@Test
	public void testExpandMethodReturnsCorrectOutputWithMaximumUpperParameter() {
		Range.expand(expandRangeObjectUnderTest, 0.0, 1.0);
		assertTrue("Failure: Expanded range does not match expected range of (2, 10)", expandRangeObjectUnderTest.equals(new Range(2, 10)));
	}
	
	@Test
	public void testShiftMethodReturnsCorrectOutputWithPositiveInput() {
		rangeObjectUnderTest = Range.shift(rangeObjectUnderTest, 1, true);
		assertTrue("Failure: Shifted range does not match expected range of (4, 6)", rangeObjectUnderTest.equals(new Range(4, 6)));
	}
  
	@Test 
	public void testShiftMethodReturnsCorrectOutputWithNegativeInput() {
		rangeObjectUnderTest = Range.shift(rangeObjectUnderTest, -1, true);
		assertTrue("Failure: Shifted range does not match expected range of (2, 4)", rangeObjectUnderTest.equals(new Range(2, 4)));
	}
	
	@Test
    public void testShiftMethodThrowsExpectedExceptionWithNullInput() {
        try {
            rangeObjectUnderTest = Range.shift(null, 1, true);
            fail("No exception thrown. The expected outcome was an exception of type: InvalidParameterException");
        } catch (Exception e) {
            assertTrue("Incorrect exception type thrown", e.getClass().equals(InvalidParameterException.class));
        }
    }

	@Test
	public void testShiftMethodReturnsCorrectOutputWithPositiveInputCrossZeroAllow() {
		rangeObjectUnderTest = new Range(-5, -1);
		rangeObjectUnderTest = Range.shift(rangeObjectUnderTest, 2, true);
		assertTrue("Failure: Shifted range does not match expected range of (-3, 1)", rangeObjectUnderTest.equals(new Range(-3, 1)));
	}
	@Test
	public void testShiftMethodReturnsCorrectOutputWithPositiveInputCrossZeroNotAllow() {
		rangeObjectUnderTest = new Range(-5, -1);
		rangeObjectUnderTest = Range.shift(rangeObjectUnderTest, 2, false);
		assertTrue("Failure: Shifted range does not match expected range of (-3, 0)", rangeObjectUnderTest.equals(new Range(-3, 0)));
	}
	
	@Test
	public void testShiftMethodReturnsCorrectOutputWithNegativeInputCrossZeroAllow() {
		rangeObjectUnderTest = new Range(1, 5);
		rangeObjectUnderTest = Range.shift(rangeObjectUnderTest, -2, true);
		assertTrue("Failure: Shifted range does not match expected range of (-1, 3)", rangeObjectUnderTest.equals(new Range(-1, 3)));
	}
	@Test
	public void testShiftMethodReturnsCorrectOutputWithNegativeInputCrossZeroNotAllow() {
		rangeObjectUnderTest = new Range(1, 5);
		rangeObjectUnderTest = Range.shift(rangeObjectUnderTest, -2, false);
		assertTrue("Failure: Shifted range does not match expected range of (0, 3)", rangeObjectUnderTest.equals(new Range(0, 3)));
	}
	
	@Test
	public void testShiftMethodReturnsCorrectOutputWithZeroLowerCrossZeroNotAllow() {
		rangeObjectUnderTest = new Range(0, 1);
		rangeObjectUnderTest = Range.shift(rangeObjectUnderTest, 2, false);
		assertTrue("Failure: Shifted range does not match expected range of (2, 3)", rangeObjectUnderTest.equals(new Range(2, 3)));
	}
	
	@Test
	public void testShiftMethodReturnsCorrectOutputWithZeroUpperCrossZeroNotAllow() {
		rangeObjectUnderTest = new Range(-2, 0);
		rangeObjectUnderTest = Range.shift(rangeObjectUnderTest, 2, false);
		assertTrue("Failure: Shifted range does not match expected range of (0, 2)", rangeObjectUnderTest.equals(new Range(0, 2)));
	}
	
	@Test
	public void testBaseShiftMethodReturnsCorrectOutput() {
		rangeObjectUnderTest = new Range(1, 5);
		rangeObjectUnderTest = Range.shift(rangeObjectUnderTest, -2);
		assertTrue("Failure: Shifted range does not match expected range of (0, 3)", rangeObjectUnderTest.equals(new Range(0, 3)));
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
		assertFalse("Failure: Returns True when should return False.", rangeObjectUnderTest.equals(new Range(3, 6)));
	}
	
	@Test
    public void testConstrainMethodReturnsClosestValueGivenValueWithinRange() {
		assertEquals("Failure: Returns incorrect value when should return 4.", 
				4.0, rangeObjectUnderTest.constrain(4), 0.0000001d);
	}
	
	@Test
    public void testConstrainMethodReturnsClosestValueGivenValueBelowRange() {
		assertEquals("Failure: Returns incorrect value when should return 3.", 
				3.0, rangeObjectUnderTest.constrain(1), 0.0000001d);
	}
	@Test
    public void testConstrainMethodReturnsClosestValueGivenValueAboveRange() {
		assertEquals("Failure: Returns incorrect value when should return 5.", 
				5.0, rangeObjectUnderTest.constrain(6), 0.0000001d);
	}
	
	@Test
    public void testConstrainMethodReturnsClosestValueGivenValueOnLowerRangeBoundary() {
		assertEquals("Failure: Returns incorrect value when should return 3.", 
				3.0, rangeObjectUnderTest.constrain(3), 0.0000001d);
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
	
	@Test
    public void testHashCodeMethodReturnsInteger() {
		assertEquals("Failure: Returns incorrect data type when should return integer.", Integer.valueOf(rangeObjectUnderTest.hashCode()) instanceof Integer);
	}
    
    @Test
    public void testIntersectsMethodReturnsTrueGivenRangeWithin() {
        assertTrue("Failure: Returns False when should return True.", rangeObjectUnderTest.intersects(4, 4.5));
    }
    
    @Test
    public void testIntersectsMethodReturnsTrueGivenRangeOverlapsLowerBound() {
        assertTrue("Failure: Returns False when should return True.", rangeObjectUnderTest.intersects(2, 4));
    }
    
    @Test
    public void testIntersectsMethodReturnsTrueGivenRangeOverlapsUpperBound() {
        assertTrue("Failure: Returns False when should return True.", rangeObjectUnderTest.intersects(4.5, 6));
    }
    
    @Test
    public void testIntersectsMethodReturnsFalseGivenRangeBefore() {
        assertFalse("Failure: Returns True when should return False.", rangeObjectUnderTest.intersects(1, 2.5));
    }
    
    @Test
    public void testIntersectsMethodReturnsFalseGivenRangeAfter() {
        assertFalse("Failure: Returns True when should return False.", rangeObjectUnderTest.intersects(6, 8));
    }
    
    @Test
    public void testIntersectsMethodReturnsTrueGivenRangeSameAsLowerBound() {
        assertTrue("Failure: Returns False when should return True.", rangeObjectUnderTest.intersects(3, 4));
    }
    
    @Test
    public void testIntersectsMethodReturnsTrueGivenRangeSameAsUpperBound() {
        assertTrue("Failure: Returns False when should return True.", rangeObjectUnderTest.intersects(4, 5));
    }
    
    @Test
    public void testCombineReturnsNullWhenBothRangesAreNull() {
        Range combinedRange = Range.combine(null, null);
        assertNull("Failure: Combined range should be null when both input ranges are null.", combinedRange);
    }
    
    @Test
    public void testCombineReturnsRangeWhenOneRangeIsNull() {
        Range range1 = new Range(1, 3);
        Range combinedRange1 = Range.combine(range1, null);
        Range combinedRange2 = Range.combine(null, range1);
        
        assertEquals("Failure: Combined range should be equal to the non-null range.", range1, combinedRange1);
        assertEquals("Failure: Combined range should be equal to the non-null range.", range1, combinedRange2);
    }
    
    @Test
    public void testCombineReturnsCombinedRangeWhenBothRangesAreNonNull() {
        Range range1 = new Range(1, 3);
        Range range2 = new Range(4, 6);
        
        Range combinedRange = Range.combine(range1, range2);
        
        assertEquals("Failure: Combined range should have lower bound equal to the minimum of input ranges' lower bounds.", 1.0, combinedRange.getLowerBound(), 0.001);
        assertEquals("Failure: Combined range should have upper bound equal to the maximum of input ranges' upper bounds.", 6.0, combinedRange.getUpperBound(), 0.001);
    }
    
    @Test
    public void testExpandToIncludeReturnsRangeWithInputValueWhenInputRangeIsNull() {
        Range expandedRange = Range.expandToInclude(null, 10);
        assertNotNull("Failure: Expanded range should not be null.", expandedRange);
        assertEquals("Failure: Expanded range should have lower and upper bounds equal to the input value.", 10.0, expandedRange.getLowerBound(), 0.001);
        assertEquals("Failure: Expanded range should have lower and upper bounds equal to the input value.", 10.0, expandedRange.getUpperBound(), 0.001);
    }
    
    @Test
    public void testExpandToIncludeReturnsRangeWithInputValueAsLowerBoundWhenValueIsLessThanCurrentLowerBound() {
        Range expandedRange = Range.expandToInclude(rangeObjectUnderTest, 2);
        assertNotNull("Failure: Expanded range should not be null.", expandedRange);
        assertEquals("Failure: Expanded range should have input value as the new lower bound.", 2.0, expandedRange.getLowerBound(), 0.001);
        assertEquals("Failure: Expanded range should have upper bound unchanged.", 5.0, expandedRange.getUpperBound(), 0.001);
    }
    
    @Test
    public void testExpandToIncludeReturnsRangeWithInputValueAsUpperBoundWhenValueIsGreaterThanCurrentUpperBound() {
        Range expandedRange = Range.expandToInclude(rangeObjectUnderTest, 8);
        assertNotNull("Failure: Expanded range should not be null.", expandedRange);
        assertEquals("Failure: Expanded range should have input value as the new upper bound.", 8.0, expandedRange.getUpperBound(), 0.001);
        assertEquals("Failure: Expanded range should have lower bound unchanged.", 3.0, expandedRange.getLowerBound(), 0.001);
    }
    
    @Test
    public void testExpandToIncludeReturnsOriginalRangeWhenInputValueIsWithinTheRange() {
        Range expandedRange = Range.expandToInclude(rangeObjectUnderTest, 4.5);
        assertNotNull("Failure: Expanded range should not be null.", expandedRange);
        assertEquals("Failure: Expanded range should have the same lower bound.", 3.0, expandedRange.getLowerBound(), 0.001);
        assertEquals("Failure: Expanded range should have the same upper bound.", 5.0, expandedRange.getUpperBound(), 0.001);
    }
    
    @After
    public void tearDown() throws Exception {
        rangeObjectUnderTest = null;
    }
}
