package it.unicam.cs.pa.jbudget104953.main;

import it.unicam.cs.pa.jbudget104953.controller.Controller;

public class index {

    public static void main(String[] args) {
        if (args.length == 0)
            new Controller().start();

    }
}