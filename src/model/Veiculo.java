package model;

public class Veiculo {
    private int id;
    private String marca;
    private String modelo;
    private String placa;
    private int idCliente;

    public Veiculo() {}

    public Veiculo(String marca, String modelo, String placa, int idCliente) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.idCliente = idCliente;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Placa: " + placa + " | Modelo: " + modelo + " | Marca: " + marca + " | ID Cliente: " + idCliente;
    }
}