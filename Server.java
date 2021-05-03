import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Remote;
import java.io.Serializable;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.*;

public class Server extends UnicastRemoteObject implements Stocks, Serializable{
    double balance;
    Map<String, Double> stockData = new HashMap<>();
    
    public Server(double balance) throws RemoteException{
        this.balance = balance;
        stockData.put("A", 1.0);
        stockData.put("B", 2.0);
        stockData.put("C", 3.0);
        stockData.put("D", 4.0);
    }

    public String getBalance() throws RemoteException {
        return "Your balance is: " + balance;
    }

    public String buy(String stock, int num) throws RemoteException {
        double price = stockData.get(stock);
        if(price * num > balance) {
            return "You do not have enough money";
        }
        else {
            balance -= price * num;
            return "You have bought " + num + " of stock: " + stock + ".\nYour remaining balance is: " + balance; 
        }
    }

    public String sell(String stock, int num) throws RemoteException {
        double price = stockData.get(stock);
        balance += price * num;
        return "You have sold " + num + " of stock: " + stock + ".\nYour remaining balance is: " + balance; 
    }

    public static void main(String[] args) 
    {
        try {
            Registry r = LocateRegistry.createRegistry(1099);
            r.rebind("server", new Server(100.00));
            //String ip = "rmi://localhost:1099/";
            //Naming.bind(ip, server);
            System.out.println("Server is running...");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
