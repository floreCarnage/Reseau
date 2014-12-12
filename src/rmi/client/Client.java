package rmi.client;

import java.rmi.Naming;
import rmi.protocole.ProtocoleRmi;;

/**
 * Created by Garance on 12/12/2014.
 */
public class Client {

        public static void main(String args[]) throws Exception {
            ProtocoleRmi obj = (ProtocoleRmi) Naming.lookup("//localhost/ServeurRmi");
            System.out.println(obj.getMessage());
        }

}
