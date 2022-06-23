package com.eleks.academy.whoami.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Home {
    private QuickGame quickGame;
    private WaitingScreen waitingScreen;
    private int countOnlinePlayers = 0;

    public String getPlayerWithOnline(){
        String playersOnline = Integer.toString(this.countOnlinePlayers);
        if(this.countOnlinePlayers == 1) {
            playersOnline += " PLAYER ONLINE";
        }else {
            playersOnline += " PLAYERS ONLINE";
        }
        return playersOnline;
    }

    public void playQuickGame(){
        this.countOnlinePlayers++;
        this.quickGame.play();
    }

}
