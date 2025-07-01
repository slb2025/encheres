package fr.eni.bo;

public class Retrait {

    private String rue;
    private String CodePostal;
    private String ville;

    //constructeur

    public Retrait(String rue, String codePostal, String ville) {
        this.rue = rue;
        CodePostal = codePostal;
        this.ville = ville;
    }

    //getters and setters

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return CodePostal;
    }

    public void setCodePostal(String codePostal) {
        CodePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

}
