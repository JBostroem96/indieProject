package edu.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Keys {

	@JsonProperty("keys")
	private List<KeysItem> keys;

	/**
	 * Get keys list.
	 *
	 * @return the list
	 */
	public List<KeysItem> getKeys(){
		return keys;
	}
}