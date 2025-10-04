package fr.uvsq.cprog.collex;

public class NomMachine {
    String nommachine;
    String nomdomaine;

    public NomMachine(String nommachine,  String nomdomaine) {
        this.nommachine = nommachine;
        this.nomdomaine = nomdomaine;
    }

    public static NomMachine NomMachine (String nomComplet){
        int index = nomComplet.indexOf(".");
        String nommachine = nomComplet.substring(0, index);
        String nomdomaine = nomComplet.substring(index+1);
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

    public String toString(){
        return getNomcomplet();
    }



}
