import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Reader {
	private static final String Separator = ",";

	public ArrayList<Airport> readFileAirports() throws IOException {
		ArrayList<Airport> Airports = new ArrayList<Airport>();
		var URL = new URL("https://raw.githubusercontent.com/jpatokal/openflights/master/data/airports.dat");
		String[] index = null;
		Airport newAirport = null;
		String str;
		try {
			BufferedReader Reader = new BufferedReader(new InputStreamReader(URL.openStream()));
			String line = Reader.readLine();
			while (line != null) {
				index = line.split(Separator);

				newAirport = new Airport();
				newAirport.setId(index[0]);
				newAirport.setName(index[1].replace("\"", "").replaceAll("\\\\N", ""));
				newAirport.setCiudad(index[2].replace("\"", "").replaceAll("\\\\N", ""));
				newAirport.setPais(index[3].replace("\"", "").replaceAll("\\\\N", ""));
				newAirport.setIATA(index[4].replace("\"", "").replaceAll("\\\\N", ""));
				newAirport.setICAO(index[5].replace("\"", "").replaceAll("\\\\N", ""));
				str = index[6];
				try {
					newAirport.setLatitud(Double.parseDouble(str));
				} catch (NumberFormatException ex) {

				} 
				str = index[7];
				try {
					newAirport.setLongitud(Double.parseDouble(str));
				} catch (NumberFormatException ex) {

				}
				str = index[8];
				try {
					newAirport.setAltitud(Double.parseDouble(str));
				} catch (NumberFormatException ex) {

				}
				newAirport.setZonaHoraria(index[9].replace("\"", "").replaceAll("\\\\N", ""));
				newAirport.setDST(index[10].replace("\"", "").replaceAll("\\\\N", ""));
				newAirport.setTZ(index[11].replace("\"", "").replaceAll("\\\\N", ""));
				newAirport.setTipo(index[12].replace("\"", "").replaceAll("\\\\N", ""));
				newAirport.setFuente(index[13].replace("\"", "").replaceAll("\\\\N", ""));

				Airports.add(newAirport);
				line = Reader.readLine();
			}
			Reader.close();
		} catch (Exception ex) {
			System.out.println("File not found: " + ex);
		}
		return Airports;
	}
  
	public ArrayList<Route> readFileRoutes() throws IOException {
		var URL = new URL("https://raw.githubusercontent.com/jpatokal/openflights/master/data/routes.dat");
		String[] index = null;
		ArrayList<Route> Routes = new ArrayList<Route>();
		Route newRoute = null;
		try {
			BufferedReader Reader = new BufferedReader(new InputStreamReader(URL.openStream()));
			String line = Reader.readLine();
			while (line != null) {
				index = line.split(Separator);

				newRoute = new Route();
				newRoute.setCode(index[0].replace("\"", "").replaceAll("\\\\N", ""));
				newRoute.setID(index[1].replace("\"", "").replaceAll("\\\\N", ""));
				newRoute.setSourceAirportIATA(index[2].replace("\"", "").replaceAll("\\\\N", ""));
				newRoute.setSourceAirportID(index[3].replace("\"", "").replaceAll("\\\\N", ""));
				newRoute.setDestinationAirportIATA(index[4].replace("\"", "").replaceAll("\\\\N", ""));
				newRoute.setDestinationAirportID(index[5].replace("\"", "").replaceAll("\\\\N", ""));

				Routes.add(newRoute);
				line = Reader.readLine();

			}
			Reader.close();
		} catch (Exception ex) {
			System.out.println("Error in: " + ex);
		}
		return Routes;
	}
	
	public ArrayList<Route> readFileRoutes(ArrayList<Airport> Airports) throws IOException {
		var URL = new URL("https://raw.githubusercontent.com/jpatokal/openflights/master/data/routes.dat");
		String[] index = null;
		ArrayList<Route> Routes = new ArrayList<Route>();
		Route newRoute = null;
		try {
			BufferedReader Reader = new BufferedReader(new InputStreamReader(URL.openStream()));
			String line = Reader.readLine();
			while (line != null) {
				index = line.split(Separator);
				
				newRoute = new Route();
				newRoute.setCode(index[0].replace("\"", "").replaceAll("\\\\N", ""));
				newRoute.setID(index[1].replace("\"", "").replaceAll("\\\\N", ""));
				newRoute.setSourceAirportIATA(index[2].replace("\"", "").replaceAll("\\\\N", ""));
				newRoute.setSourceAirportID(index[3].replace("\"", "").replaceAll("\\\\N", ""));
				
				if (newRoute.getSourceAirportID().equals("")) {
					for (Airport airport : Airports) {
						if (newRoute.getSourceAirportIATA().equals(airport.getIATA())) {
							newRoute.setSourceAirport(airport);
							
							break;
						}
					}
				} else {
					for (Airport airport : Airports) {
						if (newRoute.getSourceAirportID().equals(airport.getId())) {
							newRoute.setSourceAirport(airport);
							
							break;
						}
					}
				}
				
				newRoute.setDestinationAirportIATA(index[4].replace("\"", "").replaceAll("\\\\N", ""));
				newRoute.setDestinationAirportID(index[5].replace("\"", "").replaceAll("\\\\N", ""));
				
				if (newRoute.getDestinationAirportID().equals("")) {
					for (Airport airport : Airports) {
						if (newRoute.getDestinationAirportIATA().equals(airport.getIATA())) {
							newRoute.setDestinationAirport(airport);
							
							break;
						}
					}
				} else {
					for (Airport airport : Airports) {
						if (newRoute.getDestinationAirportID().equals(airport.getId())) {
							newRoute.setDestinationAirport(airport);
							
							break;
						}
					}
				}
				
				if (newRoute.getDestinationAirport() != null && newRoute.getSourceAirport() != null) {
					newRoute.setPathWeight(newRoute.getSourceAirport().getLatitud(),
							newRoute.getDestinationAirport().getLatitud(), newRoute.getSourceAirport().getLongitud(),
							newRoute.getDestinationAirport().getLongitud());
					Routes.add(newRoute);
				}
				line = Reader.readLine();
			}
		} catch (Exception ex) {
			System.out.println("Exception: "+ ex);
		}
		
		return Routes;
	}
}