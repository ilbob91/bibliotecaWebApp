package bibliotecaWebApp.model;

public class Scontrino {
	
	private int idScontrino;
	
	private String username;
	
	private String data;
	
	private double costoTotale;

	public Scontrino(int idScontrino, String username, String data, double costoTotale) {
		this.idScontrino = idScontrino;
		this.username = username;
		this.data = data;
		this.costoTotale = costoTotale;
	}

	public int getIdScontrino() {
		return idScontrino;
	}

	public void setIdScontrino(int idScontrino) {
		this.idScontrino = idScontrino;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public double getCostoTotale() {
		return costoTotale;
	}

	public void setCostoTotale(double costoTotale) {
		this.costoTotale = costoTotale;
	}
	
	

}
