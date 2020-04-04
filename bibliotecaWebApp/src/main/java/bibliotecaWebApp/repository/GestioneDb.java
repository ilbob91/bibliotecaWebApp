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
	public void insertLibro(Libro l) throws SQLException {
		PreparedStatement statement = connessione.prepareStatement("insert into libro (titolo, autore, prezzo, disponibilita, quantita) values (?,?,?,?,?);");
		statement.setString(1, l.getTitolo() );
		statement.setString(2, l.getAutore());
		statement.setDouble(3, l.getPrezzo());
		statement.setInt(4, l.getDisponibilita());
		statement.setInt(5, l.getQuantita());
		statement.execute();
	}
	public List<Libro> getLibri(Libro l) throws SQLException, ClassNotFoundException {
		insertLibro(l);
		List<Libro> elenco = stampaLibri();
		
		return elenco;

	}

	public Utente insertUtente(String user, String pass) throws SQLException {
		PreparedStatement statement = connessione
				.prepareStatement("insert into utente (username, password, active) values (?,?,?);");
		statement.setString(1, user);
		statement.setString(2, pass);
		statement.setBoolean(3, false);
		statement.execute();
		Utente u = new Utente(user, pass, false);
        return u;
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

	public boolean controlloUtente(String user, String pass) throws SQLException {
		PreparedStatement state = connessione
				.prepareStatement("select * from utente where username = ? and password =?;");
		state.setString(1, user);
		state.setString(2, pass);
		ResultSet risultato = state.executeQuery();
		while (risultato.next()) {
			return true;
		}
		return false;
	}
	public Utente getUtente(String username, String password) throws SQLException {
		PreparedStatement statement = connessione
				.prepareStatement("select * from utente where username = ? and password = ?");
		statement.setString(1, username);
		statement.setString(2, password);
		ResultSet executeQuery = statement.executeQuery();
		while (executeQuery.next()) {

			String u = executeQuery.getString("username");
			String p = executeQuery.getString("password");
			boolean ac = executeQuery.getBoolean("active");
		
			return new Utente(u, p, ac);

		}
		return null;
	}
	public void validaUtente(String mailUtente) throws SQLException {
		PreparedStatement prepareStatement = this.connessione.prepareStatement("UPDATE utente SET active = ? WHERE (username = ?);");
		prepareStatement.setBoolean(1, true);
		prepareStatement.setString(2, mailUtente);
		prepareStatement.execute();
	}
	public boolean checkEmail(String user) throws SQLException {
		PreparedStatement state = connessione.prepareStatement("select * from utente where username = ? ;");
		state.setString(1, user);
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

	public void updateLibri(String titolo, int quantita, int qtV, int dsV) throws SQLException {
		String query3 = "update libro set quantita=?, disponibilita=? where titolo=?;";
		PreparedStatement statement3 = connessione.prepareStatement(query3);
		statement3.setInt(1, qtV + quantita);
		statement3.setInt(2, dsV + quantita);
		statement3.setString(3, titolo);
		statement3.execute();	
	}

	public int checkQuantita(String titolo) throws SQLException {
		PreparedStatement state = connessione.prepareStatement("select * from libro where titolo = ?;");
		state.setString(1, titolo);
		ResultSet risultato = state.executeQuery();
		while (risultato.next()) {
			return risultato.getInt("quantita");
		}

		return 0;
	}

	public int checkDisponibilita(String titolo) throws SQLException {
		PreparedStatement state = connessione.prepareStatement("select * from libro where titolo = ?;");
		state.setString(1, titolo);
		ResultSet risultato = state.executeQuery();
		while (risultato.next()) {
			return risultato.getInt("disponibilita");
		}

		return 0;
	}

	public List<Prestito> ordinaData() throws SQLException {
		PreparedStatement state = connessione.prepareStatement("select *  from prestito ORDER BY STR_TO_DATE(dataAffitto,\"%d/%m/%Y\") asc;");
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
