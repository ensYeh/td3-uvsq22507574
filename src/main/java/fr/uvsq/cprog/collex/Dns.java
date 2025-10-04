package fr.uvsq.cprog.collex;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class Dns {
    private List<DnsItem> Items;
    private String fichiersMachines;

    public Dns(String fichiersMachines) throws IOException {
        this.fichiersMachines = fichiersMachines;
        this.Items = new ArrayList<>();


        // lire chaque lignes
        List<String> lignes = readAllLines(Path.of(fichiersMachines));

        for (String line : lignes) {
            String[] parts = line.split("\t");
            if (parts.length == 2){
                String NomMachine = parts[0];
                String adresseIP = parts[1];
                String nomdomaine = "";
                Items.add(new DnsItem(new NomMachine(NomMachine, nomdomaine), new AdresseIP()));
            }
        }
    }


}















































