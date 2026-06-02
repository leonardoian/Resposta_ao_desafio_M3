import java.rmi.Naming;
import java.util.Scanner;

public class ContaBancariaClient {
    public static void main(String[] args) {
        try {
            // Localiza o objeto remoto registrado no servidor
            ContaBancaria conta = (ContaBancaria) Naming.lookup("//localhost:1099/ContaBancariaService");
            System.out.println("=== Conectado com sucesso à Conta Bancária Remota ===");

            Scanner scanner = new Scanner(System.in);
            int opcao = 0;

            while (opcao != 4) {
                System.out.println("\n--- MENU DE OPERAÇÕES ---");
                System.out.println("1. Consultar Saldo");
                System.out.println("2. Realizar Depósito");
                System.out.println("3. Realizar Retirada (Saque)");
                System.out.println("4. Sair");
                System.out.print("Escolha uma opção: ");
                
                if (scanner.hasNextInt()) {
                    opcao = scanner.nextInt();
                } else {
                    scanner.next(); // Limpa entrada inválida
                    continue;
                }

                switch (opcao) {
                    case 1:
                        System.out.println("Saldo Atual: R$ " + conta.saldo());
                        break;
                    case 2:
                        System.out.print("Digite o valor para depósito: R$ ");
                        double valorDep = scanner.nextDouble();
                        conta.deposita(valorDep);
                        System.out.println("Operação de depósito enviada ao servidor.");
                        break;
                    case 3:
                        System.out.print("Digite o valor para retirada: R$ ");
                        double valorRet = scanner.nextDouble();
                        boolean sucesso = conta.retira(valorRet);
                        if (sucesso) {
                            System.out.println("Retirada efetuada com sucesso!");
                        } else {
                            System.out.println("Erro: Saldo insuficiente para realizar a retirada.");
                        }
                        break;
                    case 4:
                        System.out.println("Encerrando o cliente bancário RMI. Até logo!");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.err.println("[CLIENT] Erro durante a execução do cliente:");
            e.printStackTrace();
        }
    }
}
