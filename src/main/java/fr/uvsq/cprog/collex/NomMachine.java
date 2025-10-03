package fr.uvsq.cprog.collex;

public class NomMachine {
    String nommachine;
    String nomdomaine;

    public NomMachine(String nommachine,  String nomdomaine) {
        this.nommachine = nommachine;
        this.nomdomaine = nomdomaine;
    }
    public String getNommachine() {
        return nommachine;
    }
    public void setNommachine(String nommachine) {
        this.nommachine = nommachine;
    }
    public String getNomdomaine() {
        return nomdomaine;
    }
    public void setNomdomaine(String nomdomaine) {
        this.nomdomaine = nomdomaine;
    }



}
