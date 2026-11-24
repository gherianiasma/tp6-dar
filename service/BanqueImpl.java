
package service;

import corbaBanque.*;
import java.util.ArrayList;

public class BanqueImpl extends IBanqueRemotePOA {

    private ArrayList<Compte> comptes = new ArrayList<>();

    // Créer un compte
    @Override
    public void creerCompte(Compte cpte) {

        for (Compte c : comptes) {
            if (c.code == cpte.code) {
                System.out.println("Compte deja existant !");
                return;
            }
        }

        comptes.add(cpte);
        System.out.println("Compte cree avec succes !");
    }

    // Verser de l'argent
    @Override
    public void verser(float mt, int code) {

        for (Compte c : comptes) {
            if (c.code == code) {
                if (mt > 0) {
                    c.solde += mt;
                    System.out.println("Versement effectue !");
                    return;
                }
            }
        }

        System.out.println("Compte introuvable ou montant invalide !");
    }

    // Retirer de l'argent
    @Override
    public void retirer(float mt, int code) {

        for (Compte c : comptes) {
            if (c.code == code) {
                if (mt > 0 && c.solde >= mt) {
                    c.solde -= mt;
                    System.out.println("Retrait effectue !");
                    return;
                }
            }
        }

        System.out.println("Retrait impossible !");
    }

    // Consulter un compte
    @Override
    public Compte getCompte(int code) {

        for (Compte c : comptes) {
            if (c.code == code) {
                return c;
            }
        }

        return null;
    }

    // Consulter tous les comptes
    @Override
    public Compte[] getComptes() {

        return comptes.toArray(new Compte[0]);
    }

    // Conversion Euro vers Dinar Tunisien
    @Override
    public double conversion(float mt) {

        return mt * 3.3;
    }
}
