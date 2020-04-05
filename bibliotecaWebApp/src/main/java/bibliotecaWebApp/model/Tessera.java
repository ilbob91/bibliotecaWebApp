package bibliotecaWebApp.model;

public class Tessera {
	
		private int idTessera;
		private String username;
		private String dataAffitto;
		public Tessera(int idTessera, String username, String dataAffitto) {
			
			this.idTessera = idTessera;
			this.username = username;

			this.dataAffitto = dataAffitto;
		}
		public int getIdTessera() {
			return idTessera;
		}
		public void setIdTessera(int idTessera) {
			this.idTessera = idTessera;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}

		public String getDataAffitto() {
			return dataAffitto;
		}
		public void setDataAffitto(String dataAffitto) {
			this.dataAffitto = dataAffitto;
		}
		@Override
		public String toString() {
			return "Tessera [idTessera=" + idTessera + ", username=" + username +  ", dataAffitto="
					+ dataAffitto + "]";
		}



		}

