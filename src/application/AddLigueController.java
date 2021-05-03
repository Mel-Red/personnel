package application;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import personnel.GestionPersonnel;
import personnel.Ligue;
import personnel.SauvegardeImpossible;

public class AddLigueController {
	@FXML
	private TextField nomLigue;
	
	private ObservableList<Ligue> appMainObservableList;

    @FXML
    protected void handleAddLigueAction(ActionEvent event) {
    	String nom = nomLigue.getText();
    	Ligue newLigue = null;
		try {
			newLigue = GestionPersonnel.getGestionPersonnel().addLigue(nom);
		} catch (SauvegardeImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("New Ligue: " + newLigue.getNom());
    	if(newLigue != null) appMainObservableList.add(newLigue);
    	handleCancelLigueAction(event);
    }
    
    @FXML
    protected void handleCancelLigueAction(ActionEvent event) {
    	 Node  source = (Node)  event.getSource(); 
         Stage stage  = (Stage) source.getScene().getWindow();
         stage.close();
    }
    
    public void setAppMainObservableList(ObservableList<Ligue> tvObservableList) {
        this.appMainObservableList = tvObservableList;
        
    }
}
