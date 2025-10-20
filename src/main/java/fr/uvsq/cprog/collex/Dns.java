package fr.uvsq.cprog.collex;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

// ... imports inchangés
public class Dns {
    private final List<DnsItem> items;
    private final Path fichierMachinesPath;

    public Dns() throws IOException {
        this.items = new ArrayList<>();

        // Charger le fichier de propriétés depuis les ressources
        Properties props = new Properties();
        try (InputStream input = Dns.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("config.properties introuvable sur le classpath");
            }
            props.load(input);
        }

        // Récupérer le nom du fichier de la base de données
        String fichierMachines = props.getProperty("fichier.machines");
        if (fichierMachines == null) {
            throw new IOException("Propriété 'fichier.machines' introuvable");
        }

        // Résoudre le Path : peut être relatif (ex: src/main/resources/machines.txt)
        this.fichierMachinesPath = Path.of(fichierMachines);

        // Si le fichier n'existe pas, le créer vide
        if (!Files.exists(fichierMachinesPath)) {
            Files.createDirectories(fichierMachinesPath.getParent() == null ? Path.of(".") : fichierMachinesPath.getParent());
            Files.write(fichierMachinesPath, List.of());
        }

        // Charger les lignes du fichier machine.txt
        List<String> lignes = Files.readAllLines(fichierMachinesPath);

        for (String line : lignes) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split("\\s+"); // séparation par tab ou espace
            if (parts.length == 2) {
                String nomMachineComplet = parts[0].trim();
                String adresseIPStr = parts[1].trim();

                try {
                    AdresseIP ip = AdresseIP.fromString(adresseIPStr);
                    NomMachine nm = NomMachine.fromString(nomMachineComplet);
                    items.add(new DnsItem(ip, nm));
                } catch (IllegalArgumentException e) {
                    // ignorer la ligne mal formée ou logguer
                    System.err.println("Ligne ignorée (mal formée) : " + line);
                }
            } else {
                // ligne mal formée -> log
                System.err.println("Ligne ignorée (format incorrect) : " + line);
            }
        }

    }
    public void addItem(AdresseIP ip, NomMachine nm) throws IOException {
        for (DnsItem item : items) {
            if (item.getAdresseIP().toString().equals(ip.toString())) {
                throw new IllegalArgumentException("ERREUR : L’adresse IP existe déjà !");
            }
            if (item.getNomMachine().toString().equals(nm.toString())) {
                throw new IllegalArgumentException("ERREUR : Le nom de machine existe déjà !");
            }
        }

        items.add(new DnsItem(ip, nm));

        List<String> lignes = new ArrayList<>();
        for (DnsItem item : items) {
            lignes.add(item.getNomMachine().toString() + "\t" + item.getAdresseIP().toString());
        }
        Files.write(fichierMachinesPath, lignes);
    }

}
// ... méthodes getItem, getItems, addItem (voir ci-dessous)
