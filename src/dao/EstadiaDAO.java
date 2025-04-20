package dao;

import model.Estadia;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EstadiaDAO {
    private Connection conexao;

    public EstadiaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    // Create
    public void cadastrar(Estadia estadia) throws SQLException {
        String sql = "INSERT INTO estadia (horario_entrada, horario_saida, id_cliente, id_veiculo, valor) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, Timestamp.valueOf(estadia.getHorarioEntrada()));
            stmt.setTimestamp(2, Timestamp.valueOf(estadia.getHorarioSaida()));
            stmt.setInt(3, estadia.getIdCliente());
            stmt.setInt(4, estadia.getIdVeiculo());
            stmt.setFloat(5, estadia.getValor());
            stmt.execute();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    estadia.setId(rs.getInt(1));
                }
            }
        }
    }

    // Read
    public Estadia buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM estadia WHERE id = ?";
        Estadia estadia = null;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    estadia = new Estadia();
                    estadia.setId(rs.getInt("id"));
                    estadia.setHorarioEntrada(rs.getTimestamp("horario_entrada").toLocalDateTime());
                    estadia.setHorarioSaida(rs.getTimestamp("horario_saida").toLocalDateTime());
                    estadia.setIdCliente(rs.getInt("id_cliente"));
                    estadia.setIdVeiculo(rs.getInt("id_veiculo"));
                    estadia.setValor(rs.getFloat("valor"));
                }
            }
        }
        return estadia;
    }

    // Update
    public void atualizar(Estadia estadia) throws SQLException {
        String sql = "UPDATE estadia SET horario_entrada = ?, horario_saida = ?, id_cliente = ?, id_veiculo = ?, valor = ? WHERE id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(estadia.getHorarioEntrada()));
            stmt.setTimestamp(2, Timestamp.valueOf(estadia.getHorarioSaida()));
            stmt.setInt(3, estadia.getIdCliente());
            stmt.setInt(4, estadia.getIdVeiculo());
            stmt.setFloat(5, estadia.getValor());
            stmt.setInt(6, estadia.getId());
            stmt.execute();
        }
    }

    // List all
    public List<Estadia> listarTodos() throws SQLException {
        String sql = "SELECT * FROM estadia";
        List<Estadia> estadias = new ArrayList<>();

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Estadia estadia = new Estadia();
                estadia.setId(rs.getInt("id"));
                estadia.setHorarioEntrada(rs.getTimestamp("horario_entrada").toLocalDateTime());
                estadia.setHorarioSaida(rs.getTimestamp("horario_saida").toLocalDateTime());
                estadia.setIdCliente(rs.getInt("id_cliente"));
                estadia.setIdVeiculo(rs.getInt("id_veiculo"));
                estadia.setValor(rs.getFloat("valor"));
                estadias.add(estadia);
            }
        }
        return estadias;
    }

    public List<Estadia> listarPorCliente(int idCliente) throws SQLException {
        String sql = "SELECT * FROM estadia WHERE id_cliente = ?";
        List<Estadia> estadias = new ArrayList<>();

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Estadia estadia = new Estadia();
                    estadia.setId(rs.getInt("id"));
                    estadia.setHorarioEntrada(rs.getTimestamp("horario_entrada").toLocalDateTime());
                    estadia.setHorarioSaida(rs.getTimestamp("horario_saida").toLocalDateTime());
                    estadia.setIdCliente(rs.getInt("id_cliente"));
                    estadia.setIdVeiculo(rs.getInt("id_veiculo"));
                    estadia.setValor(rs.getFloat("valor"));
                    estadias.add(estadia);
                }
            }
        }
        return estadias;
    }

    public List<Estadia> listarPorVeiculo(int idVeiculo) throws SQLException {
        String sql = "SELECT * FROM estadia WHERE id_veiculo = ?";
        List<Estadia> estadias = new ArrayList<>();

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idVeiculo);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Estadia estadia = new Estadia();
                    estadia.setId(rs.getInt("id"));
                    estadia.setHorarioEntrada(rs.getTimestamp("horario_entrada").toLocalDateTime());
                    estadia.setHorarioSaida(rs.getTimestamp("horario_saida").toLocalDateTime());
                    estadia.setIdCliente(rs.getInt("id_cliente"));
                    estadia.setIdVeiculo(rs.getInt("id_veiculo"));
                    estadia.setValor(rs.getFloat("valor"));
                    estadias.add(estadia);
                }
            }
        }
        return estadias;
    }
}