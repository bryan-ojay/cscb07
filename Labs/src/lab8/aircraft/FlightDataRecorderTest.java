//Partners: Bryan Oladeji (oladejib) and Moe Ali (alimuh53)
package lab8.aircraft;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

import org.junit.Test;

public class FlightDataRecorderTest {
	public FlightDataRecorder flight;

	public void setUp() {
		this.flight = new FlightDataRecorder();
	}


	/**
	 * Test if average of empty FDR returns 0.
	 */
	@Test
	public void testAverageOfNewFDR() {
		setUp();
		double result = flight.average();
		assertEquals(0d, result, 0.001);	
	}

	/**
	 * Test if the correct average of a non-empty FDR is given
	 */
	@Test
	public void testAverageOfNonEmptyFDR() {
		//this method will give a bug
		setUp();
		flight.record(1d);
		flight.record(2d);
		flight.record(3d);
		double expected = 2d;
		double result = flight.average();
		assertEquals(expected, result, 0.001);
	}

	/**
	 * Test if FDR can record more than "CAPACITY" elements 
	 * (should override first inserted elements if greater than CAPACITY)
	 */
	@Test
	public void testRecordingTooManyElementsFDR() {
		setUp();
		//this method will give a bug

		//insert CAPACITY elements in mock list (first element is overwritten)
		List<Double> expected = new ArrayList<Double>();
		expected.add(2d);
		for (int i = 0; i < flight.CAPACITY - 1; i++) {
			expected.add(1d);
		}


		//insert CAPACITY + 1 elements into FDR
		for (int i = 0; i < flight.CAPACITY; i++) {
			flight.record(1d);
		}
		flight.record(2d);
		List<Double> result = flight.getRecordedData();	

		assertEquals(expected, result);	
	}

	/**
	 * Test if all the data points are received from getDataPoints.
	 */
	public void testgetDataPoints() {
		setUp();
		//insert elements into mock list (in reverse)
		List<Double> expected = new ArrayList<Double>();
		expected.add(1d);
		expected.add(2d);
		expected.add(3d);

		//insert elements into FDR
		flight.record(1d);
		flight.record(2d);
		flight.record(3d);
		List<Double> result = flight.getRecordedData();

		assertEquals(expected, result);	

	}

	/**
	 * Test if "lastDataPoints" returns all of the elements in the list if
	 * the the parameter is bigger than the length of the list 
	 */
	@Test
	public void testGetMoreDataPointsThanInFDR() {
		setUp();
		//insert elements into mock list (in reverse)
		List<Double> expected = new ArrayList<Double>();
		expected.add(3d);
		expected.add(2d);
		expected.add(1d);

		//insert elements into FDR
		flight.record(1d);
		flight.record(2d);
		flight.record(3d);
		List<Double> result = flight.getLastDataPoints(10);

		assertEquals(expected, result);	
	}

	/**
	 * Test if "lastDataPoints" and "getRecordedData" return correctly after
	 * being called together.
	 */
	@Test
	public void testGetLastDataThenGetData() {
		//this method will give a bug
		setUp();
		//insert CAPACITY + 1 elements into FDR
		for (int i = 0; i < flight.CAPACITY; i++) {
			flight.record((double) i);
		}
		List<Double> lastData = flight.getLastDataPoints(3);
		List<Double> getData = flight.getRecordedData();

		List<Double> expected = new ArrayList<Double>();
		for (int i = 0; i < flight.CAPACITY; i++) {
			expected.add((double) i);
		}

		List<Double> expected2 = expected.subList(flight.CAPACITY - 3, 
				flight.CAPACITY);

		assertEquals(expected, getData); //getRecordedData
		assertEquals(expected2, lastData); //getLastData
	}
	
	/**
	 * Test if "lastDataPoints" returns the same thing if called twice.
	 */
	@Test
	public void testGetLastDataTwice() {
		//this method will give a bug
		setUp();
		//insert CAPACITY + 1 elements into FDR
		for (int i = 0; i < flight.CAPACITY; i++) {
			flight.record((double) i);
		}
		//getLastDataTwice
		List<Double> lastData = flight.getLastDataPoints(3);
		List<Double> lastData2 = flight.getLastDataPoints(3);

		//created expected list
		List<Double> expected = new ArrayList<Double>();
		expected.add(9d);
		expected.add(8d);
		expected.add(7d);

		assertEquals(expected, lastData); //getLastData
		assertEquals(expected, lastData2); //getLastData2
	}
	
}

//Bugs:
//- average is wrong, returns the total/CAPACITY instead of total/[num of items]

//- if you try to record more than CAPACITY items, 
//  the items won't get replaced, it will throw an error

//- getLastData moves the "tail", a.k.a the data marker, in the arraylist.

//  This gives 2 bugs, if getLastData is called before getRecordedData, it
//  will alter the value that getRecordedData returns.
//  If getLastData is called twice, it does not return the same thing both
//  times (unless the parameter given is equal to flight.CAPACITY)
	

