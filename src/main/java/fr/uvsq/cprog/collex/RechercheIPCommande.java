package fr.uvsq.cprog.collex;

public class RechercheIPCommande implements Commande {
    private final AdresseIP ip;

    public RechercheIPCommande(AdresseIP ip) {
        this.ip = ip;
    }

    @Override
    public String execute(Dns dns) {
        DnsItem item = dns.getItem(ip);
        if (item == null) {
            return "Aucune machine trouvée pour l’adresse IP : " + ip;
        }
        return "Résultat : " + item.getNomMachine();
    }
}
