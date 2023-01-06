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
    public Avis(double note, String contenu){
        this.note = note;
        this.contenu = contenu;
        // TODO : Récupérer les infos de la BDD
    }
    public Avis(double note){
        this.note = note;
        // TODO : Récupérer les infos de la BDD
    }

    // Methods
    public void supprimeAvisBDD(boolean gardeNote){
        // TODO
    }




}
