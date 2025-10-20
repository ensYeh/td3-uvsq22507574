package fr.uvsq.cprog.collex;

import java.io.IOException;

public class AjoutCommande implements Commande {
    private final AdresseIP ip;
    private final NomMachine nomMachine;

    public AjoutCommande(AdresseIP ip, NomMachine nomMachine) {
        this.ip = ip;
        this.nomMachine = nomMachine;
    }

    @Override
    public String execute(Dns dns) {
        try {
            dns.addItem(ip, nomMachine);
            return " Ajout réussi : " + nomMachine + " -> " + ip;
        } catch (IOException e) {
            return "⚠️ Erreur d’écriture dans le fichier : " + e.getMessage();
        } catch (IllegalArgumentException e) {
            return " pufff" + e.getMessage();
        }
    }
}
