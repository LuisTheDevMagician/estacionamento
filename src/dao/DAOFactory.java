package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/estacionamento";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static ClienteDAO getClienteDAO() throws SQLException {
        return new ClienteDAO(getConnection());
    }

    public static VeiculoDAO getVeiculoDAO() throws SQLException {
        return new VeiculoDAO(getConnection());
    }

    public static EstadiaDAO getEstadiaDAO() throws SQLException {
        return new EstadiaDAO(getConnection());
    }
}