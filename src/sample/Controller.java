package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable{


    public SplitMenuButton split_list;

    @FXML
    private ImageView omegaModel;

    @FXML
    private ImageView omegaLabel;

    @FXML
    private ImageView thetaLabel;

    @FXML
    private ImageView lambdaLabel;

    @FXML
    private ImageView lambdaModel;

    @FXML
    private Button choiceAlgorytm;

    public static int choiceModel = 1;

    private String[] firstModel = {"third_point_model/omegaModel.png", "third_point_model/lambdaModel.png"};

    String[] secondModel = {"multi_third_point_model/omegaModel.png", "multi_third_point_model/lambdaModel.png"};

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Image labelOmega = new Image(Main.class.getResourceAsStream("third_point_model/labelomega.png"));
        Image labelLambda = new Image(Main.class.getResourceAsStream("third_point_model/lambaLabel.png"));
        Image labelTheta = new Image(Main.class.getResourceAsStream("third_point_model/thetaLabel.png"));

        omegaLabel.setImage(labelOmega);
        thetaLabel.setImage(labelTheta);
        lambdaLabel.setImage(labelLambda);

        for (final MenuItem item : split_list.getItems()) {
            item.setOnAction((event) -> {
                split_list.setText(item.getText());
                switch (item.getText()){
                    case "Трьохчастотна тригонометрична кватерніонна модель":
                        choiceModel = 1;

                        setImages(firstModel);
                        break;

                    case "Мультиплікативна трьохчастотна модель":
                        choiceModel = 2;

                        setImages(secondModel);
                        break;

                    case "Двухчастотна несиметрична модель 1":
                        choiceModel = 3;
                        break;

                    case "Двухчастотна несиметрична модель 2":
                        choiceModel = 4;
                        break;

                    case "Модель конічного обертання":
                        choiceModel = 5;
                        break;

                    case "Модель регулярної прецесії":
                        choiceModel = 6;
                        break;
                }
            });
        }


        choiceAlgorytm.setOnAction(event -> {
            switch (choiceModel){
                case 1:
                    createWindowAlgorytm();
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    break;

                case 6:
                    createWindowModelRegular();
                    break;
            }
            });
    }



    private void setImages(String[] arr)
    {
        int size = arr.length;

        Image[] image = new Image[size];
        for (int i = 0; i < size; i++) {
            image[i] = new Image(Main.class.getResourceAsStream(arr[i]));
        }
        omegaModel.setImage(image[0]);
        lambdaModel.setImage(image[1]);
    }

    private void createWindowAlgorytm()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("layout_for_input_parametrs_third_mult.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Введите параметры");
            stage.setScene(new Scene(root, 421, 379));
            stage.show();
            // Hide this current window (if this is what you want)
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createWindowModelRegular()
    {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("layout_for_model_regular_precesing.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Введите параметры");
            stage.setScene(new Scene(root, 421, 379));
            stage.show();
            // Hide this current window (if this is what you want)
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
