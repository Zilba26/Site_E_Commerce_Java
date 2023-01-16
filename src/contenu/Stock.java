package src.contenu;

import java.util.ArrayList;

public class Stock {
    private ArrayList<Categorie> categories;

    public Stock() {
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

    public void ajouteCategorie(Categorie cat) {
        this.categories.add(cat);
    }

    public void retireArticle(Article article) {
        // TODO : refaire parce que c'est moche comme codage
        int indexK = -1;
        int indexI = -1;
        for (int k = 0; k < this.categories.size(); k++) {
            for (int i = 0; i < this.categories.get(k).getArticles().size(); i++) {
                if (this.categories.get(k).getArticles().get(i).equals(article)) {
                    indexI = i;
                    indexK = k;
                }
            }
        }
        if (indexK != -1 && indexI != -1)
            this.categories.get(indexK).getArticles().remove(indexI);
    }

}
