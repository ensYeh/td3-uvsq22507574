package fr.uvsq.cprog.collex;

public class QuitterCommande implements Commande {
    @Override
    public String execute(Dns dns) {
        return "Fin du programme. À bientôt !";
    }
}
