module com.maze {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    
    opens com.maze to javafx.fxml;
    exports com.maze;
}