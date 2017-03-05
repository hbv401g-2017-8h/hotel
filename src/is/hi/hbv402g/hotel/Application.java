package is.hi.hbv402g.hotel;

import java.util.ArrayList;

import is.hi.hbv402g.hotel.test.*;

public class Application {

	public static void main(String[] args) {
        ArrayList<Integer> ids = TestClass.getIdsForName("Name");
        
        for (int i : ids) {
            System.out.println("LALA:" + i);
        }
	}

}
