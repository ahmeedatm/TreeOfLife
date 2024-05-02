package src.ahmedjordypiia.Controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import src.ahmedjordypiia.Modele.Tree;
import src.ahmedjordypiia.Vue.TreeOfLifeVisual;

//package src.ahmedjordypiia.Controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import src.ahmedjordypiia.Modele.Tree;
import src.ahmedjordypiia.Vue.TreeOfLifeVisual;

public class Control {

    @FXML
    public Button zoomInButton;
    @FXML
    public Button zoomOutButton;
    @FXML
    public TextField searchField;
    @FXML
    public Button searchButton;
    @FXML
    public Text speciesName;
    @FXML
    private HBox topBar;
    @FXML
    private Rectangle espece;
    @FXML
    private Pane treePane;
    private TreeOfLifeVisual tree;

    public void initialize() {
        TreeOfLifeVisual tree = new TreeOfLifeVisual();
        Group treeGroup = tree.getTreeGroup();
        treePane.getChildren().add(treeGroup);
    }

    @FXML
    public void search(MouseEvent event) {
        String name = searchField.getText();
        //tree.searchNode(name);
        speciesName.setText(name);
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
