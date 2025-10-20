package fr.uvsq.cprog.collex;

import java.util.List;

public class RechercheDomaineCommande implements Commande {
    private final String domaine;

    public RechercheDomaineCommande(String domaine) {
        this.domaine = domaine;
    }

    @Override
    public String execute(Dns dns) {
        List<DnsItem> items = dns.getItems(domaine);
        if (items.isEmpty()) {
            return "Aucun enregistrement trouvé pour le domaine : " + domaine;
        }

        StringBuilder sb = new StringBuilder("✅ Machines du domaine " + domaine + " :\n");
        for (DnsItem item : items) {
            sb.append("- ").append(item.getNomMachine().getNommachine())
                    .append(" -> ").append(item.getAdresseIP()).append("\n");
        }
        return sb.toString();
    }
}
