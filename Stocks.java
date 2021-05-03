import java.rmi.*;

public interface Stocks extends Remote{
    public String getBalance() throws RemoteException;
    public String buy(String stock, int num) throws RemoteException;
    public String sell(String stock, int num) throws RemoteException;
}
