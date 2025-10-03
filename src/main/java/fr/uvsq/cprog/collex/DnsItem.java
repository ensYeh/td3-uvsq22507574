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
   public void setAdresseIP(AdresseIP adresseIP) {
       this.adresseIP = adresseIP;
   }
   public NomMachine getNomMachine() {
       return nomMachine;
   }
   public void setNomMachine(NomMachine nomMachine) {
       this.nomMachine = nomMachine;
   }

}
