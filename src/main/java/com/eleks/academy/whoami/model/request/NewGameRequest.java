package com.eleks.academy.whoami.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewGameRequest {

	// TODO: Retrieve from config
	@Min(2)
	@Max(4)
	@NotNull
	private Integer maxPlayers;

}
