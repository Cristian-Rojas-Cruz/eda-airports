public class Airport implements java.io.Serializable {
	private static final long serialVersionUID = 9214314719142541165L;
	
	private String id;
	private String name;
	private String ciudad;
	private String pais;
	private String IATA;
	private String ICAO;
	private double latitud;
	private double longitud;
	private double altitud;
	private String zonaHoraria;
	private String DST;
	private String TZ; 
	private String Tipo;
	private String fuente;
	
	public Airport() {
		
	}
	
	public Airport(String id, String name, String ciudad, String pais, 
			String IATA, String ICAO, double latitud, double longitud, double altitud, String zonaHoraria, String DST, String TZ, String Tipo, String fuente) {
		this.id = id;
		this.name = name;
		this.ciudad = ciudad;
		this.pais = pais;
		this.IATA = IATA;
		this.ICAO = ICAO;
		this.latitud = latitud;
		this.longitud = longitud;
		this.altitud = altitud;
		this.zonaHoraria = zonaHoraria;
		this.DST = DST;
		this.TZ = TZ;
		this.Tipo = Tipo;
		this.fuente= fuente;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getIATA() {
		return IATA;
	}
	public void setIATA(String iATA) {
		IATA = iATA;
	}
	public String getICAO() {
		return ICAO;
	}
	public void setICAO(String iCAO) {
		ICAO = iCAO;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public String getZonaHoraria() {
		return zonaHoraria;
	}
	public void setZonaHoraria(String zonaHoraria) {
		this.zonaHoraria = zonaHoraria;
	}
	public String getDST() {
		return DST;
	}
	public void setDST(String dST) {
		DST = dST;
	}
	public String getTZ() {
		return TZ;
	}
	public void setTZ(String tZ) {
		TZ = tZ;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	public String getFuente() {
		return fuente;
	}
	public void setFuente(String fuente) {
		this.fuente = fuente;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public double getAltitud() {
		return altitud;
	}
	public void setAltitud(double d) {
		this.altitud = d;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	 
	 
}
