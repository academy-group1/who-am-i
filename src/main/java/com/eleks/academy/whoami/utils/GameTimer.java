package com.eleks.academy.whoami.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Timer;
import java.util.TimerTask;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameTimer {

    private String time;
    private boolean isRunning = false;

    public void progressive(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            private int sec = 0;
            private int min = 0;
            @Override
            public void run() {
                if(sec == 60){
                    min ++;
                    sec = 0;
                    if(min < 10){
                        setTime("0" + min + ":" + sec + "0");
                    }else{
                        setTime(min + ":" + sec + "0");
                    }
                    sec++;
                }else if(min == 99 && sec == 59){
                    setTime(min + ":" + sec);
                    isRunning = false;
                }else {
                    if(min < 10){
                        if(sec < 10) {
                            setTime("0" + min + ":" + sec + "0");
                        }else{
                            setTime("0" + min + ":" + sec);
                        }
                    }else{
                        if(sec < 10) {
                            setTime(min + ":" + sec + "0");
                        }else{
                            setTime(min + ":" + sec);
                        }
                    }
                    sec++;
                }
                if(isRunning == false){
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(task,0,1000);
    }
}