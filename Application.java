public class Application{

    private BDD bdd;
    private Admin adminConnecte;
    private Stock stock;

    public Application() {
        this.stock = new Stock();
    }

    public BDD getBDD() {
        return this.bdd;
    }

    public void connexionAdmin(String email, String mdp) {
        //TO DO
        //Si infos en paramètre correspondent à un admin dans la BDD
          //this.adminConnecte = new Admin( -Nom correspondant aux infos paramètres- )
          //Changement de fenètre ayant accès au Stock
        //Sinon
          //Message d'erreur, pas de changement de fenètre
    }

    public Admin getAdminConnecte() {
        return this.adminConnecte;
    }

    public boolean adminEstConnecte() {
        return !(adminConnecte == null);
    }

    public void creerArticle (String nom, double prix, int quantite, String photo, String description, String nomCategorie) {
        Article article = new Article(nom, prix, quantite, photo, description, nomCategorie);
        String[] infosNewArticle = article.getInfoArticle();
        boolean articleAlreadyExist = false;

        for (int i=0 ; i<this.stock.getArrayCategorie().size() ; i++) {
            for (int k=0 ; k<this.stock.getArrayCategorie().get(i).getArticles().size() ; k++) {
                if (this.stock.getArrayCategorie().get(i).getArticles().get(k).getInfoArticle() == infosNewArticle) {
                    articleAlreadyExist = true;
                    //TO DO //Message prévenant de l'échec de l'opération
                }
            }
        }

        if (!articleAlreadyExist) {
            boolean categorieEstTrouve = false;
            for (int i=0 ; i<this.stock.getArrayCategorie().size() ; i++) {
                if (this.stock.getArrayCategorie().get(i).getNom() == nomCategorie) {
                    this.stock.getArrayCategorie().get(i).getArticles().add(article);
                    categorieEstTrouve = true;
                    //TO DO //Message validant l'opération
                }
            }

            if(!categorieEstTrouve) {
                //TO DO //Message prévenant de l'échec de l'opération | Variante
            }
        }
    }

    public void supprimerArticle(Article article) {
        boolean estTrouve = false;
        for (int i=0 ; i<this.stock.getArrayCategorie().size() ; i++) {
            for (int k=0 ; k<this.stock.getArrayCategorie().get(i).getArticles().size() ; k++) {
                if (this.stock.getArrayCategorie().get(i).getArticles().get(k) == article) {
                    this.stock.getArrayCategorie().get(i).getArticles().remove(k);
                    estTrouve = true;
                }
            }
        }

        if (estTrouve) {
            //TO DO //Message validant l'opération
        }
        else {
            //TO DO //Message prévenant de l'échec de l'opération
        }
    }

    public void modifierArticle(Article article) {
        //TO DO //Affichage qui demande ce qu'il veut changer, par exemple le nom
        article.setNom("Test");
    }

    public boolean supprimerAvisClient(Article article, Avis avis, boolean gardeNote) {
        article.supprimerAvis(avis, gardeNote);
        return gardeNote;
    }

    public void creerAdmin(String nom, String email, String mdp) {
        //TO DO //Ajoutez nouvel Admin sur BDD
    }

    public void creerCategorie(String nom) {
        boolean categorieAlreadyExist = false;
        for (int i=0 ; i<this.stock.getArrayCategorie().size() ; i++) {
            if (this.stock.getArrayCategorie().get(i).getNom() == nom) {
                categorieAlreadyExist = true;
            }
        }
        if (!categorieAlreadyExist) {
            this.stock.creerCategorie(nom);
            //TO DO //Message validant l'opération
        }
        else {
            //TO DO //Message prévenant de l'échec de l'opération
        }
    }

}