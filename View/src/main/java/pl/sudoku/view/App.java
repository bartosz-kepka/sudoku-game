package pl.sudoku.view;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App.
 */
public class App extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        Locale locale = new Locale("pl, PL");
        ResourceBundle bundle = ResourceBundle.getBundle("bundles/menu", locale);

        FXMLLoader loader = new FXMLLoader(App.class.getResource("menu.fxml"), bundle);
        MenuController primaryController = new MenuController();
        loader.setController(primaryController);
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();
    }

    static void setScene(Parent root) {
        primaryStage.setScene(new Scene(root));
        primaryStage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch();
    }

}