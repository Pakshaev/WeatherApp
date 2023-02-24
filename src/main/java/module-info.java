module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.WeatherApp to javafx.fxml;
    exports com.example.WeatherApp;
}