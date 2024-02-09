module pidev.javafx{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires transitive javafx.base;
    requires transitive javafx.graphics;
    requires transitive java.desktop;

    opens pidev.javafx to javafx.fxml;
    exports pidev.javafx;
    exports pidev.javafx.Controller;
    opens pidev.javafx.Controller to javafx.fxml;
    opens pidev.javafx.Controller.MarketPlace to javafx.fxml;
    exports pidev.javafx.Model.MarketPlace;
    opens pidev.javafx.Model.MarketPlace to javafx.fxml;
    exports pidev.javafx.Controller.MarketPlace;
    opens pidev.javafx.Controller.UserMarketDashbord to javafx.fxml;
    exports pidev.javafx.Controller.Crud;
    opens pidev.javafx.Controller.Crud to javafx.fxml;
    exports pidev.javafx.Controller.Tools;
    opens pidev.javafx.Controller.Tools to javafx.fxml;
}

