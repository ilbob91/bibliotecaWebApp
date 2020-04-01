package bibliotecaWebApp.model;

public class Prestito {

	private int idPrestito;
	
	private int idLibro;
	
	private String titolo;
	
	private String dataAffitto;
	
	private String username;
	
	
	

	public Prestito(int idLibro, String titolo, String dataAffitto, String username) {
		this.idLibro = idLibro;
		this.titolo = titolo;
		this.dataAffitto = dataAffitto;
		this.username = username;
	}

	public int getIdPrestito() {
		return idPrestito;
	}

	public void setIdPrestito(int idPrestito) {
		this.idPrestito = idPrestito;
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

	public String getDataAffitto() {
		return dataAffitto;
	}

	public void setDataAffitto(String dataAffitto) {
		this.dataAffitto = dataAffitto;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	
}
