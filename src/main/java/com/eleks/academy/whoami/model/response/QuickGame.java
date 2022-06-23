package com.eleks.academy.whoami.model.response;

import com.eleks.academy.whoami.controller.GameController;
import com.eleks.academy.whoami.model.request.NewGameRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuickGame {

    private static int countOnlinePlayers = 1;
    private NewGameRequest newGameRequest;
    private GameController gameController;
    private GameLight game;


    public void play() {
        String player = "Player " + countOnlinePlayers;
        List<GameLight> availableGames;
        availableGames = this.gameController.findAvailableGames(player);
        if (availableGames.size() != 0) {
            this.game = availableGames.get(0);
            this.gameController.enrollToGame(game.getId(), player);
        } else {
            this.gameController.createGame(player, newGameRequest);
        }
        CountPlayers();
    }

    private int CountPlayers(){
        countOnlinePlayers++;
        if(countOnlinePlayers > newGameRequest.getMaxPlayers()){
            countOnlinePlayers = 1;
        }
        return countOnlinePlayers;
    }

}
