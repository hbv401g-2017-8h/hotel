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

}
