import java.util.ArrayList;

public class Stock {
    private ArrayList<Categorie> categories;

    Stock() {
        this.categories = new ArrayList<Categorie>();
        // TODO : init categories from db
    }

    public ArrayList<Categorie> getArrayCategorie() {
        return this.categories;
    }

    public void creerCategorie(String nom) {
        Categorie categorie = new Categorie(nom);
        this.getArrayCategorie().add(categorie);
    }


}
