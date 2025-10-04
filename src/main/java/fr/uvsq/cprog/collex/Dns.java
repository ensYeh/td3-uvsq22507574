package fr.uvsq.cprog.collex;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Dns {
    private final List<DnsItem> items;
    private final String fichierMachines;

    public Dns(String fichierMachines) throws IOException {
        this.fichierMachines = fichierMachines;
        this.items = new ArrayList<>();

        // Lire chaque ligne du fichier
        List<String> lignes = Files.readAllLines(Path.of(fichierMachines));

        for (String line : lignes) {
            String[] parts = line.split("\t");
            if (parts.length == 2) {
                String nomMachineComplet = parts[0];
                String adresseIPStr = parts[1];

                // Découper l’adresse en 4 octets
                String[] ipParts = adresseIPStr.split("\\.");
                int o1 = Integer.parseInt(ipParts[0]);
                int o2 = Integer.parseInt(ipParts[1]);
                int o3 = Integer.parseInt(ipParts[2]);
                int o4 = Integer.parseInt(ipParts[3]);

                AdresseIP ip = new AdresseIP(o1, o2, o3, o4);

                // Séparer machine et domaine (ex: www.uvsq.fr -> www et uvsq.fr)
                String[] machineParts = nomMachineComplet.split("\\.", 2);
                String nomMachine = machineParts[0];
                String nomDomaine = machineParts.length > 1 ? machineParts[1] : "";

                NomMachine nm = new NomMachine(nomMachine, nomDomaine);


                items.add(new DnsItem(ip, nm));
            }
        }

    }
    public List<DnsItem> getItems() {
        return items;
    }
    public DnsItem getItem(AdresseIP ip) {
        for (DnsItem item : items) {
            if (item.getAdresseIP().toString().equals(ip.toString())) {
                return item;
            }
        }
        return null; // ou tu peux lever une exception si IP non trouvée
    }

    public DnsItem getItem(String nomMachineComplet) {
        for (DnsItem item : items) {
            String fullName = item.getNomMachine().getNommachine() + "." + item.getNomMachine().getNomdomaine();
            if (fullName.equals(nomMachineComplet)) {
                return item;
            }
        }
        return null; // ou lever une exception
    }

    public List<DnsItem> getItems(String nomDomaine) {
        List<DnsItem> result = new ArrayList<>();
        for (DnsItem item : items) {
            if (item.getNomMachine().getNomdomaine().equals(nomDomaine)) {
                result.add(item);
            }
        }
        return result;
    }

    public void addItem(AdresseIP ip, NomMachine nomMachine) throws Exception {
        // Vérifier doublons
        for (DnsItem item : items) {
            if (item.getAdresseIP().toString().equals(ip.toString())) {
                throw new Exception("ERREUR : L'adresse IP existe déjà !");
            }
            String fullName = item.getNomMachine().getNommachine() + "." + item.getNomMachine().getNomdomaine();
            String newFullName = nomMachine.getNommachine() + "." + nomMachine.getNomdomaine();
            if (fullName.equals(newFullName)) {
                throw new Exception("ERREUR : Le nom de machine existe déjà !");
            }
        }

        // Ajouter à la liste
        items.add(new DnsItem(ip, nomMachine));

        // Mettre à jour le fichier
        List<String> lignes = new ArrayList<>();
        for (DnsItem item : items) {
            lignes.add(item.getNomMachine().getNommachine() + "." + item.getNomMachine().getNomdomaine() + "\t" + item.getAdresseIP());
        }
        Files.write(Path.of(fichierMachines), lignes);
    }

}
