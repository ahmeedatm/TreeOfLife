package src.ahmedjordypiia.Controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import src.ahmedjordypiia.Vue.TreeOfLifeVisual;

public class Recherche {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button closeButton;

    @FXML
    private Rectangle espece;

    @FXML
    private Button especeDetails;

    @FXML
    private StackPane leftBar;

    @FXML
    private Button searchButton;

    //@FXML
    //private AutoCompleteTextField<?> searchField;

    @FXML
    private ScrollPane speciesMenu;

    @FXML
    private Text speciesName;

    @FXML
    private Text speciesNameMenu;

    @FXML
    private Text speciesDescription;

    @FXML
    private HBox topBar;

    @FXML
    private Pane treePane;

    @FXML
    private Button zoomInButton;

    @FXML
    private Button zoomOutButton;

    private TreeOfLifeVisual tree;

    public void initialize() {
        TreeOfLifeVisual tree = new TreeOfLifeVisual();
        Group treeGroup = tree.getTreeGroup();
        treePane.getChildren().add(treeGroup);
        speciesMenu.setVisible(false);
        speciesNameMenu.setText(speciesName.getText());
    }

    @FXML
    public void search(MouseEvent event) {
        //String name = searchField.getText();
        //tree.searchNode(name);
        //speciesName.setText(name);
    }

    @FXML
    void openEspecePanel(ActionEvent event) {
        speciesNameMenu.setText(speciesName.getText());
        speciesMenu.setVisible(true);
    }

    @FXML
    void closeEspecePanel(ActionEvent event) {
        speciesNameMenu.setText("");
        speciesMenu.setVisible(false);
    }

    @FXML
    public void zoomIn(ActionEvent event) {
        tree.zoomIn();
    }

    @FXML
    public void zoomOut(ActionEvent event) {
        tree.zoomOut();
    }

    @FXML
    void setEnteredTopBar(MouseEvent event) {
        topBar.setOpacity(1);
    }

    @FXML
    void setExitedTopBar(MouseEvent event) {
        topBar.setOpacity(0.5);
    }

    @FXML
    void setEnteredEspece(MouseEvent event) {
        espece.setOpacity(1);
    }

    @FXML
    void setExitedEspece(MouseEvent event) {
        espece.setOpacity(0.5);
    }

}
