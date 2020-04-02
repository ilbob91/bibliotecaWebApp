package bibliotecaWebApp.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import com.mysql.cj.xdevapi.Result;

import bibliotecaWebApp.model.Acquisto;
import bibliotecaWebApp.model.Libro;
import bibliotecaWebApp.model.Prestito;
import bibliotecaWebApp.model.Utente;

public class GestioneDb {
	private Connection connessione;

	public GestioneDb() throws SQLException, ClassNotFoundException {
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("file.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Class.forName("com.mysql.cj.jdbc.Driver");
		String password = properties.getProperty("db.password");
		String username = properties.getProperty("db.username");
		String url = "jdbc:mysql://" + properties.getProperty("db.url")
				+ "?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC&createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false";
		this.connessione = DriverManager.getConnection(url, username, password);
	}

	public void close() throws SQLException {
		this.connessione.close();
	}

	public void insertUtente(Utente u) throws SQLException {
		PreparedStatement statement = connessione
				.prepareStatement("insert into utente (username, password) values (?,?);");
		statement.setString(1, u.getUsername());
		statement.setString(2, u.getPassword());
		statement.execute();

	}

	public int creaScontrino(String username) throws SQLException {

		PreparedStatement state = connessione
				.prepareStatement("insert into scontrino (idScontrino, data, nome) values (?,?,?);");
		java.util.Date data = new java.util.Date();
		DateFormat formato = DateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);
		int idScontrino = (int) (Math.random() * 1000 + Math.random() * 1000);
		state.setInt(1, idScontrino);
		state.setString(2, formato.format(data));
		state.setString(3, username);
		state.execute();
		return idScontrino;
	}

	public boolean controlloUtente(Utente u) throws SQLException {
		PreparedStatement state = connessione
				.prepareStatement("select * from utente where username = ? and password =?;");
		state.setString(1, u.getUsername());
		state.setString(2, u.getPassword());
		ResultSet risultato = state.executeQuery();
		while (risultato.next()) {
			return true;
		}
		return false;
	}

	public boolean checkEmail(Utente u) throws SQLException {
		PreparedStatement state = connessione.prepareStatement("select * from utente where username = ? ;");
		state.setString(1, u.getUsername());
		ResultSet risultato = state.executeQuery();
		while (risultato.next()) {

			return false;
		}
		return true;
	}

	public List<Libro> stampaLibri() throws SQLException {
		PreparedStatement state = connessione.prepareStatement("select * from libro;");
		ResultSet risultato = state.executeQuery();
		List<Libro> lista = new ArrayList<>();
		while (risultato.next()) {
			Libro l = new Libro(risultato.getString("titolo"), risultato.getString("autore"),
					risultato.getDouble("prezzo"), risultato.getInt("disponibilita"), risultato.getInt("quantita"));
			lista.add(l);
		}
		return lista;
	}

	public List<Acquisto> stampaLibriVenduti() throws SQLException {
		PreparedStatement state = connessione.prepareStatement("select * from acquisto;");
		ResultSet risultato = state.executeQuery();
		List<Acquisto> lista = new ArrayList<>();
		while (risultato.next()) {
			Acquisto a = new Acquisto(risultato.getInt("idLibro"), risultato.getString("titolo"),
					risultato.getInt("quantita"), risultato.getDouble("prezzo"), risultato.getString("username"),
					risultato.getInt("idScontrino"));
			lista.add(a);
		}
		return lista;
	}

	public List<Prestito> stampaLibriPrestati() throws SQLException {
		PreparedStatement state = connessione.prepareStatement("select * from prestito;");
		ResultSet risultato = state.executeQuery();
		List<Prestito> lista = new ArrayList<>();
		while (risultato.next()) {
			Prestito a = new Prestito(risultato.getInt("idLibro"), risultato.getString("titolo"),
					risultato.getString("dataAffitto"), risultato.getString("username"));
			lista.add(a);
		}
		return lista;
	}
}
