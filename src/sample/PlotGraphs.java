package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.List;

public class PlotGraphs {

    public void createViewGraphsLambda(List<Double> listLambda1, List<Double> listLambda2, List<Double> listLambda3, List<Double> listLambda4) {

        Stage stage = new Stage();
        stage.setTitle("Введите параметры");

        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();

        LineChart<Number, Number> numberLineChart = new LineChart<Number, Number>(x, y);
        numberLineChart.setCreateSymbols(false);

        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
        XYChart.Series series4 = new XYChart.Series();

        series4.setName("Lambda4(t)");
        series3.setName("Lambda3(t)");
        series2.setName("Lambda2(t)");
        series1.setName("Lambda1(t)");

        ObservableList<XYChart.Data> data1 = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> data2 = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> data3 = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> data4 = FXCollections.observableArrayList();

        for (int i = 0; i < listLambda1.size(); i++) {

            data1.add(new XYChart.Data(i, listLambda1.get(i)));
            data2.add(new XYChart.Data(i, listLambda2.get(i)));
            data3.add(new XYChart.Data(i, listLambda3.get(i)));
            data4.add(new XYChart.Data(i, listLambda4.get(i)));

        }

        series1.setData(data1);
        series2.setData(data2);
        series3.setData(data3);
        series4.setData(data4);


        Scene scene = new Scene(numberLineChart, 600, 600);
        numberLineChart.getData().add(series1);
        numberLineChart.getData().add(series2);
        numberLineChart.getData().add(series3);
        numberLineChart.getData().add(series4);
        scene.getStylesheets().add("sample/Chart.css");
        stage.setScene(scene);

        stage.show();

    }

    public void createViewGraphsOmega(List<Double> listOmega1, List<Double> listOmega2, List<Double> listOmega3) {

        Stage stage = new Stage();

        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();

        LineChart<Number, Number> numberLineChart = new LineChart<Number, Number>(x, y);
        numberLineChart.setTitle("Series");
        numberLineChart.setCreateSymbols(false);

        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();

        series3.setName("Omega3(t)");
        series2.setName("Omega2(t)");
        series1.setName("Omega1(t)");

        ObservableList<XYChart.Data> data1 = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> data2 = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> data3 = FXCollections.observableArrayList();

        for (int i = 0; i < listOmega1.size(); i++) {
            data1.add(new XYChart.Data(i, listOmega1.get(i)));
            data2.add(new XYChart.Data(i, listOmega2.get(i)));
            data3.add(new XYChart.Data(i, listOmega3.get(i)));
        }

        series1.setData(data1);
        series2.setData(data2);
        series3.setData(data3);


        Scene scene = new Scene(numberLineChart, 600, 600);
        numberLineChart.getData().add(series1);
        numberLineChart.getData().add(series2);
        numberLineChart.getData().add(series3);
        scene.getStylesheets().add("sample/Chart.css");
        stage.setScene(scene);

        stage.show();

    }

    public void createGraphsLambda(List<Double> listLambda1, List<Double> listLambda2) {

        Stage stage = new Stage();

        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();

        LineChart<Number, Number> numberLineChart = new LineChart<Number, Number>(x, y);
        numberLineChart.setTitle("Series");
        numberLineChart.setCreateSymbols(false);

        XYChart.Series series1 = new XYChart.Series();

        for (int i = 0; i < listLambda1.size(); i+=10) {
            series1.getData().add(new XYChart.Data(listLambda1.get(i), listLambda2.get(i)));
        }

        Scene scene = new Scene(numberLineChart, 600, 600);
        numberLineChart.getData().add(series1);
        scene.getStylesheets().add("sample/Chart.css");
        stage.setScene(scene);

        stage.show();
    }
}
