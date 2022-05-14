package com.eleks.academy.whoami.repository;

import java.util.Optional;
import java.util.stream.Stream;

import com.eleks.academy.whoami.core.Game;

public interface GameRepository {

	Stream<Game> findAllAvailable(String player);

	Game save(Game game);

	Optional<Game> findById(String id);

}
