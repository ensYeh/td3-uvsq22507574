package fr.uvsq.cprog.collex;

public class RechercheNomCommande implements Commande {
    private final NomMachine nomMachine;

    public RechercheNomCommande(NomMachine nomMachine) {
        this.nomMachine = nomMachine;
    }

    @Override
    public String execute(Dns dns) {
        DnsItem item = dns.getItem(nomMachine);
        if (item == null) {
            return "Aucune adresse IP trouvée pour : " + nomMachine;
        }
        return "Résultat : " + item.getAdresseIP();
    }
}
