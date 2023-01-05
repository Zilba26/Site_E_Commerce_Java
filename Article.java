import java.util.ArrayList;

public class Article {

    private String nom;
    private int quantite;
    private int nombreNoteClient;
    private ArrayList<Avis> listeAvis;
    private String photo;
    private double prix;
    private String description;
    private String nomCategorie;

    public Article (String nom, double prix, int quantite, String photo, String description, String nomCategorie) {
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.photo = photo;
        this.description = description;
        this.nomCategorie = nomCategorie;
        this.listeAvis = new ArrayList<Avis>();
    }

    public void supprimerArticleBDD() {
        //Supprimer article dans Base De Données
    }

    public void ajouterQuantite(int quantite) {
        if (quantite > 0)
            this.quantite+=quantite;
    }

    public void retirerQuantite(int quantite) {
        if (quantite > 0 && this.quantite - quantite >= 0)
            this.quantite-=quantite;
    }

    public void supprimerAvis(Avis avis, boolean gardeNote) {
        for (int i=0 ; i<this.listeAvis.size() ; i++) {
            if (avis == listeAvis.get(i)) {
                avis.supprimeAvisBDD(gardeNote);
            }
        }
    }

    public double calculNote() {
        if (this.listeAvis.size() == 0)
            return 2.5;
        else {
            double sommeNote = 0;
            for (int i=0 ; i<this.listeAvis.size() ; i++) {
                sommeNote += this.listeAvis.get(i).getNote();
            }
            return sommeNote / this.listeAvis.size();
        }
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

    public void setNomCategorie(String categorie) {
        this.nomCategorie = categorie;
    }
    public String getNomCategorie() {
        return this.nomCategorie;
    }
}
