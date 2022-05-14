package com.eleks.academy.whoami.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.eleks.academy.whoami.core.Game;
import com.eleks.academy.whoami.core.impl.Answer;
import com.eleks.academy.whoami.core.impl.PersistentGame;
import com.eleks.academy.whoami.core.impl.StartGameAnswer;
import com.eleks.academy.whoami.model.request.CharacterSuggestion;
import com.eleks.academy.whoami.model.request.NewGameRequest;
import com.eleks.academy.whoami.model.response.GameDetails;
import com.eleks.academy.whoami.model.response.GameLight;
import com.eleks.academy.whoami.repository.GameRepository;
import com.eleks.academy.whoami.service.GameService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

	private final GameRepository gameRepository;

	@Override
	public List<GameLight> findAvailableGames(String player) {
		return this.gameRepository.findAllAvailable(player).map(GameLight::of).collect(Collectors.toList());
	}

	@Override
	public GameDetails createGame(String player, NewGameRequest gameRequest) {
		final var game = this.gameRepository.save(new PersistentGame(player, gameRequest.getMaxPlayers()));

		return GameDetails.of(game);
	}

	@Override
	public void enrollToGame(String id, String player) {
		this.gameRepository.findById(id).filter(Game::isAvailable)
		        .ifPresentOrElse(game -> game.makeTurn(new Answer(player)), () -> {
			        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot enroll to a game");
		        });
	}

	@Override
	public Optional<GameDetails> findByIdAndPlayer(String id, String player) {
		return this.gameRepository.findById(id).filter(game -> game.hasPlayer(player)).map(GameDetails::of);
	}

	@Override
	public void suggestCharacter(String id, String player, CharacterSuggestion suggestion) {
		this.gameRepository.findById(id).filter(game -> game.hasPlayer(player))
		        .ifPresentOrElse(game -> game.makeTurn(new Answer(player, suggestion.getCharacter())), () -> {
			        throw new ResponseStatusException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase());
		        });
	}

	@Override
	public Optional<GameDetails> startGame(String id, String player) {
		UnaryOperator<Game> startGame = game -> {
			game.makeTurn(new StartGameAnswer(player));

			return game;
		};

		return this.gameRepository.findById(id).map(startGame).map(GameDetails::of);
	}

}
