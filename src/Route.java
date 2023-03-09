import java.math.BigDecimal;
import java.math.MathContext;

public class Route{
	
	private String Code;
	private String ID;
	private Airport sourceAirport;
	private String sourceAirportID;
	private String sourceAirportIATA;
	private Airport destinationAirport;
	private String destinationAirportID;
	private String destinationAirportIATA;
	private double weight;
	
	public Route() {
		this.sourceAirport = null;
		this.destinationAirport = null;
	}

	public Airport getSourceAirport() {
		return sourceAirport;
	}

	public void setSourceAirport(Airport sourceAirport) {
		this.sourceAirport = sourceAirport;
	}

	public String getSourceAirportID() {
		return sourceAirportID;
	}

	public void setSourceAirportID(String sourceAirportID) {
		this.sourceAirportID = sourceAirportID;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public String getDestinationAirportID() {
		return destinationAirportID;
	}

	public void setDestinationAirportID(String destinationAirportID) {
		this.destinationAirportID = destinationAirportID;
	}


	public String getSourceAirportIATA() {
		return sourceAirportIATA;
	}

	public void setSourceAirportIATA(String sourceAirportIATA) {
		this.sourceAirportIATA = sourceAirportIATA;
	}

	public String getDestinationAirportIATA() {
		return destinationAirportIATA;
	}
	
	//simulacion de la curvatura del planeta tierra en el mapa
	public void setPathWeight(double lat1, double lat2, double long1, double long2) {
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);
		long1 = Math.toRadians(long1);
		long2 = Math.toRadians(long2);
		double deltaLat = lat2 - lat1;
		double deltaLong = long2 - long1;
		
		double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
				+ Math.cos(lat1) * Math.cos(lat2) * Math.sin(deltaLong / 2) * Math.sin(deltaLong / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		
		double distance = 6371e3 * c;
		
		BigDecimal bd = new BigDecimal(distance);
		bd = bd.round(new MathContext(4));
		
		this.weight = (bd.doubleValue() / 1000);
	}
	
	public double getWeight() {
		return this.weight;
	}

	public void setDestinationAirportIATA(String destinationAirportIATA) {
		this.destinationAirportIATA = destinationAirportIATA;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

}
