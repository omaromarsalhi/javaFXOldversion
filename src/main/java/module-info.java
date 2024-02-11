module pidev.javafx{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens pidev.javafx to javafx.fxml;
    exports pidev.javafx;
    exports pidev.javafx.Controller;
    opens pidev.javafx.Controller to javafx.fxml;
    opens pidev.javafx.Controller.MarketPlace to javafx.fxml;
    exports pidev.javafx.entities.Marketplace;
    opens pidev.javafx.entities.Marketplace to javafx.fxml;
    exports pidev.javafx.Controller.Transport;
    opens pidev.javafx.Controller.Transport to javafx.fxml;
    opens pidev.javafx.Services to javafx.base;
    opens pidev.javafx.entities.Transport to javafx.base;
    exports pidev.javafx.tests;
    opens pidev.javafx.tests to javafx.fxml;
}

