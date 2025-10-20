package fr.uvsq.cprog.collex;

public class DnsApp {
    public void run() {
        try {
            Dns dns = new Dns();
            DnsTUI tui = new DnsTUI();

            tui.affiche("=== Bienvenue dans le mini serveur DNS ===");

            boolean continuer = true;
            while (continuer) {
                Commande cmd = tui.nextCommande();
                if (cmd != null) {
                    String resultat = cmd.execute(dns);
                    tui.affiche(resultat);

                    if (cmd instanceof QuitterCommande) {
                        continuer = false;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new DnsApp().run();
    }
}
