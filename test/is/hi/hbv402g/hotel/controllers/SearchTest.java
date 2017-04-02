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
		// This test checks to that the mock data manager returns the list of
		// rooms

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
		withWifiAndRoomServiceAndBreakfast
				.setAmenities(new ArrayList<>(Arrays.asList("wifi", "roomService", "breakfast")));
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

	@Test(expected = IllegalArgumentException.class)
	public void testNegativePriceRangeException()
	{
		// This test checks if negative price range exception works correctly

		// Set up test data

		ArrayList<Room> rooms = new ArrayList<>();

		int price = 5000;

		rooms.add(new Room(1)
		{
			{
				setCostPerNight(price);
			}
		});

		MockDataManager mdm = new MockDataManager(rooms);
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
		// This test checks if price rooms filtered by minimum price only works
		// correctly

		// Set up test data
		ArrayList<Room> rooms = new ArrayList<>();

		rooms.add(new Room(1)
		{
			{
				setCostPerNight(1000);
			}
		});

		rooms.add(new Room(2)
		{
			{
				setCostPerNight(5000);
			}
		});

		rooms.add(new Room(3)
		{
			{
				setCostPerNight(11000);
			}
		});

		MockDataManager mdm = new MockDataManager(rooms);
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
		ArrayList<Room> rooms = new ArrayList<>();

		rooms.add(new Room(1)
		{
			{
				setCostPerNight(1000);
			}
		});

		rooms.add(new Room(2)
		{
			{
				setCostPerNight(5000);
			}
		});

		rooms.add(new Room(3)
		{
			{
				setCostPerNight(11000);
			}
		});

		MockDataManager mdm = new MockDataManager(rooms);
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
		ArrayList<Room> rooms = new ArrayList<>();

		int lowPrice = 0;
		int mediumPrice = 7000;
		int highPrice = 20000;

		rooms.add(new Room(1)
		{
			{
				setCostPerNight(lowPrice);
			}
		});

		rooms.add(new Room(2)
		{
			{
				setCostPerNight(mediumPrice);
			}
		});

		rooms.add(new Room(3)
		{
			{
				setCostPerNight(highPrice);
			}
		});

		MockDataManager mdm = new MockDataManager(rooms);
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
		ArrayList<Room> rooms = new ArrayList<>();

		rooms.add(new Room(1)
		{
			{
				setNumberOfSingleBeds(1);
			}
		});
		rooms.add(new Room(2)
		{
			{
				setNumberOfSingleBeds(2);
			}
		});
		rooms.add(new Room(3)
		{
			{
				setNumberOfSingleBeds(3);
			}
		});

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
		// This test checks to that the mock data manager returns the list of
		// rooms

		// Set up test data
		ArrayList<Room> rooms = new ArrayList<>();

		rooms.add(new Room(1)
		{
			{
				setNumberOfSingleBeds(1);
			}
		});
		rooms.add(new Room(2)
		{
			{
				setNumberOfSingleBeds(2);
			}
		});
		rooms.add(new Room(3)
		{
			{
				setNumberOfSingleBeds(3);
			}
		});

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
		// This test checks to that the mock data manager returns the list of
		// rooms

		// Set up test data
		ArrayList<Room> rooms = new ArrayList<>();

		rooms.add(new Room(1)
		{
			{
				setNumberOfSingleBeds(1);
			}
		});
		rooms.add(new Room(2)
		{
			{
				setNumberOfSingleBeds(2);
			}
		});
		rooms.add(new Room(3)
		{
			{
				setNumberOfSingleBeds(3);
			}
		});

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
		// This test checks to that the mock data manager returns the list of
		// rooms

		// Set up test data
		ArrayList<Room> rooms = new ArrayList<>();

		rooms.add(new Room(1)
		{
			{
				setNumberOfSingleBeds(10);
			}
		});
		rooms.add(new Room(2)
		{
			{
				setNumberOfSingleBeds(20);
			}
		});
		rooms.add(new Room(3)
		{
			{
				setNumberOfSingleBeds(30);
			}
		});

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
		// This test checks to that the mock data manager returns the list of
		// rooms

		// Set up test data
		ArrayList<Room> rooms = new ArrayList<>();

		rooms.add(new Room(1)
		{
			{
				setNumberOfDoubleBeds(1);
			}
		});
		rooms.add(new Room(2)
		{
			{
				setNumberOfDoubleBeds(2);
			}
		});
		rooms.add(new Room(3)
		{
			{
				setNumberOfDoubleBeds(3);
			}
		});

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
		// This test checks to that the mock data manager returns the list of
		// rooms

		// Set up test data
		ArrayList<Room> rooms = new ArrayList<>();

		rooms.add(new Room(1)
		{
			{
				setNumberOfDoubleBeds(1);
			}
		});
		rooms.add(new Room(2)
		{
			{
				setNumberOfDoubleBeds(2);
			}
		});
		rooms.add(new Room(3)
		{
			{
				setNumberOfDoubleBeds(3);
			}
		});

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
		// This test checks to that the mock data manager returns the list of
		// rooms

		// Set up test data
		ArrayList<Room> rooms = new ArrayList<>();

		rooms.add(new Room(1)
		{
			{
				setNumberOfDoubleBeds(1);
			}
		});
		rooms.add(new Room(2)
		{
			{
				setNumberOfDoubleBeds(2);
			}
		});
		rooms.add(new Room(3)
		{
			{
				setNumberOfDoubleBeds(3);
			}
		});

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
		// This test checks to that the mock data manager returns the list of
		// rooms

		// Set up test data
		ArrayList<Room> rooms = new ArrayList<>();

		rooms.add(new Room(1)
		{
			{
				setNumberOfDoubleBeds(10);
			}
		});
		rooms.add(new Room(2)
		{
			{
				setNumberOfDoubleBeds(20);
			}
		});
		rooms.add(new Room(3)
		{
			{
				setNumberOfDoubleBeds(30);
			}
		});

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

	@Test(expected = IllegalArgumentException.class)
	public void testFilterByStarCountNegativeException()
	{
		// This test checks if star count filter works

		// Set up test data
		ArrayList<Hotel> hotels = new ArrayList<>();
		ArrayList<Room> rooms = new ArrayList<>();

		int starCount = 1;

		hotels.add(new Hotel(1)
		{
			{
				setStarCount(starCount);
			}
		});

		for (Hotel h : hotels)
		{
			rooms.add(new Room(h.getId())
			{
				{
					setHotel(h);
				}
			});
		}

		MockDataManager mdm = new MockDataManager(rooms);
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
		ArrayList<Hotel> hotels = new ArrayList<>();
		ArrayList<Room> rooms = new ArrayList<>();

		int starCount = 1;

		hotels.add(new Hotel(1)
		{
			{
				setStarCount(starCount);
			}
		});

		for (Hotel h : hotels)
		{
			rooms.add(new Room(h.getId())
			{
				{
					setHotel(h);
				}
			});
		}

		MockDataManager mdm = new MockDataManager(rooms);
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
		ArrayList<Hotel> hotels = new ArrayList<>();
		ArrayList<Room> rooms = new ArrayList<>();

		int lowStarCount = 1;
		int mediumStarCount = 3;
		int highStarCount = 5;

		hotels.add(new Hotel(1)
		{
			{
				setStarCount(lowStarCount);
			}
		});
		hotels.add(new Hotel(2)
		{
			{
				setStarCount(mediumStarCount);
			}
		});
		hotels.add(new Hotel(3)
		{
			{
				setStarCount(highStarCount);
			}
		});

		for (Hotel h : hotels)
		{
			rooms.add(new Room(h.getId())
			{
				{
					setHotel(h);
				}
			});
		}

		MockDataManager mdm = new MockDataManager(rooms);
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
		ArrayList<Hotel> hotels = new ArrayList<>();
		ArrayList<Room> rooms = new ArrayList<>();

		int lowStarCount = 1;
		int mediumStarCount = 3;
		int highStarCount = 5;

		hotels.add(new Hotel(1)
		{
			{
				setStarCount(lowStarCount);
			}
		});
		hotels.add(new Hotel(2)
		{
			{
				setStarCount(mediumStarCount);
			}
		});
		hotels.add(new Hotel(3)
		{
			{
				setStarCount(highStarCount);
			}
		});

		for (Hotel h : hotels)
		{
			rooms.add(new Room(h.getId())
			{
				{
					setHotel(h);
				}
			});
		}

		MockDataManager mdm = new MockDataManager(rooms);
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
		ArrayList<Hotel> hotels = new ArrayList<>();
		ArrayList<Room> rooms = new ArrayList<>();

		int lowStarCount = 1;
		int mediumStarCount = 3;
		int highStarCount = 5;

		hotels.add(new Hotel(1)
		{
			{
				setStarCount(lowStarCount);
			}
		});
		hotels.add(new Hotel(2)
		{
			{
				setStarCount(mediumStarCount);
			}
		});
		hotels.add(new Hotel(3)
		{
			{
				setStarCount(highStarCount);
			}
		});

		for (Hotel h : hotels)
		{
			rooms.add(new Room(h.getId())
			{
				{
					setHotel(h);
				}
			});
		}

		MockDataManager mdm = new MockDataManager(rooms);
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
		ArrayList<Room> rooms = new ArrayList<>();

		rooms.add(new Room(1)
		{
			{
				setEnSuiteBathroom(true);
			}
		});
		rooms.add(new Room(2)
		{
			{
				setEnSuiteBathroom(false);
			}
		});

		MockDataManager mdm = new MockDataManager(rooms);
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
			if (r.getId() == 1)
				withBathroom = true;
			if (r.getId() == 2)
				withoutBathroom = true;
		}

		assertTrue("Could not find room with bathroom", withBathroom);
		assertFalse("Found room without bathroom", withoutBathroom);
	}
	
	@Test
	public void testSortByStarcountAscending()
	{
		// This test checks that the mock data manager returns the list of
		// rooms that have bathrooms

		// Set up test data
		Hotel oneStar = new Hotel(1);
		oneStar.setStarCount(1);
		
		Hotel fiveStar = new Hotel(2);
		fiveStar.setStarCount(5);
		
		ArrayList<Room> rooms = new ArrayList<>();

		rooms.add(new Room(2)
		{
			{
				setHotel(fiveStar);
			}
		});
		rooms.add(new Room(1)
		{
			{
				setHotel(oneStar);
			}
		});

		MockDataManager mdm = new MockDataManager(rooms);
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		ArrayList<Room> sortedRooms = search.sort(Search.SortBy.STARCOUNT_ASCENDING);

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
		// rooms that have bathrooms

		// Set up test data
		Hotel oneStar = new Hotel(1);
		oneStar.setStarCount(1);
		
		Hotel fiveStar = new Hotel(2);
		fiveStar.setStarCount(5);
		
		ArrayList<Room> rooms = new ArrayList<>();

		rooms.add(new Room(1)
		{
			{
				setHotel(oneStar);
			}
		});
		rooms.add(new Room(2)
		{
			{
				setHotel(fiveStar);
			}
		});

		MockDataManager mdm = new MockDataManager(rooms);
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
		// rooms that have bathrooms

		// Set up test data
		ArrayList<Room> rooms = new ArrayList<>();

		rooms.add(new Room(1)
		{
			{
				setCostPerNight(10000);
			}
		});
		rooms.add(new Room(2)
		{
			{
				setCostPerNight(15000);
			}
		});

		MockDataManager mdm = new MockDataManager(rooms);
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		ArrayList<Room> sortedRooms = search.sort(Search.SortBy.PRICE_ASCENDING);

		// Check results
		assertNotNull(sortedRooms);
		assertEquals(2, sortedRooms.size());

		assertTrue("Incorrect sort, low price not first in list", 
				sortedRooms.get(0).getCostPerNight() == 10000);
		assertTrue("Incorrect sort, high price not last in list", 
				sortedRooms.get(1).getCostPerNight() == 15000);
	}
	
	@Test
	public void testSortByPriceDescending()
	{
		// This test checks that the mock data manager returns the list of
		// rooms that have bathrooms

		// Set up test data
		ArrayList<Room> rooms = new ArrayList<>();

		rooms.add(new Room(1)
		{
			{
				setCostPerNight(10000);
			}
		});
		rooms.add(new Room(2)
		{
			{
				setCostPerNight(15000);
			}
		});

		MockDataManager mdm = new MockDataManager(rooms);
		Search search = new Search(mdm);

		// Perform actions to be tested
		search.find(null, null, null, null);
		ArrayList<Room> sortedRooms = search.sort(Search.SortBy.PRICE_DESCENDING);

		// Check results
		assertNotNull(sortedRooms);
		assertEquals(2, sortedRooms.size());

		assertTrue("Incorrect sort, high price not first in list", 
				sortedRooms.get(0).getCostPerNight() == 15000);
		assertTrue("Incorrect sort, low price not last in list", 
				sortedRooms.get(1).getCostPerNight() == 10000);
	}
	
//	@Test
//	public void testSortByHotelNameAZ()
//	{
//		// This test checks that the mock data manager returns the list of
//		// rooms that have bathrooms
//
//		// Set up test data
//	
//		ArrayList<Room> rooms = new ArrayList<>();
//
//		rooms.add(new Room(1)
//		{
//			{
//				setHotel().;(10000);
//			}
//		});
//		rooms.add(new Room(2)
//		{
//			{
//				setCostPerNight(15000);
//			}
//		});
//
//		MockDataManager mdm = new MockDataManager(rooms);
//		Search search = new Search(mdm);
//
//		// Perform actions to be tested
//		search.find(null, null, null, null);
//		ArrayList<Room> sortedRooms = search.sort(Search.SortBy.HOTEL_NAME_AZ);
//
//		// Check results
//		assertNotNull(sortedRooms);
//		assertEquals(2, sortedRooms.size());
//
//		assertTrue("Incorrect sort, low price not first in list", 
//				sortedRooms.get(0).getCostPerNight() == 10000);
//		assertTrue("Incorrect sort, high price not last in list", 
//				sortedRooms.get(1).getCostPerNight() == 15000);
//	}
}



















