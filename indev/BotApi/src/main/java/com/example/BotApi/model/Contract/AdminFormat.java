package com.example.BotApi.model.Contract;

import org.springframework.http.ResponseEntity;

/**
 * Class used to return information related to checking admin conditions for administrative purposes.
 * And while checking also retrieve the information else the error information needed for a response.
 * 
 * @author Gervais Pierre
 *
 */
public class AdminFormat {
	
	//Attributes.
	private boolean error;				//Tells whether the check should return an error or is valid. 
	private int id;						//The id that was checked.
	private ResponseEntity<?> response;	//The error that should be returned if there is one.
	
	//Constructor.
	public AdminFormat(int id) {
		this.setError(false);
		this.setId(id);
		this.setResponse(null);
	}
	
	public AdminFormat(ResponseEntity<?> response) {
		this.setError(true);
		this.setId(0);
		this.setResponse(response);
	}
	
	//Getter & Setter.
	public boolean isError() {
		return error;
	}
	private void setError(boolean error) {
		this.error = error;
	}
	public int getId() {
		return id;
	}
	private void setId(int id) {
		this.id = id;
	}
	public ResponseEntity<?> getResponse() {
		return response;
	}
	private void setResponse(ResponseEntity<?> response) {
		this.response = response;
	}
	
	
}
