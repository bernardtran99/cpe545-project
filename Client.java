import java.rmi.RMISecurityManager;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Remote;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.io.Serializable;

public class Client {

    public static void main(String[] args) 
    {
        try {
            //String ip = "rmi://localhost:1099/";
            Registry r = LocateRegistry.getRegistry("localhost", 1099);
            Stocks client = (Stocks)r.lookup("server");
            System.out.println(client.getBalance());
            System.out.println(client.buy("A", 2));
            System.out.println(client.buy("B", 1));
            System.out.println(client.buy("C", 1));
            System.out.println(client.sell("A", 1));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
