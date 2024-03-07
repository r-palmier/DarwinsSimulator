package com.example.BotApi.function;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.BotApi.model.Contract.AdminFormat;
import com.example.BotApi.model.Contract.PageFormat;

/**
 * Class used to check any http parameters that will go through the RestControllers.
 * So a class used to provide custom functions.
 * 
 * @author Gervais Pierre
*/
public class ParameterCheck {
	
	/**
	 * Used to check GetRequest that need a Pageable return.
	 * 
	 * @param params : The http RequestParams.
	 * @param paramType : Is it a pageable : "pageable" ? & Is there a query : "pageableQuery" ?
	 * @param checkType : Used to determine by what attributes it can be sorted based on intended return class : "item", "tag", "category", "generic".
	 * 
	 * @return {@link PageFormat} : Can return either the proper values to build a {@link PageRequest} or return a {@link ResponseEntity} if the parameters are incorrect. {@link PageFormat} has a .isError() function to determine which it contains. 
	 * 
	 * @author Gervais Pierre
	 */
	public static PageFormat checkPageable(final Map<String, String> params,final String paramType, final String checkType) {
		
		switch(paramType) {	//Check if the request parameters are valid. Based on the indented parameters that should be received. if not return the HttpStatus.
		
			case "pageable":
				if (!(params.containsKey("page") && params.containsKey("size") && params.containsKey("sort") && (params.size() == 3))) {
					return new PageFormat(new ResponseEntity<>("Parameters are not valid. Must include [page, size and sort].", HttpStatus.BAD_REQUEST));
				}
				break;
				
			case "pageableQuery":
				if (!(params.containsKey("q") && params.containsKey("page") && params.containsKey("size") && params.containsKey("sort") && (params.size() == 4))) {
					return new PageFormat(new ResponseEntity<>("Parameters are not valid. Must include [q, page, size and sort].", HttpStatus.BAD_REQUEST));
				}
				break;
		}
		
		//Check & Convert to an integer the String value of the page & size parameter.
		int page;
		int size;
		try {
			page = Integer.parseInt(params.get("page"));
			size = Integer.parseInt(params.get("size"));
		}catch(NumberFormatException error) {
			return new PageFormat(new ResponseEntity<>("Page or Size was not a valid number. Must be an integer.", HttpStatus.BAD_REQUEST));
		}		
		
		//Get the sort String and decide which type of check it should go through.
		String sort = params.get("sort");
		switch(checkType) {	//Check if the sort string is a valid authorised one. Based on the type of Object that must be returned by the endpoint.
		
			case "item":
				if (!(sort.equals("id") || sort.equals("name") || sort.equals("discordUser") || sort.equals("time"))) {
					return new PageFormat(new ResponseEntity<>("Sort value is not valid. Try [id, name, discordUser or time].", HttpStatus.BAD_REQUEST));
				}
				break;
			
			case "tag":
				if (!(sort.equals("id") || sort.equals("name") || sort.equals("itemCount"))) {
					return new PageFormat(new ResponseEntity<>("Sort value is not valid. Try [id, name, itemCount].", HttpStatus.BAD_REQUEST));
				}
				break;
			
			case "category":
				if (!(sort.equals("id") || sort.equals("name") || sort.equals("itemCount") || sort.equals("tagCount"))) {
					return new PageFormat(new ResponseEntity<>("Sort value is not valid. Try [id, name, itemCount, tagCount].", HttpStatus.BAD_REQUEST));
				}
				break;

			case "generic":
				if (!(sort.equals("id") || sort.equals("name"))) {
					return new PageFormat(new ResponseEntity<>("Sort value is not valid. Try [id, name].", HttpStatus.BAD_REQUEST));
				}
				break;
			
			default:
				//If the search type is null or wrong then sort by Id. Since all models should have an Id.
				sort = "id";
				break;
		}
		
		//If all check out return a PageFormat.
		return new PageFormat(page, size, sort);
	}
	
	
	/**
	 * Used to check DeleteRequest made by an admin.
	 * 
	 * @param params : The http RequestParams.
	 * 
	 * @return {@link AdminFormat} : Can return the id integer or return a {@link ResponseEntity} if the parameters are incorrect. {@link AmdinFormat} has a .isError() function to determine which it contains. 
	 * 
	 * @author Gervais Pierre
	 */
	public static AdminFormat checkAdminDelete(final Map<String, String> params) {
		
		//Check if the request parameter is valid.
		if (!((params.containsKey("id") && (params.size() == 1)))) {
			return new AdminFormat(new ResponseEntity<>("Parameters are not valid. Must include [id].", HttpStatus.BAD_REQUEST));
		}
		
		//Check & Convert to an integer the String value of the id parameter.
		int id;
		try {
			id = Integer.parseInt(params.get("id"));
		}catch(NumberFormatException error) {
			return new AdminFormat(new ResponseEntity<>("Id was not a valid number. Must be an integer.", HttpStatus.BAD_REQUEST));
		}
		
		//If all check out return a AdminFormat.
		return new AdminFormat(id);
	}
	
}
