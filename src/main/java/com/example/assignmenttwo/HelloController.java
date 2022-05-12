package com.example.assignmenttwo;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.util.concurrent.Callable;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label timeDuration;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Slider progressBar;
    @FXML
    private ImageView pause;

    @FXML
    private ImageView stop;

    @FXML
    private ImageView play;
    @FXML
    private MediaView mediaView;
    private MediaPlayer player;

    @FXML
    public void initialize() {
        String video = getClass().getResource("/design.mp4").toExternalForm();
        Media media = new Media(video);
        player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        sliderVolume1();

        timeDurationLabel();

        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if(volumeSlider.isValueChanging()){
                    player.setVolume(volumeSlider.getValue()*0.01);
                }
            }
        });

        player.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observableValue, Duration duration, Duration t1) {
                progressBar.setValue(t1.toSeconds());
            }
        });
    }


    @FXML
    public void  sliderVolume1() {
        volumeSlider.setValue(player.getVolume()*100);
        player.setOnReady(new Runnable() {
            @Override
            public void run() {
                volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        if(volumeSlider.isValueChanging()){
                            player.setVolume(volumeSlider.getValue()*0.01);
                        }
                    }
                });
            }
        });
    }

    @FXML
    void playVideo(MouseEvent event){
        player.play();
    }



    @FXML
    void stopVideo(MouseEvent event) {

        player.stop();
    }

    @FXML
    void pauseVideo(MouseEvent event) {
        player.pause();
    }


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void timeDurationLabel(){
        timeDuration.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return getTime(player.getCurrentTime()) + " /";
            }
        },player.currentTimeProperty()));
    }

    public String getTime(Duration time){
        int hours = (int) time.toHours();
        int minutes = (int) time.toMinutes();
        int seconds = (int) time.toSeconds();

        if (seconds >59) seconds = seconds % 60;
        if (minutes >59) minutes = minutes % 60;
        if (hours >59) hours = hours % 60;

        if(hours > 0) return String.format("%d:%02d%02d", hours, minutes, seconds);
        else return  String.format("%02d:%02d",hours, minutes, seconds);
    }





/*
    @FXML
    private MediaView mediaView;

    @FXML
    private Slider progressBar;

    @FXML
    private ImageView pause;

    @FXML
    private ImageView stop;

    @FXML
    private ImageView play;

    @FXML
    private Slider volumeSlider;

    @FXML
    void pauseVideo(MouseEvent event) {

    }

    @FXML
    void playVideo(MouseEvent event) {

    }

    @FXML
    void sliderVolume1(MouseEvent event) {

    }

    @FXML
    void stopVideo(MouseEvent event) {

    }


*/








}
