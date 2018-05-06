package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.omg.CORBA.MARSHAL;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ControllerInputParameterThirdMult implements Initializable {

    public Label lb_name_model;
    public TextField ti_k1, ti_k2, ti_k3;
    public Button btn_view_graphs_omega;
    public Button btn_view_graphs_lambda;
    public Button btn_graphs_lambda1;

    private double k1Parameter, k2Parameter, k3Parameter;
    private List<Double> listOmega1, listOmega2, listOmega3;
    private List<Double> listLambda1, listLambda2, listLambda3, listLambda4;
    private double countSteps;
    private double t = 0.1;
    private PlotGraphs plotGraphs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        //countSteps = periodT / t;

        plotGraphs = new PlotGraphs();

        listLambda1 = new ArrayList<>((int) countSteps);
        listLambda2 = new ArrayList<>((int) countSteps);
        listLambda3 = new ArrayList<Double>((int) countSteps);
        listLambda4 = new ArrayList<Double>((int) countSteps);


        listOmega1 = new ArrayList<Double>((int) countSteps);
        listOmega2 = new ArrayList<Double>((int) countSteps);
        listOmega3 = new ArrayList<Double>((int) countSteps);


        btn_view_graphs_omega.setOnMousePressed(event -> {

            k1Parameter = Double.parseDouble(ti_k1.getCharacters().toString());
            k2Parameter = Double.parseDouble(ti_k2.getCharacters().toString());
            k3Parameter = Double.parseDouble(ti_k3.getCharacters().toString());

            findOmega(k1Parameter, k2Parameter, k3Parameter);
        });

        btn_view_graphs_lambda.setOnMousePressed(event -> {

            k1Parameter = Double.parseDouble(ti_k1.getCharacters().toString());
            k2Parameter = Double.parseDouble(ti_k2.getCharacters().toString());
            k3Parameter = Double.parseDouble(ti_k3.getCharacters().toString());

            findLambda(k1Parameter, k2Parameter, k3Parameter);
        });

        btn_graphs_lambda1.setOnMousePressed(event -> {
            if (listLambda1.size() != 0) {
                plotGraphs.createGraphsLambda(listLambda1, listLambda3);
            }
        });

    }

    private void findOmega(double k1, double k2, double k3) {

        listOmega1.clear();
        listOmega2.clear();
        listOmega3.clear();

        t = 0.1;

        for (int i = 0; i < countSteps; i++) {
            double omega1 = 0.25 * k1 * Math.sin((k1 + 2 * k2 + 2 * k3) * t) + 0.25 * k1 * Math.sin((-k1 + 2 * k2 + 2 * k3) * t) +
                    0.25 * k1 * Math.sin((k1 - 2 * k2 + 2 * k3) * t) + 0.25 * k1 * Math.sin((-k1 - 2 * k2 + 2 * k3) * t) +
                    0.5 * k1 * Math.cos((k1 - 2 * k2) * t) - 0.5 * k1 * Math.cos((k1 + 2 * k2) * t) + k2 * Math.cos((k1 + 2 * k3) * t) +
                    k2 * Math.cos((k1 - 2 * k3) * t) - 2 * k3 * Math.sin(k3 * t);

            double omega2 = 0.25 * k1 * Math.cos((k1 + 2 * k2 - 2 * k3) * t) - 0.25 * k1 * Math.cos((k1 + 2 * k2 + 2 * k3) * t) +
                    0.25 * k1 * Math.cos((k1 - 2 * k2 - 2 * k3) * t) - 0.25 * k1 * Math.cos((k1 - 2 * k2 + 2 * k3) * t) -
                    0.5 * k1 * Math.sin((2 * k2 + k1) * t) - 0.5 * k1 * Math.sin((2 * k2 - k1) * t) + k2 * Math.sin((k1 + 2 * k3) * t) +
                    k2 * Math.sin((k1 - 2 * k3) * t) - 2 * k3 * Math.cos(k3 * t);

            double omega3 = k1 + 0.5 * k1 * Math.cos((2 * k2 + 2 * k3) * t) + 0.5 * k1 * Math.cos((2 * k2 - 2 * k3) * t) - 2 * k2 * Math.sin((2 * k3) * t);

            t += 0.1;

            listOmega1.add(omega1);
            listOmega2.add(omega2);
            listOmega3.add(omega3);
        }

        plotGraphs.createViewGraphsOmega(listOmega1, listOmega2, listOmega3);
    }

    private void findLambda(double k1, double k2, double k3) {

        listLambda1.clear();
        listLambda2.clear();
        listLambda3.clear();
        listLambda4.clear();

        t = 0;

        for (int i = 0; i < countSteps; i++) {
            double lambda1 = Math.cos(k1 * t) * Math.cos(k2 * t) * Math.cos(k3 * t) + Math.sin(k1 * t) * Math.sin(k2 * t) *
                    Math.sin(k3 * t);

            double lambda2 = -Math.sin(k2 * t) * Math.cos(k3 * t);

            double lambda3 = Math.cos(k2 * t) * Math.sin(k3 * t);

            double lambda4 = Math.sin(k1 * t) * Math.cos(k2 * t) * Math.cos(k3 * t) - Math.cos(k1 * t) * Math.sin(k2 * t) *
                    Math.sin(k3 * t);

            t += 0.1;

            listLambda1.add(lambda1);
            listLambda2.add(lambda2);
            listLambda3.add(lambda3);
            listLambda4.add(lambda4);
        }

        for (Double aListLambda1 : listLambda1) {
            System.out.println(aListLambda1);
        }
        System.out.println("----------------------");
        for (Double aListLambda1 : listLambda3) {
            System.out.println(aListLambda1);
        }
        plotGraphs.createViewGraphsLambda(listLambda1, listLambda2, listLambda3, listLambda4);
    }




}
