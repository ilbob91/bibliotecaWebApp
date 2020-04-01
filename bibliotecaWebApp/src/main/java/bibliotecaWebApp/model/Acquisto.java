package bibliotecaWebApp.model;

public class Acquisto {
	private int idAcquisto;
	
	private int idLibro;
	
	private String titolo;
	
	private int quantita;
	
	private double prezzo;
	
	private String username;
	
	private int idScontrino;

	public Acquisto(int idLibro, String titolo, int quantita, double prezzo, String username, int idScontrino) {
		this.idLibro = idLibro;
		this.titolo = titolo;
		this.quantita = quantita;
		this.prezzo = prezzo;
		this.username = username;
		this.idScontrino = idScontrino;
	}

	public int getIdAcquisto() {
		return idAcquisto;
	}

	public void setIdAcquisto(int idAcquisto) {
		this.idAcquisto = idAcquisto;
	}

	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getIdScontrino() {
		return idScontrino;
	}

	public void setIdScontrino(int idScontrino) {
		this.idScontrino = idScontrino;
	}
	
	
	

}
