import dao.ClienteDAO;
import dao.DAOFactory;
import dao.EstadiaDAO;
import dao.VeiculoDAO;
import model.Cliente;
import model.Estadia;
import model.Veiculo;
import util.ConsoleUI;
import util.DateUtils;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            boolean executando = true;

            while (executando) {
                ConsoleUI.exibirMenuPrincipal();
                int opcao = ConsoleUI.lerInteiro("");

                switch (opcao) {
                    case 1:
                        gerenciarClientes();
                        break;
                    case 2:
                        gerenciarVeiculos();
                        break;
                    case 3:
                        gerenciarEstadias();
                        break;
                    case 0:
                        executando = false;
                        System.out.println("Sistema encerrado.");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }

    private static void gerenciarClientes() throws SQLException {
        boolean voltar = false;
        ClienteDAO clienteDAO = DAOFactory.getClienteDAO();

        while (!voltar) {
            ConsoleUI.exibirMenuClientes();
            int opcao = ConsoleUI.lerInteiro("");

            switch (opcao) {
                case 1: // Cadastrar
                    cadastrarCliente(clienteDAO);
                    break;
                case 2: // Listar
                    listarClientes(clienteDAO);
                    break;
                case 3: // Buscar por ID
                    buscarClientePorId(clienteDAO);
                    break;
                case 4: // Buscar por CPF
                    buscarClientePorCpf(clienteDAO);
                    break;
                case 5: // Atualizar
                    atualizarCliente(clienteDAO);
                    break;
                case 6: // Excluir
                    excluirCliente(clienteDAO);
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            ConsoleUI.pausar();
        }
    }

    private static void gerenciarVeiculos() throws SQLException {
        boolean voltar = false;
        VeiculoDAO veiculoDAO = DAOFactory.getVeiculoDAO();

        while (!voltar) {
            ConsoleUI.exibirMenuVeiculos();
            int opcao = ConsoleUI.lerInteiro("");

            switch (opcao) {
                case 1: // Cadastrar
                    cadastrarVeiculo(veiculoDAO);
                    break;
                case 2: // Listar
                    listarVeiculos(veiculoDAO);
                    break;
                case 3: // Buscar por ID
                    buscarVeiculoPorId(veiculoDAO);
                    break;
                case 4: // Buscar por Placa
                    buscarVeiculoPorPlaca(veiculoDAO);
                    break;
                case 5: // Atualizar
                    atualizarVeiculo(veiculoDAO);
                    break;
                case 6: // Excluir
                    excluirVeiculo(veiculoDAO);
                    break;
                case 7: // Listar por Cliente
                    listarVeiculosPorCliente(veiculoDAO);
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            ConsoleUI.pausar();
        }
    }

    private static void gerenciarEstadias() throws SQLException {
        boolean voltar = false;
        EstadiaDAO estadiaDAO = DAOFactory.getEstadiaDAO();

        while (!voltar) {
            ConsoleUI.exibirMenuEstadias();
            int opcao = ConsoleUI.lerInteiro("");

            switch (opcao) {
                case 1: // Registrar Entrada
                    registrarEntradaVeiculo(estadiaDAO);
                    break;
                case 2: // Registrar Saída
                    registrarSaidaVeiculo(estadiaDAO);
                    break;
                case 3: // Listar Todas
                    listarEstadias(estadiaDAO);
                    break;
                case 4: // Buscar por ID
                    buscarEstadiaPorId(estadiaDAO);
                    break;
                case 5: // Listar por Cliente
                    listarEstadiasPorCliente(estadiaDAO);
                    break;
                case 6: // Listar por Veículo
                    listarEstadiasPorVeiculo(estadiaDAO);
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
            ConsoleUI.pausar();
        }
    }

    // Métodos para Clientes
    private static void cadastrarCliente(ClienteDAO clienteDAO) throws SQLException {
        String nome = ConsoleUI.lerString("Nome do cliente: ");
        String cpf = ConsoleUI.lerString("CPF do cliente (apenas números): ");

        Cliente cliente = new Cliente(nome, cpf);
        clienteDAO.cadastrar(cliente);
        System.out.println("Cliente cadastrado com sucesso! ID: " + cliente.getId());
    }

    private static void listarClientes(ClienteDAO clienteDAO) throws SQLException {
        List<Cliente> clientes = clienteDAO.listarTodos();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            System.out.println("\n=== LISTA DE CLIENTES ===");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }

    private static void buscarClientePorId(ClienteDAO clienteDAO) throws SQLException {
        int id = ConsoleUI.lerInteiro("ID do cliente: ");
        Cliente cliente = clienteDAO.buscarPorId(id);

        if (cliente != null) {
            System.out.println("\n=== CLIENTE ENCONTRADO ===");
            System.out.println(cliente);
        } else {
            System.out.println("Cliente não encontrado com o ID informado.");
        }
    }

    private static void buscarClientePorCpf(ClienteDAO clienteDAO) throws SQLException {
        String cpf = ConsoleUI.lerString("CPF do cliente (apenas números): ");
        Cliente cliente = clienteDAO.buscarPorCpf(cpf);

        if (cliente != null) {
            System.out.println("\n=== CLIENTE ENCONTRADO ===");
            System.out.println(cliente);
        } else {
            System.out.println("Cliente não encontrado com o CPF informado.");
        }
    }

    private static void atualizarCliente(ClienteDAO clienteDAO) throws SQLException {
        int id = ConsoleUI.lerInteiro("ID do cliente a ser atualizado: ");
        Cliente cliente = clienteDAO.buscarPorId(id);

        if (cliente != null) {
            System.out.println("Dados atuais do cliente:");
            System.out.println(cliente);

            String nome = ConsoleUI.lerString("Novo nome (deixe em branco para manter): ");
            String cpf = ConsoleUI.lerString("Novo CPF (deixe em branco para manter): ");

            if (!nome.isEmpty()) cliente.setNome(nome);
            if (!cpf.isEmpty()) cliente.setCpf(cpf);

            clienteDAO.atualizar(cliente);
            System.out.println("Cliente atualizado com sucesso!");
        } else {
            System.out.println("Cliente não encontrado com o ID informado.");
        }
    }

    private static void excluirCliente(ClienteDAO clienteDAO) throws SQLException {
        int id = ConsoleUI.lerInteiro("ID do cliente a ser excluído: ");
        Cliente cliente = clienteDAO.buscarPorId(id);

        if (cliente != null) {
            System.out.println("Tem certeza que deseja excluir o cliente abaixo?");
            System.out.println(cliente);
            String confirmacao = ConsoleUI.lerString("Digite 'sim' para confirmar: ");

            if (confirmacao.equalsIgnoreCase("sim")) {
                clienteDAO.excluir(id);
                System.out.println("Cliente excluído com sucesso!");
            } else {
                System.out.println("Operação cancelada.");
            }
        } else {
            System.out.println("Cliente não encontrado com o ID informado.");
        }
    }

    // Métodos para Veículos
    private static void cadastrarVeiculo(VeiculoDAO veiculoDAO) throws SQLException {
        ClienteDAO clienteDAO = DAOFactory.getClienteDAO();

        // Primeiro listamos os clientes disponíveis
        System.out.println("\n=== CLIENTES CADASTRADOS ===");
        List<Cliente> clientes = clienteDAO.listarTodos();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado. Cadastre um cliente primeiro!");
            return;
        }

        // Mostra ID e Nome de cada cliente
        for (Cliente cliente : clientes) {
            System.out.println("ID: " + cliente.getId() + " - Nome: " + cliente.getNome());
        }

        // Pede o ID do cliente com validação
        int idCliente;
        boolean clienteExiste;

        do {
            idCliente = ConsoleUI.lerInteiro("\nID do cliente proprietário: ");
            clienteExiste = false;

            // Verifica se o ID existe
            for (Cliente cliente : clientes) {
                if (cliente.getId() == idCliente) {
                    clienteExiste = true;
                    break;
                }
            }

            if (!clienteExiste) {
                System.out.println("ERRO: Cliente com ID " + idCliente + " não encontrado. Digite um ID válido!");
            }
        } while (!clienteExiste);

        // Restante do cadastro do veículo
        String marca = ConsoleUI.lerString("Marca do veículo: ");
        String modelo = ConsoleUI.lerString("Modelo do veículo: ");
        String placa = ConsoleUI.lerString("Placa do veículo: ");

        Veiculo veiculo = new Veiculo(marca, modelo, placa, idCliente);
        veiculoDAO.cadastrar(veiculo);
        System.out.println("Veículo cadastrado com sucesso! ID: " + veiculo.getId());
    }

    private static void listarVeiculos(VeiculoDAO veiculoDAO) throws SQLException {
        List<Veiculo> veiculos = veiculoDAO.listarTodos();

        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
        } else {
            System.out.println("\n=== LISTA DE VEÍCULOS ===");
            for (Veiculo veiculo : veiculos) {
                System.out.println(veiculo);
            }
        }
    }

    private static void buscarVeiculoPorId(VeiculoDAO veiculoDAO) throws SQLException {
        int id = ConsoleUI.lerInteiro("ID do veículo: ");
        Veiculo veiculo = veiculoDAO.buscarPorId(id);

        if (veiculo != null) {
            System.out.println("\n=== VEÍCULO ENCONTRADO ===");
            System.out.println(veiculo);
        } else {
            System.out.println("Veículo não encontrado com o ID informado.");
        }
    }

    private static void buscarVeiculoPorPlaca(VeiculoDAO veiculoDAO) throws SQLException {
        String placa = ConsoleUI.lerString("Placa do veículo: ");
        Veiculo veiculo = veiculoDAO.buscarPorPlaca(placa);

        if (veiculo != null) {
            System.out.println("\n=== VEÍCULO ENCONTRADO ===");
            System.out.println(veiculo);
        } else {
            System.out.println("Veículo não encontrado com a placa informada.");
        }
    }

    private static void atualizarVeiculo(VeiculoDAO veiculoDAO) throws SQLException {
        int id = ConsoleUI.lerInteiro("ID do veículo a ser atualizado: ");
        Veiculo veiculo = veiculoDAO.buscarPorId(id);

        if (veiculo != null) {
            System.out.println("Dados atuais do veículo:");
            System.out.println(veiculo);

            String marca = ConsoleUI.lerString("Nova marca (deixe em branco para manter): ");
            String modelo = ConsoleUI.lerString("Novo modelo (deixe em branco para manter): ");
            String placa = ConsoleUI.lerString("Nova placa (deixe em branco para manter): ");
            int idCliente = ConsoleUI.lerInteiro("Novo ID do cliente (digite 0 para manter): ");

            if (!marca.isEmpty()) veiculo.setMarca(marca);
            if (!modelo.isEmpty()) veiculo.setModelo(modelo);
            if (!placa.isEmpty()) veiculo.setPlaca(placa);
            if (idCliente != 0) veiculo.setIdCliente(idCliente);

            veiculoDAO.atualizar(veiculo);
            System.out.println("Veículo atualizado com sucesso!");
        } else {
            System.out.println("Veículo não encontrado com o ID informado.");
        }
    }

    private static void excluirVeiculo(VeiculoDAO veiculoDAO) throws SQLException {
        int id = ConsoleUI.lerInteiro("ID do veículo a ser excluído: ");
        Veiculo veiculo = veiculoDAO.buscarPorId(id);

        if (veiculo != null) {
            System.out.println("Tem certeza que deseja excluir o veículo abaixo?");
            System.out.println(veiculo);
            String confirmacao = ConsoleUI.lerString("Digite 'sim' para confirmar: ");

            if (confirmacao.equalsIgnoreCase("sim")) {
                veiculoDAO.excluir(id);
                System.out.println("Veículo excluído com sucesso!");
            } else {
                System.out.println("Operação cancelada.");
            }
        } else {
            System.out.println("Veículo não encontrado com o ID informado.");
        }
    }

    private static void listarVeiculosPorCliente(VeiculoDAO veiculoDAO) throws SQLException {
        int idCliente = ConsoleUI.lerInteiro("ID do cliente: ");
        List<Veiculo> veiculos = veiculoDAO.listarPorCliente(idCliente);

        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo encontrado para este cliente.");
        } else {
            System.out.println("\n=== VEÍCULOS DO CLIENTE ===");
            for (Veiculo veiculo : veiculos) {
                System.out.println(veiculo);
            }
        }
    }

    // Métodos para Estadias
    private static void registrarEntradaVeiculo(EstadiaDAO estadiaDAO) throws SQLException {
        int idVeiculo = ConsoleUI.lerInteiro("ID do veículo: ");
        int idCliente = ConsoleUI.lerInteiro("ID do cliente: ");

        Estadia estadia = new Estadia();
        estadia.setHorarioEntrada(LocalDateTime.now());
        estadia.setIdCliente(idCliente);
        estadia.setIdVeiculo(idVeiculo);
        estadia.setValor(0); // Valor será calculado na saída

        estadiaDAO.cadastrar(estadia);
        System.out.println("Entrada registrada com sucesso! ID da estadia: " + estadia.getId());
    }

    private static void registrarSaidaVeiculo(EstadiaDAO estadiaDAO) throws SQLException {
        int id = ConsoleUI.lerInteiro("ID da estadia: ");
        Estadia estadia = estadiaDAO.buscarPorId(id);

        if (estadia != null && estadia.getHorarioSaida() == null) {
            estadia.setHorarioSaida(LocalDateTime.now());

            // Cálculo simplificado do valor (R$5 por hora)
            long horas = java.time.Duration.between(estadia.getHorarioEntrada(), estadia.getHorarioSaida()).toHours();
            float valor = horas * 5;
            estadia.setValor(valor);

            estadiaDAO.atualizar(estadia);
            System.out.println("Saída registrada com sucesso!");
            System.out.println("Tempo estacionado: " + horas + " horas");
            System.out.println("Valor a pagar: R$" + valor);
        } else if (estadia == null) {
            System.out.println("Estadia não encontrada com o ID informado.");
        } else {
            System.out.println("Esta estadia já foi finalizada anteriormente.");
        }
    }

    private static void listarEstadias(EstadiaDAO estadiaDAO) throws SQLException {
        List<Estadia> estadias = estadiaDAO.listarTodos();

        if (estadias.isEmpty()) {
            System.out.println("Nenhuma estadia registrada.");
        } else {
            System.out.println("\n=== LISTA DE ESTADIAS ===");
            for (Estadia estadia : estadias) {
                System.out.println(estadia);
            }
        }
    }

    private static void buscarEstadiaPorId(EstadiaDAO estadiaDAO) throws SQLException {
        int id = ConsoleUI.lerInteiro("ID da estadia: ");
        Estadia estadia = estadiaDAO.buscarPorId(id);

        if (estadia != null) {
            System.out.println("\n=== ESTADIA ENCONTRADA ===");
            System.out.println(estadia);
        } else {
            System.out.println("Estadia não encontrada com o ID informado.");
        }
    }

    private static void listarEstadiasPorCliente(EstadiaDAO estadiaDAO) throws SQLException {
        int idCliente = ConsoleUI.lerInteiro("ID do cliente: ");
        List<Estadia> estadias = estadiaDAO.listarPorCliente(idCliente);

        if (estadias.isEmpty()) {
            System.out.println("Nenhuma estadia encontrada para este cliente.");
        } else {
            System.out.println("\n=== ESTADIAS DO CLIENTE ===");
            for (Estadia estadia : estadias) {
                System.out.println(estadia);
            }
        }
    }

    private static void listarEstadiasPorVeiculo(EstadiaDAO estadiaDAO) throws SQLException {
        int idVeiculo = ConsoleUI.lerInteiro("ID do veículo: ");
        List<Estadia> estadias = estadiaDAO.listarPorVeiculo(idVeiculo);

        if (estadias.isEmpty()) {
            System.out.println("Nenhuma estadia encontrada para este veículo.");
        } else {
            System.out.println("\n=== ESTADIAS DO VEÍCULO ===");
            for (Estadia estadia : estadias) {
                System.out.println(estadia);
            }
        }
    }
}