package src.ahmedjordypiia.Controle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import src.ahmedjordypiia.Modele.Tree;

public class Recherche {
    @FXML
    public TextField searchField;
    @FXML
    public Button searchButton;
    @FXML
    public Text speciesName;
    Tree tree;

    @FXML
    public void search(MouseEvent event) {
        String name = searchField.getText();
        //tree.searchNode(name);
        speciesName.setText(name);
    }

}
