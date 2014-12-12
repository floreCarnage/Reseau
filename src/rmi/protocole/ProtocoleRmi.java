package rmi.protocole;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Garance on 12/12/2014.
 */
public interface ProtocoleRmi extends Remote {
        public String getMessage() throws RemoteException;
}
