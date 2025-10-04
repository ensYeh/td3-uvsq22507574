package fr.uvsq.cprog.collex;

public class DnsItem {
   AdresseIP adresseIP;
   NomMachine nomMachine;

   public DnsItem(AdresseIP adresseIP, NomMachine nomMachine) {
       this.adresseIP = adresseIP;
       this.nomMachine = nomMachine;
   }
   public AdresseIP getAdresseIP() {
        return adresseIP;
   }

   public NomMachine getNomMachine() {
       return nomMachine;
   }

   public String toString(){
       return adresseIP.toString()+" "+nomMachine.toString();
   }

}
