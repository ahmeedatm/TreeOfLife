module src.ahmedjordypiia {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports src.ahmedjordypiia.Vue to javafx.graphics;


    opens src.ahmedjordypiia to javafx.fxml;
    exports src.ahmedjordypiia;
}