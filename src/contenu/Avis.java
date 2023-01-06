package src.contenu;
public class Avis {
    private String nomClient;
    private String date;
    private String contenu;
    private Article articleAssocie;
    private double note;

    // Getters

    public String getNomClient(){
        return nomClient;
    }
    public String getDate(){
        return date;
    }
    public String getContenu(){
        return contenu;
    }
    public Article getArticleAssocie(){
        return articleAssocie;
    }
    public double getNote(){
        return note;
    }

    // Constructors
    public Avis(double note, String contenu, String nomClient, String date, Article articleAssocie){
        this.note = note;
        this.contenu = contenu;
        this.nomClient = nomClient;
        this.date = date;
        this.articleAssocie = articleAssocie;
        // TODO : Récupérer les infos de la BDD
    }
    public Avis(double note, String nomClient, String date, Article articleAssocie){
        this.note = note;
        this.contenu = null;
        this.nomClient = nomClient;
        this.date = date;
        this.articleAssocie = articleAssocie;
        // TODO : Récupérer les infos de la BDD
    }

    // Methods
    public void supprimeAvisBDD(boolean gardeNote){
        // TODO
    }




}
