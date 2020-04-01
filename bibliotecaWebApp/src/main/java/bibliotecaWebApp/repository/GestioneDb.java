package bibliotecaWebApp.repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

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
		String url = "jdbc:mysql://"+properties.getProperty("db.url")+"?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC&createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false";
		this.connessione = DriverManager.getConnection(url, username, password);
	}

	public void close() throws SQLException {
		this.connessione.close();
	}
	
	public void insertUtente(String nome, String password) throws SQLException {
		PreparedStatement statement = connessione.prepareStatement("insert into utente (username, password) values (?,?);");
		statement.setString(1, nome);
		statement.setString(2, password);
		statement.execute();

	}
}
