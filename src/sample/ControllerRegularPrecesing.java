package sample;

import javafx.event.EventHandler;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerRegularPrecesing implements Initializable {
    public Label lb_name_model;
    public TextField ti_w10, ti_w20, ti_w30;
    public Button btn_view_graphs_omega;
    public Button btn_view_graphs_lambda;
    public Button btn_graphs_lambda1;
    public TextField ti_eps;
    public TextField ti_period_t;
    public TextField ti_det_t;
    public Button btn_calculate;

    private double w10, w20, w30, eps;
    private double countSteps;
    private double t = 0, periodT;
    private double a, psi, k, phi, r, alpha1, alpha2;

    private PlotGraphs plotGraphs;

    private List<Double> listOmega1, listOmega2, listOmega3;
    private List<Double> listLambda1, listLambda2, listLambda3, listLambda4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        listLambda1 = new ArrayList<>((int) countSteps);
        listLambda2 = new ArrayList<>((int) countSteps);
        listLambda3 = new ArrayList<>((int) countSteps);
        listLambda4 = new ArrayList<>((int) countSteps);


        listOmega1 = new ArrayList<>((int) countSteps);
        listOmega2 = new ArrayList<>((int) countSteps);
        listOmega3 = new ArrayList<>((int) countSteps);

        plotGraphs = new PlotGraphs();

        btn_calculate.setOnMousePressed(event -> {
            getDataFromFields();
            findOmegaLambda();
        });

        btn_view_graphs_omega.setOnMousePressed(event -> {
            if (!listOmega1.isEmpty() && !listOmega2.isEmpty() && !listOmega3.isEmpty()){
                plotGraphs.createViewGraphsOmega(listOmega1, listOmega2, listOmega3);
            }
        });

        btn_view_graphs_lambda.setOnMousePressed(event -> {
            if (!listLambda1.isEmpty() && !listLambda2.isEmpty() && !listLambda3.isEmpty() && !listLambda4.isEmpty()){
                plotGraphs.createViewGraphsLambda(listLambda1, listLambda2, listLambda3, listLambda4);
            }
        });

        btn_graphs_lambda1.setOnMousePressed(event -> {
            if (!listLambda1.isEmpty() && !listLambda2.isEmpty()){
                plotGraphs.createGraphsLambda(listLambda2, listLambda1);
            }
        });
    }

    private void getDataFromFields(){

        w10 = Double.parseDouble(ti_w10.getCharacters().toString());
        w20 = Double.parseDouble(ti_w20.getCharacters().toString());
        w30 = Double.parseDouble(ti_w30.getCharacters().toString());

        t = Double.parseDouble(ti_det_t.getCharacters().toString());
        periodT = Double.parseDouble(ti_period_t.getCharacters().toString());

        eps = Double.parseDouble(ti_eps.getCharacters().toString());

        countSteps = periodT / t;
    }


    private void findOmegaLambda(){

        listOmega1.clear();
        listOmega2.clear();
        listOmega3.clear();

        listLambda1.clear();
        listLambda2.clear();
        listLambda3.clear();
        listLambda4.clear();

        t = 0;
        for (int i = 0; i < countSteps; i++) {
            a = Math.sqrt(Math.pow(w10, 2) + Math.pow(w20, 2));
            k = (1 - eps) * w30;
            psi = - Math.atan(w20/w10);
            phi = Math.sqrt(Math.pow(a,2) + Math.pow(psi, 2) * Math.pow(w30, 2));
            r = psi * w30 / phi;
            alpha1 = (k * t) / 2;
            alpha2 = (phi * t) / 2;

            double omega1 = a * Math.cos(k * t + psi);
            double omega2 = -a * Math.sin(k * t + psi);
            double omega3 = w30;

            double lambda1 = Math.cos(alpha1) * Math.cos(alpha2) - r * Math.sin(alpha1) * Math.sin(alpha2);
            double lambda2 = a * Math.sin(alpha2) * Math.cos(alpha1 + psi) / phi;
            double lambda3 = - a * Math.sin(alpha2) * Math.sin(alpha1 + psi) / phi;
            double lambda4 = Math.sin(alpha1) * Math.cos(alpha2) + r * Math.cos(alpha1) * Math.sin(alpha2);

            listOmega1.add(omega1);
            listOmega2.add(omega2);
            listOmega3.add(omega3);

            listLambda1.add(lambda1);
            listLambda2.add(lambda2);
            listLambda3.add(lambda3);
            listLambda4.add(lambda4);

            t += 0.1;
        }

        for (Double aListLambda1 : listLambda1) {
            System.out.println(String.format("%.4f",aListLambda1));
        }
        System.out.println("----------------------");
        for (Double aListLambda1 : listLambda2) {
            System.out.println(String.format("%.4f",aListLambda1));
        }

    }


}
