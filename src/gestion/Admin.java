package src.gestion;
public class Admin {
    private String nom;
    private String email;
    private String mdp;

    public Admin(String nom, String email, String mdp){
        this.nom = nom;
        this.email = email;
        this.mdp = mdp;
    }

    public String getNom(){
        return nom;
    }

    public String getEmail() {
        return this.email;
    }

    public String getMdp() {
        return this.mdp;
    }
}
