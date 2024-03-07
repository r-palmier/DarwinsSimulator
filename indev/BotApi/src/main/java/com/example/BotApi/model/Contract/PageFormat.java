package com.example.BotApi.model.Contract;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

/**
 * Class used to contain information to build {@link PageRequest} or a {@link ResponseEntity}. Use .isError() function to determine which it contains. 
 * 
 * @author Gervais Pierre
 */
public class PageFormat {
	
	//Attributes.			//The book refers to the Set<T> that will be instanced into many subLists or pages.
	private boolean error;	//Tells whether the check should return an error or is valid. 
	private int page;		//The page from which it should pull inside of the book. [0 <-> +infinite].
	private int size;		//The size of the page. Note this will change what each page has though. So always ask for the same page size.
	private String sort;	//The value by which the book of pages should be sorted. Should refer to the attribute name of the requested class.
	private ResponseEntity<?> response;	//The error that should be returned if there is one.
	
	//Constructors.
	public PageFormat() {
		
	}
	public PageFormat(final ResponseEntity<?> response) {	//For returning errors.
		this.setError(true);
		this.setPage(0);
		this.setSize(0);
		this.setSort(null);
		this.setResponse(response);
	}
	public PageFormat(final int page, final int size, final String sort) {	//For returning valid checks.
		this.setError(false);
		this.setPage(page);
		this.setSize(size);
		this.setSort(sort);
		this.setResponse(null);
	}
	
	//Getters & Setters
	public boolean isError() {
		return error;
	}
	private void setError(boolean error) {
		this.error = error;
	}
	public int getPage() {
		return page;
	}
	private void setPage(final int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	private void setSize(final int size) {
		this.size = size;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(final String sort) {
		this.sort = sort;
	}
	public ResponseEntity<?> getResponse() {
		return response;
	}
	private void setResponse(ResponseEntity<?> response) {
		this.response = response;
	}
	
}
