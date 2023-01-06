import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;

public class BDD {
    private String BDD = "votreBaseDeDonnée";
    private String url = "jdbc:mysql://localhost:3306/" + BDD;
    private String user = "root";
    private String passwd = "root";
    private Connection bdd;

    public BDD(){
        this.initConnexionBDD();
    }

    public boolean initConnexionBDD(){
        boolean connexionReussie = false;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.bdd = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connection à " + this.BDD + " réussie !");
            connexionReussie = true;
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Erreur de connexion à la BDD");
            System.exit(0);
        }
        return connexionReussie;
    }
    
}
