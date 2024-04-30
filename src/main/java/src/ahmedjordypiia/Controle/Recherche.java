package src.ahmedjordypiia.Controle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import src.ahmedjordypiia.Modele.Tree;
import src.ahmedjordypiia.Vue.TreeOfLifeVisual;

public class Recherche {

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
    TreeOfLifeVisual tree;

    public void setTree(TreeOfLifeVisual tree) {
        this.tree = tree;
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

}
