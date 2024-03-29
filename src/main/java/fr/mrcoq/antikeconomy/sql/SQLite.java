package fr.mrcoq.antikeconomy.sql;

import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.mrcoq.antikeconomy.AntikEconomy;

public class SQLite {

	private AntikEconomy main;
	private String dbName;
	private static Connection connection;

	public SQLite(String dbName) {
		this.main = AntikEconomy.getInstance();
		this.dbName = dbName;
		connect();
		
		execute("CREATE TABLE IF NOT EXISTS Balances ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "uuid string not null,"
				+ "money double not null"
				+ ");");
	}

	private void connect() {
		File file = new File(main.getDataFolder(), this.dbName + ".db");
		if (!file.exists())
			new File(main.getDataFolder().getPath()).mkdir();
		String URL = "jdbc:sqlite:" + file;

		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void execute(String str) {
		PreparedStatement request = null;
		try {
			request = connection.prepareStatement(str);
			request.execute();
			request.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int executeID(String str) {
		PreparedStatement request = null;
		int id = 0;
		try {
			request = connection.prepareStatement(str, Statement.RETURN_GENERATED_KEYS);
			request.execute();
			ResultSet result = request.getGeneratedKeys();

			while (result.next()) {
				id = result.getInt(1);
			}

			request.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	public static List<Map<String, String>> fetchAll(String str) {
		PreparedStatement request = null;
		try {
			request = connection.prepareStatement(str);
			ResultSet result = request.executeQuery();
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			while (result.next()) {
				Map<String, String> map = new HashMap<String, String>();

				for (int i = 0; i < result.getMetaData().getColumnCount(); i++) {
					map.put(result.getMetaData().getColumnName(i + 1), result.getString(i + 1));
				}
				list.add(map);
			}
			request.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String, String> fetch(String str) {
		PreparedStatement request = null;
		try {
			request = connection.prepareStatement(str);
			ResultSet result = request.executeQuery();
			Map<String, String> map = new HashMap<String, String>();
			while (result.next()) {
				for (int i = 0; i < result.getMetaData().getColumnCount(); i++) {
					map.put(result.getMetaData().getColumnName(i + 1), result.getString(i + 1));
				}
			}
			request.close();
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
