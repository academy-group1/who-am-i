package com.eleks.academy.whoami.core;

import java.util.concurrent.Future;

public interface SynchronousPlayer {

	String getName();

	String getCharacter();

	void setCharacter(String character);

}