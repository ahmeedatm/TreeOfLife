module src.ahmedjordypiia {
    requires javafx.controls;
    requires javafx.fxml;


    opens src.ahmedjordypiia to javafx.fxml;
    exports src.ahmedjordypiia;
}