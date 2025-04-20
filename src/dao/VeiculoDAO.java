package dao;

import model.Veiculo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {
    private Connection conexao;

    public VeiculoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    // Create
    public void cadastrar(Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO veiculos (marca, modelo, placa, id_cliente) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, veiculo.getMarca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setString(3, veiculo.getPlaca());
            stmt.setInt(4, veiculo.getIdCliente());
            stmt.execute();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    veiculo.setId(rs.getInt(1));
                }
            }
        }
    }

    // Read
    public Veiculo buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM veiculos WHERE id = ?";
        Veiculo veiculo = null;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    veiculo = new Veiculo();
                    veiculo.setId(rs.getInt("id"));
                    veiculo.setMarca(rs.getString("marca"));
                    veiculo.setModelo(rs.getString("modelo"));
                    veiculo.setPlaca(rs.getString("placa"));
                    veiculo.setIdCliente(rs.getInt("id_cliente"));
                }
            }
        }
        return veiculo;
    }

    public Veiculo buscarPorPlaca(String placa) throws SQLException {
        String sql = "SELECT * FROM veiculos WHERE placa = ?";
        Veiculo veiculo = null;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, placa);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    veiculo = new Veiculo();
                    veiculo.setId(rs.getInt("id"));
                    veiculo.setMarca(rs.getString("marca"));
                    veiculo.setModelo(rs.getString("modelo"));
                    veiculo.setPlaca(rs.getString("placa"));
                    veiculo.setIdCliente(rs.getInt("id_cliente"));
                }
            }
        }
        return veiculo;
    }

    // Update
    public void atualizar(Veiculo veiculo) throws SQLException {
        String sql = "UPDATE veiculos SET marca = ?, modelo = ?, placa = ?, id_cliente = ? WHERE id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getMarca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setString(3, veiculo.getPlaca());
            stmt.setInt(4, veiculo.getIdCliente());
            stmt.setInt(5, veiculo.getId());
            stmt.execute();
        }
    }

    // Delete
    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM veiculos WHERE id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
        }
    }

    // List all
    public List<Veiculo> listarTodos() throws SQLException {
        String sql = "SELECT * FROM veiculos";
        List<Veiculo> veiculos = new ArrayList<>();

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getInt("id"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setIdCliente(rs.getInt("id_cliente"));
                veiculos.add(veiculo);
            }
        }
        return veiculos;
    }

    public List<Veiculo> listarPorCliente(int idCliente) throws SQLException {
        String sql = "SELECT * FROM veiculos WHERE id_cliente = ?";
        List<Veiculo> veiculos = new ArrayList<>();

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Veiculo veiculo = new Veiculo();
                    veiculo.setId(rs.getInt("id"));
                    veiculo.setMarca(rs.getString("marca"));
                    veiculo.setModelo(rs.getString("modelo"));
                    veiculo.setPlaca(rs.getString("placa"));
                    veiculo.setIdCliente(rs.getInt("id_cliente"));
                    veiculos.add(veiculo);
                }
            }
        }
        return veiculos;
    }
}