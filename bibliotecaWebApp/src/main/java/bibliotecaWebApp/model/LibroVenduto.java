package bibliotecaWebApp.model;

public class LibroVenduto {
	
		
		
		
		private int idScontrino;
		private String username;
		private String titolo;
		private int quantita;
		
		
		
		public LibroVenduto(int idScontrino, String username, String titolo, int quantita) {
			
			this.idScontrino = idScontrino;
			this.username = username;
			this.titolo = titolo;
			this.quantita = quantita;
			
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

		@Override
		public String toString() {
			return "LibroVenduto [idScontrino=" + idScontrino + ", username=" + username + ", titolo=" + titolo
					+ ", quantita=" + quantita + "]";
		}
		
		
		
		

	}

