public class Application{

    private BDD bdd;
    private Admin adminConnecte;

    // Getters
    public BDD getBDD(){
        return this.bdd;
    }
    public Admin getAdminConnecte(){
        return this.adminConnecte;
    }
    public boolean adminEstConnecte(){
        return !(adminConnecte == null);
    }

}