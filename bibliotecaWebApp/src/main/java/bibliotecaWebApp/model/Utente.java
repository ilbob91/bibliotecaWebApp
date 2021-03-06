package bibliotecaWebApp.model;

public class Utente {
	
	private String username;
	
	private String password;
	
	private boolean active;
	
	private String immagine;

	private String tipo;
	
	
	
	public Utente(String username, String password, boolean active, String immagine, String tipo) {
		super();
		this.username = username;
		this.password = password;
		this.active = active;
		this.immagine = immagine;
		this.tipo = tipo;
	}
	public Utente(String username, String password, boolean active, String immagine) {
		this.username = username;
		this.password = password;
		this.active = active;
		this.immagine= immagine;
	}
	public Utente(String username, String immagine) {
		this.username = username;
		this.immagine= immagine;
	}

	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
