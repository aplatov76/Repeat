/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class ControllerURL extends Application {

    public void openBrowser(ActionEvent actionEvent, String uri) throws Exception {

        getHostServices().showDocument(uri);
        //getHostServices().getWebContext().setSlot(0, "");
    }
    public void openBrowser(ActionEvent actionEvent, String uri, int id_check) throws Exception {
        getHostServices().showDocument(uri+"?id="+id_check);
        //getHostServices().resolveURI("id", "AAA");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}