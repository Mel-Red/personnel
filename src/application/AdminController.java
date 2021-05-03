package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import personnel.Employe;
import personnel.GestionPersonnel;
import personnel.ImpossibleDeChangerDate;
import personnel.Ligue;
import personnel.SauvegardeImpossible;

public class AdminController implements Initializable {
	
	private final ObservableList<Ligue> changesTabLigue = FXCollections.observableArrayList();  
	private final ObservableList<Employe> changesTabEmploye = FXCollections.observableArrayList();
	private Ligue selectedLigue;
	private Employe utilisateur;
	private boolean isRoot;
	
	public void setUtilisateur(Employe employe, boolean isRoot) {
		this.utilisateur = employe;
		this.isRoot = isRoot;
	}
	
	@FXML
    private Label titre;
    
    @FXML
    private TableView<Ligue> tabLigue;
    
    @FXML
    private TableView<Employe> tabEmploye;
    
    @FXML
    private TableColumn<Ligue, String> colNomLigue;
    
    @FXML
    private TableColumn<Ligue, Void> colActions;
    
    @FXML
    private TableColumn<Employe, String> colNomEmploye;
    
    @FXML
    private TableColumn<Employe, String> colPrenomEmploye;
    
    @FXML
    private TableColumn<Employe, String> colEmailEmploye;
    
    @FXML
    private Button ajouterLigue;
    
    @FXML
    private Button ajouterEmploye;
    
    @FXML
    protected void ajouterLigueAction(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addLigue.fxml"));
        Parent parent = fxmlLoader.load();
        AddLigueController dialogController = fxmlLoader.<AddLigueController>getController();
        dialogController.setAppMainObservableList(changesTabLigue);

        Scene scene = new Scene(parent, 300, 200);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Ajouter une ligue");
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    @FXML
    protected void ajouterEmployeAction(ActionEvent event) throws IOException {
    	final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("Ceci n'est pas implémenté!"));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }
    
    @FXML
    private void editTabLigue(Ligue ligue) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditLigue.fxml"));
        Parent parent = fxmlLoader.load();
        EditLigueController dialogController = fxmlLoader.<EditLigueController>getController();
        dialogController.setAppMainObservableList(ligue, changesTabLigue);

        Scene scene = new Scene(parent, 300, 200);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Modifier une ligue");
        stage.setScene(scene);
        stage.showAndWait();
    }
    
    public void initializeTabLigue() throws IOException {
    	if (isRoot) {
    		for(Ligue ligue : GestionPersonnel.getGestionPersonnel().getLigues())
			{
				changesTabLigue.add(ligue);
			}
    	}
    	else
    	{
    		changesTabLigue.add(utilisateur.getLigue());
    	}
    	
    	colNomLigue.setCellValueFactory(new PropertyValueFactory<>("nom"));
    	Callback<TableColumn<Ligue, Void>, TableCell<Ligue, Void>> cellFactory = new Callback<TableColumn<Ligue, Void>, TableCell<Ligue, Void>>(){

			@Override
			public TableCell<Ligue, Void> call(TableColumn<Ligue, Void> arg0) {
				final TableCell<Ligue, Void> cell = new TableCell<Ligue, Void>() {
                    final Button btn2 = new Button("Modifier Ligue");
                    final Button btn3 = new Button("Supprimer Ligue");
                    final HBox hbox = new HBox(20, btn2, btn3);
                    
                    @Override
                    public void updateItem(Void item, boolean empty) {
                    	super.updateItem(item, empty);
                    	if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn2.setOnAction(event -> {
                                Ligue ligue = getTableView().getItems().get(getIndex());
                                System.out.println("Edit Ligue: " + ligue.getNom());
                                try {
									editTabLigue(ligue);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
                            });
                            btn3.setOnAction(event -> {
                                Ligue ligue = getTableView().getItems().get(getIndex());
                                boolean removed = false;
								try {
									removed = GestionPersonnel.getGestionPersonnel().remove(ligue);
								} catch (SauvegardeImpossible e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
                                if(removed) changesTabLigue.remove(ligue);
                                System.out.println("Delete Ligue: " + ligue.getNom() + " " + removed);
                            });
                            hbox.setSpacing(50);
                            setGraphic(hbox);
                            setText(null);
                        }
                    }
				};
				return cell;
			}
    		
    	};
		colActions.setCellFactory(cellFactory);
    	
    	tabLigue.setItems(changesTabLigue);
	}
    
    public void initializeTabEmploye(Ligue ligue) throws IOException {
    	changesTabEmploye.clear();
    	
    	for(Employe e : ligue.getEmployes()) {
    		changesTabEmploye.add(e);
    	}
    	ajouterEmploye.setVisible(true);
    	colNomEmploye.setCellValueFactory(new PropertyValueFactory<>("nom"));
    	colPrenomEmploye.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    	colEmailEmploye.setCellValueFactory(new PropertyValueFactory<>("mail"));
    	tabEmploye.setItems(changesTabEmploye);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ajouterEmploye.setVisible(false);
		
		// set selected event on table cell
		tabLigue.getSelectionModel().setCellSelectionEnabled(true);
		ObservableList<?> selectedCells = tabLigue.getSelectionModel().getSelectedCells();
		
		AdminController ref = this;
		selectedCells.addListener(new ListChangeListener<Object>() {
			@Override
			public void onChanged(Change<?> arg0) {
				// TODO Auto-generated method stub
				TablePosition<?, ?> tablePosition = (TablePosition<?, ?>) selectedCells.get(0);
		        Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
		        ref.selectedLigue = changesTabLigue.get(tablePosition.getRow());
		        try {
					ref.initializeTabEmploye(ref.selectedLigue);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        System.out.println("Selected Value: " + selectedLigue.getNom());
			}
		    
		});
	}
}
