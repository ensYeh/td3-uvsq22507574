package fr.uvsq.cprog.collex;

public class AdresseIP {
    int octet1;
    int octet2;
    int octet3;
    int octet4;

    public AdresseIP(int octet1, int octet2, int octet3, int octet4)
    {
        if (!estoctetvalide(octet1) || !estoctetvalide(octet2) || !estoctetvalide(octet3) || !estoctetvalide(octet4)  )
        {
            throw new IllegalArgumentException("Adresse IP non valide. ");
        }


        this.octet1 = octet1;
        this.octet2 = octet2;
        this.octet3 = octet3;
        this.octet4 = octet4;
    }
    private boolean estoctetvalide(int octet)
    {
        if(octet>=0 && octet<=255)

            return true;

        return false;
    }

    public int getOctet1() {
        return octet1;
    }

    public void setOctet1(int octet1) {
        this.octet1 = octet1;
    }

    public int getOctet2() {
        return octet2;
    }

    public void setOctet2(int octet2) {
        this.octet2 = octet2;
    }

    public int getOctet3() {
        return octet3;
    }

    public void setOctet3(int octet3) {
        this.octet3 = octet3;
    }

    public int getOctet4() {
        return octet4;
    }

    public void setOctet4(int octet4) {
        this.octet4 = octet4;
    }

    @Override
    public String toString() {
                return octet1 + "." + octet2 + "." + octet3 + "." + octet4;
    }
}

