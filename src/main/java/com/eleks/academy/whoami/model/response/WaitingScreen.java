package com.eleks.academy.whoami.model.response;

import com.eleks.academy.whoami.controller.GameController;
import com.eleks.academy.whoami.model.request.Message;
import com.eleks.academy.whoami.model.request.NewGameRequest;
import com.eleks.academy.whoami.utils.GameTimer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WaitingScreen {

    private final Message message = new Message("PLEASE WAIT UNTIL WE FIND YOUR OPPONENTS");

    private static int countWaitingPlayers = 0;

    private GameController gameController;
    private QuickGame quickGame;
    private NewGameRequest newGameRequest;
    private GameTimer timer;

    public void play(){
        countPlayers();
        this.timer.progressive();
        if(this.newGameRequest.getMaxPlayers().equals(countWaitingPlayers)){
            this.gameController.startGame(quickGame.getGame().getId(), "Player 1");
        }
    }

    public void leaveGame(){
        timer.setRunning(false);
        countWaitingPlayers--;
    }

    public String getTimer(){
        return timer.getTime();
    }

    private int countPlayers(){
        countWaitingPlayers++;
        if(countWaitingPlayers > newGameRequest.getMaxPlayers() - 1){
            countWaitingPlayers = 1;
        }
        return countWaitingPlayers;
    }
}
