package src.ahmedjordypiia.Controle;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import src.ahmedjordypiia.Modele.Node;
import src.ahmedjordypiia.Vue.TreeOfLifeVisual;

import java.io.IOException;

import static javafx.scene.paint.Color.rgb;

public class Recherche {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Rectangle espece;

    @FXML
    private Button especeDetails;

    @FXML
    private StackPane leftBar;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;
    @FXML
    private Text speciesDescription;

    @FXML
    private BorderPane speciesMenu;

    @FXML
    private Text speciesName;

    @FXML
    private Text speciesNameMenu;

    @FXML
    private Hyperlink tolorgLink;

    @FXML
    private HBox topBar;

    @FXML
    private Pane treePane;

    @FXML
    private Button zoomInButton;

    @FXML
    private Button zoomOutButton;

    @FXML
    private ImageView speciesImage;

    private Node currentNode;

    private TreeOfLifeVisual tree = new TreeOfLifeVisual();
    private Application application;

    public Recherche() throws IOException {
    }

    public void initialize() throws IOException {
        Group treeGroup = tree.getTreeGroup();
        treePane.getChildren().add(treeGroup);
        speciesMenu.setVisible(false);

        tolorgLink = new Hyperlink();
        tolorgLink.setOnMouseClicked(event -> {
            if (currentNode != null) {
                // Ouvrir le lien dans le navigateur par défaut de l'utilisateur
                HostServices hostServices = (HostServices) this.getApplication().getHostServices();
                hostServices.showDocument(currentNode.getTolorgLink());
            }
        });
        setCurrentNode(tree.getTree().searchNode("Life on Earth"));
    }

    @FXML
    public void search(MouseEvent event) throws IOException {
        String name = searchField.getText();
        Node node = tree.getTree().searchNode(name);
        if (node != null) {
            setCurrentNode(node);
        }else {
            searchField.setPromptText("Espèce non trouvée");
            searchField.setStyle("-fx-prompt-text-fill: " + rgb(255, 0, 0));
            searchField.setText("");
            searchField.setStyle("-fx-background-color:" + rgb(0,0,0,255));
        }
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
    public void openLink(ActionEvent event) {
        if (currentNode != null) {
            // Ouvrir le lien dans le navigateur par défaut de l'utilisateur
            HostServices hostServices = (HostServices) this.getApplication().getHostServices();
            hostServices.showDocument(currentNode.getTolorgLink());
        }
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public void setCurrentNode(Node node) {
        this.currentNode = node;
        this.speciesName.setText(this.currentNode.getName());
        this.speciesNameMenu.setText(this.currentNode.getName());
        this.speciesDescription.setText(this.currentNode.toStringNodeInfo());
        this.tolorgLink.setText(this.currentNode.getTolorgLink());
        this.tolorgLink.setVisited(false);
    }

    public TreeOfLifeVisual getTree() {
        return tree;
    }
}
