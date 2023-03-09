import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ReaderTest {

	@Test
	public void ReadAllRoutes() {
		Reader reader = new Reader();
		ArrayList<Route> Routes = null;
		try {
			Routes = reader.readFileRoutes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int size = Routes.size();
		assertEquals(67663, size);
	}
	@Test 
	public void ReadAllAirports() {
		Reader reader = new Reader();
		ArrayList<Airport> Airports = null;
		try {
			Airports = reader.readFileAirports();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int size =  Airports.size();
		assertEquals(7698, size);
	}
}
