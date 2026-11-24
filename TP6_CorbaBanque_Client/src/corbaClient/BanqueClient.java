
package corbaClient;

import org.omg.CORBA.ORB;

import javax.naming.Context;
import javax.naming.InitialContext;

import corbaBanque.*;

public class BanqueClient {

    public static void main(String[] args) {

        try {

            // 1. Initialiser ORB
            ORB orb = ORB.init(args, null);

            // 2. Recuperer l'annuaire JNDI
            Context ctx = new InitialContext();

            // 3. Recuperer la reference distante
            org.omg.CORBA.Object obj =
                    (org.omg.CORBA.Object) ctx.lookup("BanqueService");

            // 4. Convertir la reference en IBanqueRemote
            IBanqueRemote banque =
                    IBanqueRemoteHelper.narrow(obj);

            System.out.println("Connexion au serveur reussie !");

            // 5. Creer deux comptes
            Compte c1 = new Compte(1, 1000);
            Compte c2 = new Compte(2, 2000);

            banque.creerCompte(c1);
            banque.creerCompte(c2);

            // 6. Effectuer un versement
            banque.verser(500, 1);

            // 7. Effectuer un retrait
            banque.retirer(200, 1);

            // 8. Consulter un compte
            Compte compte = banque.getCompte(1);

            if (compte != null) {
                System.out.println("Code : " + compte.code);
                System.out.println("Solde : " + compte.solde);
            }

            // 9. Consulter tous les comptes
            Compte[] liste = banque.getComptes();

            System.out.println("Liste des comptes :");

            for (Compte c : liste) {
                System.out.println(
                        "Code : " + c.code +
                                " | Solde : " + c.solde
                );
            }

            // 10. Conversion Euro vers DT
            double resultat = banque.conversion(100);

            System.out.println(
                    "100 Euro = " + resultat + " DT"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
