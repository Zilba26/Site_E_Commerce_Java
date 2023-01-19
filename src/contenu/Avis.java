package src.contenu;

public class Avis {
    private String nomClient;
    private String date;
    private String contenu;
    private Article articleAssocie;
    private double note;

    // Getters

    public String getNomClient() {
        return nomClient;
    }

    public String getDate() {
        return date;
    }

    public String getContenu() {
        return contenu;
    }

    public Article getArticleAssocie() {
        return articleAssocie;
    }

    public double getNote() {
        return note;
    }

    // Constructors
    public Avis(double note, String contenu, String nomClient, String date, Article articleAssocie) {
        this.note = note;
        this.contenu = contenu;
        this.nomClient = nomClient;
        this.date = date;
        this.articleAssocie = articleAssocie;
        // TODO : Récupérer les infos de la BDD
    }

    public Avis(double note, String nomClient, String date, Article articleAssocie) {
        this.note = note;
        this.contenu = null;
        this.nomClient = nomClient;
        this.date = date;
        this.articleAssocie = articleAssocie;
        // TODO : Récupérer les infos de la BDD
    }

    // Methods
    public String supprimeAvisBDD(boolean gardeNote) {
        // TODO : change println into BDD edition
        String ret = "";
        if (gardeNote) {
            ret += "Le contenu de l'avis de ";
        } else {
            ret += "L'avis de ";
        }
        ret += this.getNomClient();
        ret += " qui mentionnait '" + this.getContenu() + "'";
        ret += " datant du " + this.getDate();
        ret += " noté " + this.getNote() + "/5.0";
        ret += " associé à l'article '" + this.getArticleAssocie().getNom() + "'";
        ret += " a été supprimé.";
        // System.out.println(ret);
        return ret;
    }

    public void modifierAvis(double note, String contenu) {
        this.note = note;
        this.contenu = contenu;
    }

}
