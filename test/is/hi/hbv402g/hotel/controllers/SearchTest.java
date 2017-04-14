package is.hi.hbv402g.hotel.controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import is.hi.hbv402g.hotel.db.MockDataManager;
import is.hi.hbv402g.hotel.models.Hotel;
import is.hi.hbv402g.hotel.models.Room;

public class SearchTest
{
	// Id of hotels and rooms are in the order in which they are defined
	// Each hotel has 1 room 
	private Hotel oneStarAmenityLessHotel;
	private Hotel threeStarOnlyWiFiHotel;
	private Hotel fiveStarWiFiAndRoomServiceHotel;
	private Hotel WifiRoomServiceAndBreakfastHotel;
	private Room lowCostSingleDoubleBedRoom;
	private Room mediumCostSingleDoubleBedRoom;
	private Room highCostSingleDoubleBedRoom;
	private Room tenSingleDoubleBedEnSuiteRoom;
	private Room twentySingleDoubleBedNonSuiteRoom;
	private Room thirtySingleDoubleBedRoom;
	
	@Before
	public void setUp() throws Exception
	{
		oneStarAmenityLessHotel = new Hotel(1);
		oneStarAmenityLessHotel.setStarCount(1);
		oneStarAmenityLessHotel.setAmenities(new ArrayList<String>());
		oneStarAmenityLessHotel.setName("AaaHotel");
		
		threeStarOnlyWiFiHotel = new Hotel(2);
		threeStarOnlyWiFiHotel.setStarCount(3);
		threeStarOnlyWiFiHotel.setAmenities(new ArrayList<>(
				Arrays.asList("wifi")));
		threeStarOnlyWiFiHotel.setName("BbbHotel");
		
		fiveStarWiFiAndRoomServiceHotel = new Hotel(3);
		fiveStarWiFiAndRoomServiceHotel.setStarCount(5);
		fiveStarWiFiAndRoomServiceHotel.setAmenities(
				new ArrayList<>(Arrays.asList("wifi", "roomService")));
		fiveStarWiFiAndRoomServiceHotel.setName("CccHotel");
		
		WifiRoomServiceAndBreakfastHotel = new Hotel(4);
		WifiRoomServiceAndBreakfastHotel.setAmenities(new ArrayList<>(
				Arrays.asList("wifi", "roomService", "breakfast")));
		
		lowCostSingleDoubleBedRoom = new Room(1);
		lowCostSingleDoubleBedRoom.setCostPerNight(1000);
		lowCostSingleDoubleBedRoom.setNumberOfSingleBeds(1);
		lowCostSingleDoubleBedRoom.setNumberOfDoubleBeds(1);
		lowCostSingleDoubleBedRoom.setHotel(oneStarAmenityLessHotel);
		
		mediumCostSingleDoubleBedRoom = new Room(2);
		mediumCostSingleDoubleBedRoom.setCostPerNight(5000);
		mediumCostSingleDoubleBedRoom.setNumberOfSingleBeds(2);
		mediumCostSingleDoubleBedRoom.setNumberOfDoubleBeds(2);
		mediumCostSingleDoubleBedRoom.setHotel(threeStarOnlyWiFiHotel);
		
		highCostSingleDoubleBedRoom = new Room(3);
		highCostSingleDoubleBedRoom.setCostPerNight(11000);
		highCostSingleDoubleBedRoom.setNumberOfSingleBeds(3);
		highCostSingleDoubleBedRoom.setNumberOfDoubleBeds(3);
		highCostSingleDoubleBedRoom.setHotel(fiveStarWiFiAndRoomServiceHotel);
		
		tenSingleDoubleBedEnSuiteRoom = new Room(4);
		tenSingleDoubleBedEnSuiteRoom.setNumberOfSingleBeds(10);
		tenSingleDoubleBedEnSuiteRoom.setNumberOfDoubleBeds(10);
		tenSingleDoubleBedEnSuiteRoom.setEnSuiteBathroom(true);
		tenSingleDoubleBedEnSuiteRoom.setHotel(WifiRoomServiceAndBreakfastHotel);
		
		twentySingleDoubleBedNonSuiteRoom = new Room(5);
		twentySingleDoubleBedNonSuiteRoom.setNumberOfSingleBeds(20);
		twentySingleDoubleBedNonSuiteRoom.setNumberOfDoubleBeds(20);
		twentySingleDoubleBedNonSuiteRoom.setEnSuiteBathroom(false);
		
		thirtySingleDoubleBedRoom = new Room(6);
		thirtySingleDoubleBedRoom.setNumberOfSingleBeds(30);
		thirtySingleDoubleBedRoom.setNumberOfDoubleBeds(30);
		
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testFindReturnsMockData()
	{
		// This test checks that the mock data manager returns the list of
		// rooms

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(lowCostSingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		ArrayList<Room> foundRooms = search.find(null, null, null, null);

		// Check results
		assertNotNull(foundRooms);
		assertEquals(1, foundRooms.size());
		assertEquals(1, foundRooms.get(0).getId());
	}

	@Test
	public void testFilterByAmenities()
	{
		// This test checks if amenities filter works correctly

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(
						lowCostSingleDoubleBedRoom, 
						mediumCostSingleDoubleBedRoom, 
						highCostSingleDoubleBedRoom, 
						tenSingleDoubleBedEnSuiteRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		search.addAmenity("wifi");
		search.addAmenity("roomService");
		ArrayList<Room> filteredRooms = search.filter();

		// Check results
		assertNotNull(filteredRooms);
		assertEquals(2, filteredRooms.size());

		boolean exactMatch = false;
		boolean greaterSet = false;

		for (Room r : filteredRooms)
		{
			if (r.getId() == 3)
				exactMatch = true;
			if (r.getId() == 4)
				greaterSet = true;
		}
		assertTrue("Could not find exact match", exactMatch);
		assertTrue("Could not find greater set", greaterSet);
	}

	@Test
	public void testRemoveAmenities()
	{
		// This test checks if amenities filter works correctly
		
		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(mediumCostSingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		ArrayList<Room> availableRooms = search.find("hotel1", null, null, null);
		search.addAmenity("wifi");
		search.removeAmenity("wifi");
		ArrayList<Room> filteredRooms = search.filter();

		// Check results
		assertNotNull(filteredRooms);
		assertEquals(availableRooms.size(), filteredRooms.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegativePriceRangeException()
	{
		// This test checks if negative price range exception works correctly

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(mediumCostSingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		Integer min = -5000;
		Integer max = 10000;
		search.setPriceRange(min, max);
		search.filter();
	}

	@Test
	public void testFilterMinimumPrice()
	{
		// This test checks if price rooms filtered by minimum price only 
		// works correctly

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(
						lowCostSingleDoubleBedRoom, 
						mediumCostSingleDoubleBedRoom, 
						highCostSingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		search.setPriceRange(3000, null);
		ArrayList<Room> filteredRooms = search.filter();

		// Check results
		assertNotNull(filteredRooms);
		assertEquals(2, filteredRooms.size());

		boolean minPricedRoom = false;
		boolean midPricedRoom = false;
		boolean maxPricedRoom = false;

		for (Room r : filteredRooms)
		{
			if (r.getId() == 1)
				minPricedRoom = true;
			if (r.getId() == 2)
				midPricedRoom = true;
			if (r.getId() == 3)
				maxPricedRoom = true;
		}
		assertFalse("Found least expesive room", minPricedRoom);
		assertTrue("Could not find medium priced room", midPricedRoom);
		assertTrue("Could not find most expensive room", maxPricedRoom);
	}

	@Test
	public void testFilterMaximumPrice()
	{
		// This test checks if price rooms filtered by minimum price only works
		// correctly

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(
						lowCostSingleDoubleBedRoom, 
						mediumCostSingleDoubleBedRoom, 
						highCostSingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		search.setPriceRange(null, 7000);
		ArrayList<Room> filteredRooms = search.filter();

		// Check results
		assertNotNull(filteredRooms);
		assertEquals(2, filteredRooms.size());

		boolean minPricedRoom = false;
		boolean midPricedRoom = false;
		boolean maxPricedRoom = false;

		for (Room r : filteredRooms)
		{
			if (r.getId() == 1)
				minPricedRoom = true;
			if (r.getId() == 2)
				midPricedRoom = true;
			if (r.getId() == 3)
				maxPricedRoom = true;
		}
		assertTrue("Could not find least expensive room", minPricedRoom);
		assertTrue("Could not find medium priced room", midPricedRoom);
		assertFalse("Found most expesive room", maxPricedRoom);
	}

	@Test
	public void testFilterMinimumMaximumPrice()
	{
		// This test checks if price filter works correctly

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(
						lowCostSingleDoubleBedRoom, 
						mediumCostSingleDoubleBedRoom, 
						highCostSingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		Integer min = 5000;
		Integer max = 10000;
		search.setPriceRange(min, max);
		ArrayList<Room> filteredRooms = search.filter();

		// Check results
		assertNotNull(filteredRooms);
		assertEquals(1, filteredRooms.size());

		boolean exactMatch = false;

		for (Room r : filteredRooms)
		{
			if (r.getId() == 2)
				exactMatch = true;
		}
		assertTrue("Could not find exact match", exactMatch);
	}

	@Test
	public void testFilterAnySingleBeds()
	{
		// This test checks to that the mock data manager returns the list of
		// rooms

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(
						lowCostSingleDoubleBedRoom, 
						mediumCostSingleDoubleBedRoom, 
						highCostSingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		search.setNumberOfSingleBeds(null, null);
		ArrayList<Room> filteredRooms = search.filter();

		// Check results
		assertNotNull(filteredRooms);
		assertEquals(3, filteredRooms.size());

		boolean oneBed = false;
		boolean twoBed = false;
		boolean threeBed = false;

		for (Room r : filteredRooms)
		{
			if (r.getId() == 1)
				oneBed = true;
			if (r.getId() == 2)
				twoBed = true;
			if (r.getId() == 3)
				threeBed = true;
		}
		assertTrue("Could not find one bed room", oneBed);
		assertTrue("Could not find two bed room", twoBed);
		assertTrue("Could not find three bed room", threeBed);
	}

	@Test
	public void testFilterMinimumSingleBeds()
	{
		// This test checks to that the mock data manager returns the list of
		// rooms

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(
						lowCostSingleDoubleBedRoom, 
						mediumCostSingleDoubleBedRoom, 
						highCostSingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		search.setNumberOfSingleBeds(2, null);
		ArrayList<Room> filteredRooms = search.filter();

		// Check results
		assertNotNull(filteredRooms);
		assertEquals(2, filteredRooms.size());

		boolean twoBed = false;
		boolean threeBed = false;

		for (Room r : filteredRooms)
		{
			if (r.getId() == 2)
				twoBed = true;
			if (r.getId() == 3)
				threeBed = true;
		}
		assertTrue("Could not find two bed room", twoBed);
		assertTrue("Could not find three bed room", threeBed);
	}

	@Test
	public void testFilterMaximumSingleBeds()
	{
		// This test checks to that the mock data manager returns the list of
		// rooms

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(
						lowCostSingleDoubleBedRoom, 
						mediumCostSingleDoubleBedRoom, 
						highCostSingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		search.setNumberOfSingleBeds(null, 2);
		ArrayList<Room> filteredRooms = search.filter();

		// Check results
		assertNotNull(filteredRooms);
		assertEquals(2, filteredRooms.size());

		boolean oneBed = false;
		boolean twoBed = false;

		for (Room r : filteredRooms)
		{
			if (r.getId() == 1)
				oneBed = true;
			if (r.getId() == 2)
				twoBed = true;
		}
		assertTrue("Could not find one bed room", oneBed);
		assertTrue("Could not find two bed room", twoBed);
	}

	@Test
	public void testFilterMinimumMaximumSingleBeds()
	{
		// This test checks to that the mock data manager returns the list of
		// rooms

		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(
						tenSingleDoubleBedEnSuiteRoom, 
						twentySingleDoubleBedNonSuiteRoom, 
						thirtySingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		search.setNumberOfSingleBeds(15, 25);
		ArrayList<Room> filteredRooms = search.filter();

		// Check results
		assertNotNull(filteredRooms);
		assertEquals(1, filteredRooms.size());

		boolean twentyBed = false;

		for (Room r : filteredRooms)
		{
			if (r.getId() == 5)
				twentyBed = true;
		}
		assertTrue("Could not find twenty bed room", twentyBed);
	}

	@Test
	public void testFilterAnyDoubleBeds()
	{
		// This test checks to that the mock data manager returns the list of
		// rooms

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(
						Arrays.asList(
								lowCostSingleDoubleBedRoom, 
								mediumCostSingleDoubleBedRoom, 
								highCostSingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		search.setNumberOfDoubleBeds(null, null);
		ArrayList<Room> filteredRooms = search.filter();

		// Check results
		assertNotNull(filteredRooms);
		assertEquals(3, filteredRooms.size());

		boolean oneBed = false;
		boolean twoBed = false;
		boolean threeBed = false;

		for (Room r : filteredRooms)
		{
			if (r.getId() == 1)
				oneBed = true;
			if (r.getId() == 2)
				twoBed = true;
			if (r.getId() == 3)
				threeBed = true;
		}

		assertTrue("Could not find one bed room", oneBed);
		assertTrue("Could not find two bed room", twoBed);
		assertTrue("Could not find three bed room", threeBed);
	}

	@Test
	public void testFilterMinimumDoubleBeds()
	{
		// This test checks to that the mock data manager returns the list of
		// rooms

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(
						lowCostSingleDoubleBedRoom, 
						mediumCostSingleDoubleBedRoom, 
						highCostSingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		search.setNumberOfDoubleBeds(2, null);
		ArrayList<Room> filteredRooms = search.filter();

		// Check results
		assertNotNull(filteredRooms);
		assertEquals(2, filteredRooms.size());

		boolean twoBed = false;
		boolean threeBed = false;

		for (Room r : filteredRooms)
		{
			if (r.getId() == 2)
				twoBed = true;
			if (r.getId() == 3)
				threeBed = true;
		}
		assertTrue("Could not find two bed room", twoBed);
		assertTrue("Could not find three bed room", threeBed);
	}

	@Test
	public void testFilterMaximumDoubleBeds()
	{
		// This test checks to that the mock data manager returns the list of
		// rooms

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(
						lowCostSingleDoubleBedRoom, 
						mediumCostSingleDoubleBedRoom, 
						highCostSingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		search.setNumberOfDoubleBeds(null, 2);
		ArrayList<Room> filteredRooms = search.filter();

		// Check results
		assertNotNull(filteredRooms);
		assertEquals(2, filteredRooms.size());

		boolean oneBed = false;
		boolean twoBed = false;

		for (Room r : filteredRooms)
		{
			if (r.getId() == 1)
				oneBed = true;
			if (r.getId() == 2)
				twoBed = true;
		}

		assertTrue("Could not find one bed room", oneBed);
		assertTrue("Could not find two bed room", twoBed);
	}

	@Test
	public void testFilterMinimumMaximumDoubleBeds()
	{
		// This test checks to that the mock data manager returns the list of
		// rooms

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(
						tenSingleDoubleBedEnSuiteRoom, 
						twentySingleDoubleBedNonSuiteRoom, 
						thirtySingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		search.setNumberOfDoubleBeds(15, 25);
		ArrayList<Room> filteredRooms = search.filter();

		// Check results
		assertNotNull(filteredRooms);
		assertEquals(1, filteredRooms.size());

		boolean twentyBed = false;

		for (Room r : filteredRooms)
		{
			if (r.getId() == 5)
				twentyBed = true;
		}
		assertTrue("Could not find twenty bed room", twentyBed);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFilterByStarCountNegativeException()
	{
		// This test checks if star count filter works

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(lowCostSingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		search.setStarCount(-5, -5);
		search.filter();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFilterByStarCountOutOfBoundsException()
	{
		// This test checks if star count filter works

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(lowCostSingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		search.setStarCount(7, 7);
		search.filter();
	}

	@Test
	public void testFilterByStarCountWithoutMaximum()
	{
		// This test checks if star count filter works

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(
						lowCostSingleDoubleBedRoom, 
						mediumCostSingleDoubleBedRoom, 
						highCostSingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		search.setStarCount(2, null);
		ArrayList<Room> filteredRooms = search.filter();

		// Check results
		assertNotNull(filteredRooms);
		assertEquals(2, filteredRooms.size());

		boolean threeStars = false;
		boolean fiveStars = false;

		for (Room r : filteredRooms)
		{
			if (r.getId() == 2)
				threeStars = true;
			if (r.getId() == 3)
				fiveStars = true;
		}

		assertTrue("Could not find three star room", threeStars);
		assertTrue("Could not find five star room", fiveStars);
	}

	@Test
	public void testFilterByStarCountWithoutMinimum()
	{
		// This test checks if star count filter works

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(
						lowCostSingleDoubleBedRoom, 
						mediumCostSingleDoubleBedRoom, 
						highCostSingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		search.setStarCount(null, 4);
		ArrayList<Room> filteredRooms = search.filter();

		// Check results
		assertNotNull(filteredRooms);
		assertEquals(2, filteredRooms.size());

		boolean oneStars = false;
		boolean threeStars = false;

		for (Room r : filteredRooms)
		{
			if (r.getId() == 1)
				oneStars = true;
			if (r.getId() == 2)
				threeStars = true;
		}

		assertTrue("Could not find one star room", oneStars);
		assertTrue("Could not find three star room", threeStars);
	}

	@Test
	public void testFilterByStarCount()
	{
		// This test checks if star count filter works

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(
						lowCostSingleDoubleBedRoom, 
						mediumCostSingleDoubleBedRoom, 
						highCostSingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		search.setStarCount(2, 4);
		ArrayList<Room> filteredRooms = search.filter();

		// Check results
		assertNotNull(filteredRooms);
		assertEquals(1, filteredRooms.size());

		boolean threeStars = false;

		for (Room r : filteredRooms)
		{
			if (r.getId() == 2)
				threeStars = true;
		}
		assertTrue("Could not find three star room", threeStars);
	}

	@Test
	public void testFilterEnSuiteBathrooms()
	{
		// This test checks that the mock data manager returns the list of
		// rooms that have bathrooms

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(
						tenSingleDoubleBedEnSuiteRoom, 
						twentySingleDoubleBedNonSuiteRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		search.setEnSuiteBathroom(true);
		ArrayList<Room> filteredRooms = search.filter();

		// Check results
		assertNotNull(filteredRooms);
		assertEquals(1, filteredRooms.size());

		boolean withBathroom = false;
		boolean withoutBathroom = false;

		for (Room r : filteredRooms)
		{
			if (r.getId() == 4)
				withBathroom = true;
			if (r.getId() == 5)
				withoutBathroom = true;
		}

		assertTrue("Could not find room with bathroom", withBathroom);
		assertFalse("Found room without bathroom", withoutBathroom);
	}
	
	@Test
	public void testSortByStarcountAscending()
	{
		// This test checks that the mock data manager returns the list of
		// rooms by star count in ascending order.

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(
						highCostSingleDoubleBedRoom, 
						lowCostSingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		ArrayList<Room> sortedRooms = search.sort(
				Search.SortBy.STARCOUNT_ASCENDING);

		// Check results
		assertNotNull(sortedRooms);
		assertEquals(2, sortedRooms.size());
		
		assertTrue("Incorrect sort, one star not first in list", 
				sortedRooms.get(0).getHotel().getStarCount() == 1);
		assertTrue("Incorrect sort, five star not last in list", 
				sortedRooms.get(1).getHotel().getStarCount() == 5);
	}
	
	@Test
	public void testSortByStarcountDescending()
	{
		// This test checks that the mock data manager returns the list of
		// rooms by star count in descending order.

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(
						lowCostSingleDoubleBedRoom, 
						highCostSingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		ArrayList<Room> sortedRooms = search.sort(Search.SortBy.STARCOUNT_DESCENDING);

		// Check results
		assertNotNull(sortedRooms);
		assertEquals(2, sortedRooms.size());
		
		assertTrue("Incorrect sort, one star not last in list", 
				sortedRooms.get(0).getHotel().getStarCount() == 5);
		assertTrue("Incorrect sort, five star not first in list", 
				sortedRooms.get(1).getHotel().getStarCount() == 1);
	}
	
	@Test
	public void testSortByPriceAscending()
	{
		// This test checks that the mock data manager returns the list of
		// rooms by price in ascending order.

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(
						highCostSingleDoubleBedRoom, 
						mediumCostSingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		ArrayList<Room> sortedRooms = search.sort(Search.SortBy.PRICE_ASCENDING);

		// Check results
		assertNotNull(sortedRooms);
		assertEquals(2, sortedRooms.size());

		assertTrue("Incorrect sort, low price not first in list", 
				sortedRooms.get(0).getCostPerNight() == 5000);
		assertTrue("Incorrect sort, high price not last in list", 
				sortedRooms.get(1).getCostPerNight() == 11000);
	}
	
	@Test
	public void testSortByPriceDescending()
	{
		// This test checks that the mock data manager returns the list of
		// rooms in descending order by price.

		// Set up test data
		MockDataManager mdm = new MockDataManager(
				new ArrayList<>(Arrays.asList(
						mediumCostSingleDoubleBedRoom, 
						highCostSingleDoubleBedRoom)));
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		ArrayList<Room> sortedRooms = search.sort(Search.SortBy.PRICE_DESCENDING);

		// Check results
		assertNotNull(sortedRooms);
		assertEquals(2, sortedRooms.size());

		assertTrue("Incorrect sort, high price not first in list", 
				sortedRooms.get(0).getCostPerNight() == 11000);
		assertTrue("Incorrect sort, low price not last in list", 
				sortedRooms.get(1).getCostPerNight() == 5000);
	}
	
	@Test
	public void testSortByHotelNameAZ()
	{
		// This test checks that the mock data manager returns the list of
		// rooms in alphabetical order.

		// Set up test data
		ArrayList<Room> rooms = new ArrayList<>();
		
		rooms.add(mediumCostSingleDoubleBedRoom);
		rooms.add(highCostSingleDoubleBedRoom);
		rooms.add(lowCostSingleDoubleBedRoom);

		MockDataManager mdm = new MockDataManager(rooms);
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		ArrayList<Room> sortedRooms = search.sort(Search.SortBy.HOTEL_NAME_AZ);

		// Check results
		assertNotNull(sortedRooms);
		assertEquals(3, sortedRooms.size());

		assertTrue("Incorrect sort, A not first in list", 
				sortedRooms.get(0).getHotel().getName() == "AaaHotel");
		assertTrue("Incorrect sort, B not in middle of list",
				sortedRooms.get(1).getHotel().getName() == "BbbHotel");
		assertTrue("Incorrect sort, C not last in list", 
				sortedRooms.get(2).getHotel().getName() == "CccHotel");
	}
	
	@Test
	public void testSortByHotelNameZA()
	{
		// This test checks that the mock data manager returns the list of
		// rooms in reverse alphabetical order.

		// Set up test data
		ArrayList<Room> rooms = new ArrayList<>();
		
		rooms.add(mediumCostSingleDoubleBedRoom);
		rooms.add(lowCostSingleDoubleBedRoom);
		rooms.add(highCostSingleDoubleBedRoom);

		MockDataManager mdm = new MockDataManager(rooms);
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		ArrayList<Room> sortedRooms = search.sort(Search.SortBy.HOTEL_NAME_ZA);

		// Check results
		assertNotNull(sortedRooms);
		assertEquals(3, sortedRooms.size());

		assertTrue("Incorrect sort, C not first in list", 
				sortedRooms.get(0).getHotel().getName() == "CccHotel");
		assertTrue("Incorrect sort, B not in middle of list",
				sortedRooms.get(1).getHotel().getName() == "BbbHotel");
		assertTrue("Incorrect sort, A not last in list", 
				sortedRooms.get(2).getHotel().getName() == "AaaHotel");
	}
}



















