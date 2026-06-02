import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ContaBancariaImpl extends UnicastRemoteObject implements ContaBancaria {
    private double saldo;

    // Construtor obrigatório que lança RemoteException
    public ContaBancariaImpl(double saldoInicial) throws RemoteException {
        super(); // Garante a exportação do objeto remoto
        this.saldo = saldoInicial;
    }

    // Modificador synchronized impede depósitos simultâneos conflitantes
    @Override
    public synchronized void deposita(double valor) throws RemoteException {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("[SERVER] Depósito de R$ " + valor + " processado. Saldo atual: R$ " + this.saldo);
        }
    }

    // Modificador synchronized garante atomicidade na verificação e dedução do saldo
    @Override
    public synchronized boolean retira(double valor) throws RemoteException {
        if (valor > 0 && this.saldo >= valor) {
            this.saldo -= valor;
            System.out.println("[SERVER] Retirada de R$ " + valor + " processada. Saldo atual: R$ " + this.saldo);
            return true;
        }
        System.out.println("[SERVER] Tentativa de retirada de R$ " + valor + " negada (Saldo insuficiente).");
        return false;
    }

    // Leitura síncrona do saldo atual
    @Override
    public synchronized double saldo() throws RemoteException {
        return this.saldo;
    }
}
