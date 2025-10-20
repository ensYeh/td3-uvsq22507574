package fr.uvsq.cprog.collex;

public class NomMachine {
    private final String nommachine;
    private final String nomdomaine;

    public NomMachine(String nommachine, String nomdomaine) {
        this.nommachine = nommachine;
        this.nomdomaine = nomdomaine;
    }

    // ✅ Méthode statique plus claire pour créer à partir d’un nom complet
    public static NomMachine fromString(String nomComplet) {
        int index = nomComplet.indexOf(".");
        if (index == -1) {
            throw new IllegalArgumentException("Nom complet invalide : " + nomComplet);
        }
        String nommachine = nomComplet.substring(0, index);
        String nomdomaine = nomComplet.substring(index + 1);
        return new NomMachine(nommachine, nomdomaine);
    }

    public String getNommachine() {
        return nommachine;
    }

    public String getNomdomaine() {
        return nomdomaine;
    }

    public String getNomcomplet() {
        return nommachine + "." + nomdomaine;
    }

    @Override
    public String toString() {
        return getNomcomplet();
    }
}
