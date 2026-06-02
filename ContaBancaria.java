import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ContaBancaria extends Remote {
    void deposita(double valor) throws RemoteException;
    boolean retira(double valor) throws RemoteException;
    double saldo() throws RemoteException;
}
