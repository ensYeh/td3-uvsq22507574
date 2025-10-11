package fr.uvsq.cprog.collex;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Dns {
    private final List<DnsItem> items;
    private final String fichierMachines;

    public Dns() throws IOException {
        this.items = new ArrayList<>();

        // Charger le fichier de propriétés
        Properties props = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            props.load(input);
        }

        // Récupérer le nom du fichier de la base de données
        this.fichierMachines = props.getProperty("fichier.machines");

        // Charger les lignes du fichier machine.txt
        List<String> lignes = Files.readAllLines(Path.of(fichierMachines));

        for (String line : lignes) {
            String[] parts = line.split("\t");
            if (parts.length == 2) {
                String nomMachineComplet = parts[0];
                String adresseIPStr = parts[1];

                // Découper l’adresse IP
                String[] ipParts = adresseIPStr.split("\\.");
                int o1 = Integer.parseInt(ipParts[0]);
                int o2 = Integer.parseInt(ipParts[1]);
                int o3 = Integer.parseInt(ipParts[2]);
                int o4 = Integer.parseInt(ipParts[3]);
                AdresseIP ip = new AdresseIP(o1, o2, o3, o4);

                // Séparer nom et domaine
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
    // Récupère un item par adresse IP
    public DnsItem getItem(AdresseIP ip) {
        for (DnsItem item : items) {
            if (item.getAdresseIP().toString().equals(ip.toString())) {
                return item;
            }
        }
        return null; // non trouvé
    }

    // Récupère un item par nom de machine
    public DnsItem getItem(NomMachine nm) {
        for (DnsItem item : items) {
            if (item.getNomMachine().toString().equals(nm.toString())) {
                return item;
            }
        }
        return null; // non trouvé
    }

    // Récupère tous les items d’un domaine donné
    public List<DnsItem> getItems(String domaine) {
        List<DnsItem> resultat = new ArrayList<>();
        for (DnsItem item : items) {
            if (item.getNomMachine().getNomdomaine().equals(domaine)) {
                resultat.add(item);
            }
        }
        return resultat;
    }

    // Ajoute un item dans la base
    public void addItem(AdresseIP ip, NomMachine nm) throws IOException {
        // Vérifie si l’adresse ou le nom existe déjà
        for (DnsItem item : items) {
            if (item.getAdresseIP().toString().equals(ip.toString())) {
                throw new IllegalArgumentException("ERREUR : L’adresse IP existe déjà !");
            }
            if (item.getNomMachine().toString().equals(nm.toString())) {
                throw new IllegalArgumentException("ERREUR : Le nom de machine existe déjà !");
            }
        }

        // Ajout dans la liste
        items.add(new DnsItem(ip, nm));

        // Mise à jour du fichier
        List<String> lignes = new ArrayList<>();
        for (DnsItem item : items) {
            lignes.add(item.getNomMachine().toString() + "\t" + item.getAdresseIP().toString());
        }
        Files.write(Path.of(fichierMachines), lignes);
    }

}
