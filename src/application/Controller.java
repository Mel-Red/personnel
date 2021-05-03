package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import personnel.GestionPersonnel;
import personnel.Ligue;

public class Controller {
	
	@FXML private Label erreur;

	@FXML private TextField mail;
	
	@FXML private PasswordField mdp;
	
	@FXML private Button buttonConfirmer;
    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
    	Stage stage;
        Parent root;
    	if(GestionPersonnel.getGestionPersonnel().getRoot().getNom().equals(mail.getText()) && GestionPersonnel.getGestionPersonnel().getRoot().getPassword().equals(mdp.getText()))
    	{
    		stage = (Stage) buttonConfirmer.getScene().getWindow();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("EspaceAdmin.fxml"));
			root = loader.load();
        	Scene scene = new Scene(root);
        	AdminController gestionLigue = loader.<AdminController>getController();
        	gestionLigue.setUtilisateur(GestionPersonnel.getGestionPersonnel().getRoot(), true);
        	gestionLigue.initializeTabLigue();
			//gestionLigue.estRoot(gestionPersonnel.getGestionPersonnel().getRoot());
        	stage.setTitle(GestionPersonnel.getGestionPersonnel().getRoot().getNom());
        	stage.setScene(scene);
    	}
    	else if((!mail.getText().toLowerCase().equals("root")))
    	{
    		/*for(Ligue ligue : gestionPersonnel.getGestionPersonnel().getLigues())
    		{
    			if(ligue.getAdministrateur().getMail().toLowerCase().equals(mail.getText().toLowerCase()) && ligue.getAdministrateur().getPassword().equals(mdp.getText()))
    			{
            		stage = (Stage) buttonConfirmer.getScene().getWindow();
            		FXMLLoader loader = new FXMLLoader(getClass().getResource("EspaceAdmin.fxml"));
    				root = loader.load();
            		Scene scene = new Scene(root);
            		AdminController gestionLigue = loader.<AdminController>getController();
    				//gestionLigue.estRoot(ligue.getAdministrateur());
            		stage.setTitle(ligue.getNom());
            		stage.setScene(scene);
    			}
    		}*/
    	}
    	else
    	{
    		erreur.setText("Mail et/ou mot de passe saisi inconnu.");
    	}
    	
    	/*actiontarget.setText("Sign in button pressed");*/
    }
    
    public TextField getMail() {
		return mail;
	}

	public void setMail(TextField mail) {
		this.mail = mail;
	}

	public PasswordField getMotDePasse() {
		return mdp;
	}

	public void setMotDePasse(PasswordField mdp) {
		this.mdp = mdp;
	}	
}

