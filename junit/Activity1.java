package activities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

public class Activity1 {
 static ArrayList<String> list;

	@BeforeEach
	static void setUp() throws Exception {
		list = new ArrayList<String>();
		list.add("alpha"); // at index 0
		list.add("beta"); // at index 1
	}

	@Test
	public void insertTest() {
		assertEquals(2, list.size(), "Wrong size");
		
		list.add(2,"theta");
		assertEquals(3, list.size(), "Wrong element");

        assertEquals("alpha", list.get(0), "Wrong element");
        assertEquals("beta", list.get(1), "Wrong element");
        assertEquals("theta", list.get(2), "Wrong element");
	}
	
	@Test
	public void replaceTest() {
		
		list.set(1, "charlie");
		 
        assertEquals(2, list.size(), "Wrong size");
        assertEquals("alpha", list.get(0), "Wrong element");
        assertEquals("charlie", list.get(1), "Wrong element");
	}

}