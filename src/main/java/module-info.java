module src.ahmedjordypiia {
    requires javafx.controls;
    requires javafx.fxml;


    opens src.ahmedjordypiia to javafx.fxml;
    exports src.ahmedjordypiia.Controle;
    exports src.ahmedjordypiia.Main;
    opens src.ahmedjordypiia.Main to javafx.fxml;
    opens src.ahmedjordypiia.Controle to javafx.fxml;
}