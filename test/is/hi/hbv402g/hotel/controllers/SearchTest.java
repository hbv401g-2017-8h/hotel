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

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testFindReturnsMockData()
	{
		// This test checks to that the mock data manager returns the list of rooms

		// Set up test data
		ArrayList<Room> rooms = new ArrayList<>();
		
		rooms.add(new Room(1));
		
		MockDataManager mdm = new MockDataManager(rooms);
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
		
		Hotel amenityLessHotel = new Hotel(1);
		amenityLessHotel.setAmenities(new ArrayList<String>());
		Room room1 = new Room(1);
		room1.setHotel(amenityLessHotel);
		
		Hotel withOnlyWifi = new Hotel(2);
		withOnlyWifi.setAmenities(new ArrayList<>(Arrays.asList("wifi")));
		Room room2 = new Room(2);
		room2.setHotel(withOnlyWifi);
		
		Hotel withWifiAndRoomService = new Hotel(3);
		withWifiAndRoomService.setAmenities(new ArrayList<>(Arrays.asList("wifi", "roomService")));
		Room room3 = new Room(3);
		room3.setHotel(withWifiAndRoomService);
		
		Hotel withWifiAndRoomServiceAndBreakfast = new Hotel(4);
		withWifiAndRoomServiceAndBreakfast.setAmenities(new ArrayList<>(Arrays.asList("wifi", "roomService", "breakfast")));
		Room room4 = new Room(4);
		room4.setHotel(withWifiAndRoomServiceAndBreakfast);
		
		MockDataManager mdm = new MockDataManager(new ArrayList<>(Arrays.asList(room1, room2, room3, room4)));
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
	public void testFilterAnySingleBeds()
	{
		// This test checks to that the mock data manager returns the list of rooms

		// Set up test data
		ArrayList<Room> rooms = new ArrayList<>();
		
		rooms.add(new Room(1) {{
			setNumberOfSingleBeds(1);
		}});
		rooms.add(new Room(2) {{
			setNumberOfSingleBeds(2);
		}});
		rooms.add(new Room(3) {{
			setNumberOfSingleBeds(3);
		}});
		
		MockDataManager mdm = new MockDataManager(rooms);
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
		// This test checks to that the mock data manager returns the list of rooms

		// Set up test data
		ArrayList<Room> rooms = new ArrayList<>();
		
		rooms.add(new Room(1) {{
			setNumberOfSingleBeds(1);
		}});
		rooms.add(new Room(2) {{
			setNumberOfSingleBeds(2);
		}});
		rooms.add(new Room(3) {{
			setNumberOfSingleBeds(3);
		}});
		
		MockDataManager mdm = new MockDataManager(rooms);
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
		// This test checks to that the mock data manager returns the list of rooms

		// Set up test data
		ArrayList<Room> rooms = new ArrayList<>();
		
		rooms.add(new Room(1) {{
			setNumberOfSingleBeds(1);
		}});
		rooms.add(new Room(2) {{
			setNumberOfSingleBeds(2);
		}});
		rooms.add(new Room(3) {{
			setNumberOfSingleBeds(3);
		}});
		
		MockDataManager mdm = new MockDataManager(rooms);
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
		// This test checks to that the mock data manager returns the list of rooms

		// Set up test data
		ArrayList<Room> rooms = new ArrayList<>();
		
		rooms.add(new Room(1) {{
			setNumberOfSingleBeds(10);
		}});
		rooms.add(new Room(2) {{
			setNumberOfSingleBeds(20);
		}});
		rooms.add(new Room(3) {{
			setNumberOfSingleBeds(30);
		}});
		
		MockDataManager mdm = new MockDataManager(rooms);
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
			if (r.getId() == 2)
				twentyBed = true;
		}
		
		assertTrue("Could not find twenty bed room", twentyBed);
	}

	@Test
	public void testFilterAnyDoubleBeds()
	{
		// This test checks to that the mock data manager returns the list of rooms

		// Set up test data
		ArrayList<Room> rooms = new ArrayList<>();
		
		rooms.add(new Room(1) {{
			setNumberOfDoubleBeds(1);
		}});
		rooms.add(new Room(2) {{
			setNumberOfDoubleBeds(2);
		}});
		rooms.add(new Room(3) {{
			setNumberOfDoubleBeds(3);
		}});
		
		MockDataManager mdm = new MockDataManager(rooms);
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
		// This test checks to that the mock data manager returns the list of rooms

		// Set up test data
		ArrayList<Room> rooms = new ArrayList<>();
		
		rooms.add(new Room(1) {{
			setNumberOfDoubleBeds(1);
		}});
		rooms.add(new Room(2) {{
			setNumberOfDoubleBeds(2);
		}});
		rooms.add(new Room(3) {{
			setNumberOfDoubleBeds(3);
		}});
		
		MockDataManager mdm = new MockDataManager(rooms);
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
		// This test checks to that the mock data manager returns the list of rooms

		// Set up test data
		ArrayList<Room> rooms = new ArrayList<>();
		
		rooms.add(new Room(1) {{
			setNumberOfDoubleBeds(1);
		}});
		rooms.add(new Room(2) {{
			setNumberOfDoubleBeds(2);
		}});
		rooms.add(new Room(3) {{
			setNumberOfDoubleBeds(3);
		}});
		
		MockDataManager mdm = new MockDataManager(rooms);
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
		// This test checks to that the mock data manager returns the list of rooms

		// Set up test data
		ArrayList<Room> rooms = new ArrayList<>();
		
		rooms.add(new Room(1) {{
			setNumberOfDoubleBeds(10);
		}});
		rooms.add(new Room(2) {{
			setNumberOfDoubleBeds(20);
		}});
		rooms.add(new Room(3) {{
			setNumberOfDoubleBeds(30);
		}});
		
		MockDataManager mdm = new MockDataManager(rooms);
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
			if (r.getId() == 2)
				twentyBed = true;
		}
		
		assertTrue("Could not find twenty bed room", twentyBed);
	}
}
