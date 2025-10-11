package fr.uvsq.cprog.collex;

public class AdresseIP {
    private final int octet1;
    private final int octet2;
    private final int octet3;
    private final int octet4;

    // 🔒 Constructeur privé (interne seulement)
    private AdresseIP(int o1, int o2, int o3, int o4) {
        if (!isOctetValide(o1) || !isOctetValide(o2) || !isOctetValide(o3) || !isOctetValide(o4)) {
            throw new IllegalArgumentException("Adresse IP non valide.");
        }
        this.octet1 = o1;
        this.octet2 = o2;
        this.octet3 = o3;
        this.octet4 = o4;
    }

    // ✅ Méthode principale pour créer une adresse IP depuis une chaîne
    public static AdresseIP fromString(String ipStr) {
        String[] parts = ipStr.split("\\.");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Adresse IP invalide : " + ipStr);
        }

        try {
            int o1 = Integer.parseInt(parts[0]);
            int o2 = Integer.parseInt(parts[1]);
            int o3 = Integer.parseInt(parts[2]);
            int o4 = Integer.parseInt(parts[3]);

            return new AdresseIP(o1, o2, o3, o4);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Adresse IP invalide : " + ipStr);
        }
    }

    // Vérification d’un octet
    private static boolean isOctetValide(int octet) {
        return octet >= 0 && octet <= 255;
    }

    // Getters
    public int getOctet1() { return octet1; }
    public int getOctet2() { return octet2; }
    public int getOctet3() { return octet3; }
    public int getOctet4() { return octet4; }

    @Override
    public String toString() {
        return octet1 + "." + octet2 + "." + octet3 + "." + octet4;
    }
}
