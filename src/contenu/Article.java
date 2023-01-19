package src.contenu;

import java.util.ArrayList;

public class Article {

    private String nom;
    private int quantite;
    private ArrayList<Avis> listeAvis;
    private String photo;
    private double prix;
    private String description;
    private Categorie categorie;

    public Article(String nom, double prix, int quantite, String photo, String description, Categorie categorie) {
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.photo = photo;
        this.description = description;
        this.categorie = categorie;
        this.listeAvis = new ArrayList<Avis>();
    }

    public Article(String nom) {
        this.nom = nom;
        this.prix = 1;
        this.quantite = 5;
        this.photo = "";
        this.description = "description";
        this.categorie = null;
        this.listeAvis = new ArrayList<Avis>();
    }

    public void supprimerArticleBDD() {
        // TODO : Supprimer article dans Base De Données
        System.out.println("Article supprimé dans BDD");
    }

    public void modifierArticle(String nom, int quantite, double prix, String desc, Categorie cat) {
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
        this.description = desc;
        this.categorie = cat;
    }

    public void ajouterQuantite(int quantite) {
        if (quantite > 0)
            this.quantite += quantite;
    }

    public void retirerQuantite(int quantite) {
        if (quantite > 0 && this.quantite - quantite >= 0)
            this.quantite -= quantite;
    }

    public void ajouterAvis(Avis avis) {
        this.getListeAvis().add(avis);
    }

    public String supprimerAvis(Avis avis, boolean gardeNote) {
        for (int i = 0; i < this.listeAvis.size(); i++) {
            if (avis == listeAvis.get(i)) {
                return avis.supprimeAvisBDD(gardeNote);
            }
        }
        String ret = "Attention.";
        ret += " L'avis de " + avis.getNomClient();
        ret += " mentionnant '" + avis.getContenu();
        ret += "' datant du " + avis.getDate();
        ret += " noté " + avis.getNote() + "/5.0";
        ret += " n'est pas associé à l'article '" + this.getNom() + "'.";
        // System.out.println(ret);
        return ret;

    }

    public double calculNote() {
        if (this.listeAvis.size() == 0)
            return 2.5;
        else {
            double sommeNote = 0;
            for (int i = 0; i < this.listeAvis.size(); i++) {
                sommeNote += this.listeAvis.get(i).getNote();
            }
            return sommeNote / this.listeAvis.size();
        }
    }

    public String[] getInfoArticle() {
        String[] infos = new String[5];
        infos[0] = this.nom;
        infos[1] = "" + this.prix;
        infos[2] = "" + this.quantite;
        infos[3] = this.photo;
        infos[4] = this.description;
        return infos;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Double getPrix() {
        return this.prix;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return this.nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return this.photo;
    }

    public String getNomCategorie() {
        return this.categorie.getNom();
    }

    public int getNombreNoteClient() {
        return this.listeAvis.size();
    }

    public int getQuantite() {
        return this.quantite;
    }

    public ArrayList<Avis> getListeAvis() {
        return this.listeAvis;
    }
}
