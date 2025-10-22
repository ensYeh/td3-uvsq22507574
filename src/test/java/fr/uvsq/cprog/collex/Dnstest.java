package fr.uvsq.cprog.collex;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.*;

    public class Dnstest {
        private Path tempFile;
        private Dns dns;

        @Before
        public void setUp() throws IOException {
            // Fichier temporaire pour les tests
            tempFile = Files.createTempFile("machines", ".txt");
            Files.write(tempFile, List.of(
                    "www.uvsq.fr 193.51.31.90",
                    "poste.uvsq.fr 193.51.31.154"
            ));

            // Créer un fichier config.properties temporaire
            Path props = Path.of("config.properties");
            Files.writeString(props, "fichier.machines=" + tempFile.toAbsolutePath());

            dns = new Dns(); // le constructeur lit le fichier
        }

        @Test
        public void testGetItemByIP() {
            AdresseIP ip = AdresseIP.fromString("193.51.31.90");
            DnsItem item = dns.getItem(ip);
            assertEquals("www", item.getNomMachine().getNommachine());
        }

        @Test
        public void testGetItemByName() {
            NomMachine nm = NomMachine.fromString("poste.uvsq.fr");
            DnsItem item = dns.getItem(nm);
            assertEquals("193.51.31.154", item.getAdresseIP().toString());
        }

        @Test
        public void testAddItem() throws IOException {
            AdresseIP ip = AdresseIP.fromString("193.51.25.24");
            NomMachine nm = NomMachine.fromString("pikachu.uvsq.fr");

            dns.addItem(ip, nm);

            List<DnsItem> items = dns.getItems("uvsq.fr");
            assertTrue(items.stream().anyMatch(i -> i.getNomMachine().getNommachine().equals("pikachu")));
        }

        @Test(expected = IllegalArgumentException.class)
        public void testAddExistingMachineThrowsException() throws IOException {
            AdresseIP ip = AdresseIP.fromString("193.51.31.90");
            NomMachine nm = NomMachine.fromString("www.uvsq.fr");
            dns.addItem(ip, nm);
        }
    }


