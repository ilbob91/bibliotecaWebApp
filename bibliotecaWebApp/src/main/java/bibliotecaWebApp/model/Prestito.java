package bibliotecaWebApp.model;

public class Prestito {

	private String titolo;
	private String username;
	private int idTessera;
	private int quantita;
	private String dataAffitto;
	private String dataDiFine;
	private int idPrestito;
	
	
	
	
	public Prestito(String titolo, String username, int idTessera, int quantita, String dataAffitto, String dataDiFine,
			int idPrestito) {
		super();
		this.titolo = titolo;
		this.username = username;
		this.idTessera = idTessera;
		this.quantita = quantita;
		this.dataAffitto = dataAffitto;
		this.dataDiFine = dataDiFine;
		this.idPrestito = idPrestito;
	}

	public Prestito(String titolo, String username, int idTessera, int quantita, String dataAffitto, String dataDiFine) {
		this.titolo = titolo;
		this.username = username;
		this.idTessera = idTessera;
		this.quantita = quantita;
		this.dataAffitto = dataAffitto;
		this.dataDiFine = dataDiFine;
	}
	
	
	public int getIdPrestito() {
		return idPrestito;
	}

	public void setIdPrestito(int idPrestito) {
		this.idPrestito = idPrestito;
	}

	public String getDataDiFine() {
		return dataDiFine;
	}

	public void setDataDiFine(String dataDiFine) {
		this.dataDiFine = dataDiFine;
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
	public int getIdTessera() {
		return idTessera;
	}
	public void setIdTessera(int idTessera) {
		this.idTessera = idTessera;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	
	}

