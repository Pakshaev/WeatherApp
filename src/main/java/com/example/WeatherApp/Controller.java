package com.example.WeatherApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField city;

    @FXML
    private Text feels;

    @FXML
    private Button getData;

    @FXML
    private Text info;

    @FXML
    private Text pressure;

    @FXML
    private Text temp;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_min;

    @FXML
    void initialize() {
        getData.setOnAction(event -> {
            String getUserCity = city.getText().trim();
            if (!getUserCity.equals("")) {
                String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&appid=c72e2cbac55206a7fc8ee1322836ff4a&units=metric");
                if (!output.isEmpty()){
                    JSONObject jsonObject = new JSONObject(output);
                    temp.setText("Температура: " + Math.round(jsonObject.getJSONObject("main").getDouble("temp")));
                    feels.setText("Ощущается: " + Math.round(jsonObject.getJSONObject("main").getDouble("feels_like")));
                    temp_max.setText("Максимум: " + Math.round(jsonObject.getJSONObject("main").getDouble("temp_max")));
                    temp_min.setText("Минимум: " + Math.round(jsonObject.getJSONObject("main").getDouble("temp_min")));
                    pressure.setText("Давление: " + Math.round(jsonObject.getJSONObject("main").getDouble("pressure")));
                }
            }
            else {
                temp.setText("Температура: ");
                feels.setText("Ощущается: ");
                temp_max.setText("Максимум: ");
                temp_min.setText("Минимум: ");
                pressure.setText("Давление: ");
            }
        });
    }

    private static String getUrlContent(String urlAdress) {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAdress);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while((line=bufferedReader.readLine())!=null){
                content.append(line + "\n");
            }
            bufferedReader.close();
            return content.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}