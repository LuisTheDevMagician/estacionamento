package util;

import java.util.Scanner;

public class ConsoleUI {
    private static Scanner scanner = new Scanner(System.in);

    public static void exibirMenuPrincipal() {
        System.out.println("\n=== SISTEMA DE ESTACIONAMENTO ===");
        System.out.println("1. Gerenciar Clientes");
        System.out.println("2. Gerenciar Veículos");
        System.out.println("3. Gerenciar Estadias");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void exibirMenuClientes() {
        System.out.println("\n=== GERENCIAMENTO DE CLIENTES ===");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Listar Clientes");
        System.out.println("3. Buscar Cliente por ID");
        System.out.println("4. Buscar Cliente por CPF");
        System.out.println("5. Atualizar Cliente");
        System.out.println("6. Excluir Cliente");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
    }

    public static void exibirMenuVeiculos() {
        System.out.println("\n=== GERENCIAMENTO DE VEÍCULOS ===");
        System.out.println("1. Cadastrar Veículo");
        System.out.println("2. Listar Veículos");
        System.out.println("3. Buscar Veículo por ID");
        System.out.println("4. Buscar Veículo por Placa");
        System.out.println("5. Atualizar Veículo");
        System.out.println("6. Excluir Veículo");
        System.out.println("7. Listar Veículos por Cliente");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
    }

    public static void exibirMenuEstadias() {
        System.out.println("\n=== GERENCIAMENTO DE ESTADIAS ===");
        System.out.println("1. Registrar Entrada de Veículo");
        System.out.println("2. Registrar Saída de Veículo");
        System.out.println("3. Listar Todas as Estadias");
        System.out.println("4. Buscar Estadia por ID");
        System.out.println("5. Listar Estadias por Cliente");
        System.out.println("6. Listar Estadias por Veículo");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
    }

    public static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, digite um número inteiro válido.");
            scanner.next();
            System.out.print(mensagem);
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        return valor;
    }

    public static float lerFloat(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextFloat()) {
            System.out.println("Por favor, digite um número válido.");
            scanner.next();
            System.out.print(mensagem);
        }
        float valor = scanner.nextFloat();
        scanner.nextLine(); // Limpar o buffer
        return valor;
    }

    public static String lerString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public static void pausar() {
        System.out.println("\nPressione Enter para continuar...");
        scanner.nextLine();
    }
}