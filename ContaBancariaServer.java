import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ContaBancariaServer {
    public static void main(String[] args) {
        try {
            // Inicializa o serviço de registro RMI integrado na porta padrão 1099
            LocateRegistry.createRegistry(1099);
            System.out.println("[SERVER] Registro RMI inicializado na porta 1099.");

            // Instancia o objeto remoto com saldo inicial de R$ 1000.00
            ContaBancariaImpl conta = new ContaBancariaImpl(1000.00);

            // Registra o serviço no formato rmi://host:porta/NomeServico
            Naming.rebind("//localhost:1099/ContaBancariaService", conta);

            System.out.println("[SERVER] Servidor de Conta Bancária RMI pronto e aguardando clientes...");
        } catch (Exception e) {
            System.err.println("[SERVER] Erro fatal no servidor:");
            e.printStackTrace();
        }
    }
}
