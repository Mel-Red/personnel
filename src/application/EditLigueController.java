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

public class EditLigueController {
	@FXML
	private TextField nomLigue;
	;

	private ObservableList<Ligue> appMainObservableList;
	private Ligue editLigue;

    @FXML
    protected void handleEditLigueAction(ActionEvent event) {
    	String nom = nomLigue.getText();
    	try {
			this.editLigue.setNom(nom);
		} catch (SauvegardeImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	int idx = this.appMainObservableList.indexOf(this.editLigue);
    	if (idx > -1) this.appMainObservableList.set(idx, this.editLigue);
    	handleCancelLigueAction(event);
    }
    
    @FXML
    protected void handleCancelLigueAction(ActionEvent event) {
    	 Node  source = (Node)  event.getSource(); 
         Stage stage  = (Stage) source.getScene().getWindow();
         stage.close();
    }
    
    public void setAppMainObservableList(Ligue ligue, ObservableList<Ligue> tvObservableList) {
    	this.nomLigue.setText(ligue.getNom());
    	this.editLigue = ligue;
        this.appMainObservableList = tvObservableList;
        
    }
}
