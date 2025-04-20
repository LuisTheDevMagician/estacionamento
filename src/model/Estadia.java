package model;

import java.time.LocalDateTime;

public class Estadia {
    private int id;
    private LocalDateTime horarioEntrada;
    private LocalDateTime horarioSaida;
    private int idCliente;
    private int idVeiculo;
    private float valor;

    public Estadia() {}

    public Estadia(LocalDateTime horarioEntrada, LocalDateTime horarioSaida, int idCliente, int idVeiculo, float valor) {
        this.horarioEntrada = horarioEntrada;
        this.horarioSaida = horarioSaida;
        this.idCliente = idCliente;
        this.idVeiculo = idVeiculo;
        this.valor = valor;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(LocalDateTime horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public LocalDateTime getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(LocalDateTime horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Entrada: " + horarioEntrada + " | Saída: " + horarioSaida +
                " | ID Cliente: " + idCliente + " | ID Veículo: " + idVeiculo + " | Valor: R$" + valor;
    }
}