package it.unicam.cs.pa.jbudget104953.main;

import it.unicam.cs.pa.jbudget104953.FXController.JavaFXView;
import it.unicam.cs.pa.jbudget104953.controller.Control;
import javafx.application.Application;

public class index {

    public static void main(String[] args) {
        if (args.length != 0)
            new Control().start();
        else {
            Application.launch(JavaFXView.class, args);
        }
    }
}