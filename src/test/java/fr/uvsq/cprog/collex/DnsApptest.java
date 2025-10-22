package fr.uvsq.cprog.collex;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class DnsApptest {

    @Test
    public void testRunSimulation() throws Exception {
        // --- 1️⃣ Préparer un fichier machine temporaire ---
        Path tempFile = Files.createTempFile("machines", ".txt");
        Files.write(tempFile, List.of(
                "www.uvsq.fr 193.51.31.90",
                "poste.uvsq.fr 193.51.31.154"
        ));

        // --- 2️⃣ Créer le fichier config.properties ---
        Path props = Path.of("config.properties");
        Files.writeString(props, "fichier.machines=" + tempFile.toAbsolutePath());

        // --- 3️⃣ Simuler une saisie utilisateur ---
        String userInput = "www.uvsq.fr\n193.51.31.90\nquit\n";
        InputStream input = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(input);

        // --- 4️⃣ Rediriger la sortie console ---
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // --- 5️⃣ Lancer l’application ---
        DnsApp app = new DnsApp();
        app.run();

        // --- 6️⃣ Récupérer la sortie console ---
        String output = outputStream.toString();

        // --- 7️⃣ Vérifier que tout s’est bien passé ---
        assertTrue(output.contains("Bienvenue"));
        assertTrue(output.contains("193.51.31.90"));
        assertTrue(output.contains("www.uvsq.fr"));
        assertTrue(output.contains("Fin du programme"));
    }
}

