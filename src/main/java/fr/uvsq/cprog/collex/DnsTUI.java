package fr.uvsq.cprog.collex;

import java.util.Scanner;

public class DnsTUI {
    private final Scanner scanner;

    public DnsTUI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Analyse la saisie utilisateur et retourne la Commande correspondante.
     */
    public Commande nextCommande() {
        System.out.print("\n> Entrez une commande (help pour aide) : ");
        String saisie = scanner.nextLine().trim();

        if (saisie.equalsIgnoreCase("quit")) {
            return new QuitterCommande();
        }

        String[] parts = saisie.split("\\s+");
        if (parts.length == 0) return null;

        String cmd = parts[0].toLowerCase();

        try {
            switch (cmd) {
                case "searchip":
                    // Exemple: searchip 192.168.1.10
                    if (parts.length != 2) return null;
                    AdresseIP ip = AdresseIP.fromString(parts[1]);
                    return new RechercheIPCommande(ip);

                case "searchname":
                    // Exemple: searchname serveur.local
                    if (parts.length != 2) return null;
                    NomMachine nm = NomMachine.fromString(parts[1]);
                    return new RechercheNomCommande(nm);

                case "searchdomain":
                    // Exemple: searchdomain local
                    if (parts.length != 2) return null;
                    return new RechercheDomaineCommande(parts[1]);

                case "add":
                    // Exemple: add serveur2.local 192.168.1.50
                    if (parts.length != 3) return null;
                    NomMachine nmAdd = NomMachine.fromString(parts[1]);
                    AdresseIP ipAdd = AdresseIP.fromString(parts[2]);
                    return new AjoutCommande(ipAdd, nmAdd);

                case "help":
                    affiche("""
                        📘 Commandes disponibles :
                        - searchip <ip>         : recherche le nom associé à une IP
                        - searchname <nom>      : recherche l’adresse IP d’un nom
                        - searchdomain <domaine>: liste les machines d’un domaine
                        - add <nom> <ip>        : ajoute une nouvelle machine
                        - quit                  : quitte le programme
                        """);
                    return null;

                default:
                    affiche("Commande inconnue. Tapez 'help' pour la liste.");
                    return null;
            }
        } catch (Exception e) {
            affiche("⚠️ Erreur : " + e.getMessage());
            return null;
        }
    }

    /** Affiche un message ou un résultat. */
    public void affiche(String message) {
        System.out.println(message);
    }
}
