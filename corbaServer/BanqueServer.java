
package corbaServer;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import javax.naming.Context;
import javax.naming.InitialContext;

import service.BanqueImpl;
import corbaBanque.IBanqueRemote;

public class BanqueServer {

    public static void main(String[] args) {

        try {

            // 1. Initialiser ORB
            ORB orb = ORB.init(args, null);

            // 2. Recuperer RootPOA
            POA rootPOA = POAHelper.narrow(
                    orb.resolve_initial_references("RootPOA")
            );

            // 3. Activer le POA Manager
            rootPOA.the_POAManager().activate();

            // 4. Creer le servant
            BanqueImpl banque = new BanqueImpl();

            // 5. Obtenir la reference CORBA
            org.omg.CORBA.Object ref =
                    rootPOA.servant_to_reference(banque);

            // 6. Configurer JNDI
            Context ctx = new InitialContext();

            // 7. Enregistrer le service
            ctx.rebind("BanqueService", ref);

            System.out.println("Serveur Banque demarre !");

            // 8. Attendre les requetes
            orb.run();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
