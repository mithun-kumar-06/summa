import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class SingleRMI {

    // ---------- 1. Interface ----------
    public interface ServerInterface extends Remote {
        String sayHello(String name) throws RemoteException;
    }

    // ---------- 2. Server Implementation ----------
    public static class ServerImpl extends UnicastRemoteObject implements ServerInterface {

        protected ServerImpl() throws RemoteException {
            super();
        }

        @Override
        public String sayHello(String name) throws RemoteException {
            System.out.println("Handled by: " + Thread.currentThread().getName());
            return "Hello " + name;
        }
    }

    // ---------- 3. Server Main ----------
    public static void startServer() {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("MyServer", new ServerImpl());
            System.out.println("Server started!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // ---------- 4. Client Main ----------
    public static void startClient() {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 1099);
            ServerInterface server = (ServerInterface) reg.lookup("MyServer");

            String msg = server.sayHello("Client");
            System.out.println(msg);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // ---------- MAIN PROGRAM ----------
    public static void main(String[] args) {

        // Run server and client in the same program
        startServer();
        startClient();
    }
}
