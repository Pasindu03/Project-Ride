package lk.ijse.controller;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainDashboardControllerForm {
    public Label lblTime;
    public Label lblDate;
    public AnchorPane root;

    public void initialize() {
        TimeNow();
        lblDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
    }

    private void TimeNow(){
        Thread thread = new Thread(() ->{
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            while (!false){
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                Platform.runLater(() ->{
                    lblTime.setText(timenow);
                });
            }
        });
        thread.start();
}

}
