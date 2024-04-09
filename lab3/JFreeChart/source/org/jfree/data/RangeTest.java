package org.jfree.data;

import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RangeTest {
    
    private Range rangeObjectUnderTest;
    
    @Before
    public void setUp() throws Exception {
        rangeObjectUnderTest = new Range(3, 5);
    }
    
    // Existing test cases...

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
