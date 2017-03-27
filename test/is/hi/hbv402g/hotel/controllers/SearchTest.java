package is.hi.hbv402g.hotel.controllers;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import is.hi.hbv402g.hotel.db.MockDataManager;
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
		ArrayList<Room> rooms = new ArrayList<>();
		
		rooms.add(new Room(1));
		
		MockDataManager mdm = new MockDataManager(rooms);
		Search search = new Search(mdm);
		
		ArrayList<Room> foundRooms = search.find(null, null, null, null);
		assertNotNull(foundRooms);
		assertEquals(1, foundRooms.size());
		assertEquals(1, foundRooms.get(0).getId());
	}

}
