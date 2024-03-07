package com.example.BotApi.controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BotApi.function.ParameterCheck;
import com.example.BotApi.model.Category;
import com.example.BotApi.model.DiscordUser;
import com.example.BotApi.model.Item;
import com.example.BotApi.model.Tag;
import com.example.BotApi.model.Contract.PageFormat;
import com.example.BotApi.repository.CategoryRepository;
import com.example.BotApi.repository.DiscordUserRepository;
import com.example.BotApi.repository.ItemRepository;
import com.example.BotApi.repository.TagRepository;

/**
 * 
 * 
 * @author Gervais Pierre
 *
 */
@RestController
public class SpaController {
	
	//Attributes.
	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private TagRepository tagRepo;
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private DiscordUserRepository discordUserRepo;
	
	
	//Endpoints.

	//Get a page of items.
	@CrossOrigin(origins = "http://127.0.0.1:5173/")
	@GetMapping("/getItemsPage")	//This will be used to get an infinite scrolling page by requesting it in portions at a time.
	public ResponseEntity<?> getItemPage(@RequestParam final Map<String, String> params){
		
		//Check the pageFormat parameter values.
		PageFormat pageFormat = ParameterCheck.checkPageable(params,"pageable", "item");
		if (pageFormat.isError()) {
			return pageFormat.getResponse();
		}
		
		//If the parameters are correct then retrieve the asked data.
		Pageable sortedBy = PageRequest.of(pageFormat.getPage(), pageFormat.getSize(), Sort.by(pageFormat.getSort()));
		Page<Item> page = this.getItemRepo().findAll(sortedBy);
		
		//Return the page though specify if the content is empty if it is.
		if (page.getContent().isEmpty()) {
			return new ResponseEntity<>(page, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(page, HttpStatus.OK);
	}
	
	//Get a page of tags.
	@GetMapping("/getTagsPage")		//This will be used to retrieve tags in packs at a time.
	public ResponseEntity<?> getTagsById(@RequestParam final Map<String, String> params){
		
		//Check the pageFormat parameter values.
		PageFormat pageFormat = ParameterCheck.checkPageable(params,"pageable", "tag");
		if (pageFormat.isError()) {
			return pageFormat.getResponse();
		}
		
		//If the parameters are correct then retrieve the asked data.
		Pageable sortedBy = PageRequest.of(pageFormat.getPage(), pageFormat.getSize(), Sort.by(pageFormat.getSort()));
		Page<Tag> page = this.getTagRepo().findAll(sortedBy);
		
		//Return the page though specify if the content is empty if it is.
		if (page.getContent().isEmpty()) {
			return new ResponseEntity<>(page, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(page, HttpStatus.OK);
		
	}
	
	//Get a page of Categories.
	@GetMapping("/getCategoryPage")		//This will be used to retrieve tags in packs at a time.
	public ResponseEntity<?> getCategoryPage(@RequestParam final Map<String, String> params){
		
		//Check the pageFormat parameter values.
		PageFormat pageFormat = ParameterCheck.checkPageable(params,"pageable", "category");
		if (pageFormat.isError()) {
			return pageFormat.getResponse();
		}
		
		//If the parameters are correct then retrieve the asked data.
		Pageable sortedBy = PageRequest.of(pageFormat.getPage(), pageFormat.getSize(), Sort.by(pageFormat.getSort()));
		Page<Category> page = this.getCategoryRepo().findAll(sortedBy);
		
		//Return the page though specify if the content is empty if it is.
		if (page.getContent().isEmpty()) {
			return new ResponseEntity<>(page, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(page, HttpStatus.OK);
		
	}
	
	//Get a page of items sorted by queried name. <-!!!-> No fuzzy matching yet.
	@GetMapping("/getNameFilteredItemPage")
	public ResponseEntity<?> getNameFilteredPage(@RequestParam final Map<String, String> params) {
		
		//Check the pageFormat parameter values.
		PageFormat pageFormat = ParameterCheck.checkPageable(params,"pageableQuery", "item");
		if (pageFormat.isError()) {
			return pageFormat.getResponse();
		}
		
		//If everything checks out then return the results. If there were no results return an empty Set.
		Pageable sortedBy = PageRequest.of(pageFormat.getPage(), pageFormat.getSize(), Sort.by(pageFormat.getSort()));
		Page<Item> page = this.getItemRepo().findAllByNameStartingWithIgnoreCase(params.get("q"), sortedBy);
		
		//Return the page though specify if the content is empty if it is.
		if (page.getContent().isEmpty()) {
			return new ResponseEntity<>(page, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(page, HttpStatus.OK);
	}
	
	//There is no point in searching by Id since the client does not always know all the tags that exist.
	//Will returns matching items containing the listed tags if they have one or more. So an OR condition not AND for the search.
	@GetMapping("/getTagsFilteredItemPage")		//This will get a list of items matching queried tags.
	public ResponseEntity<?> getTagsFilteredPage(@RequestParam final Map<String, String> params){
		
		//Check the pageFormat parameter values.
		PageFormat pageFormat = ParameterCheck.checkPageable(params,"pageableQuery", "item");
		if (pageFormat.isError()) {
			return pageFormat.getResponse();
		}
		
		//Check if the tags in the query exist in the database and retrieve them if so.
		String[] tagNames = params.get("q").split(" ");
		Set<Tag> queriedTags = new HashSet<Tag>();
		for(String tagName : tagNames) {	//For each query check if the tag exists.
			Tag tag = this.getTagRepo().findByName(tagName);
			if (tag  != null) {	//If the tag exists add it to the list.
					queriedTags.add(tag);
			} else {	//If the tag does not exist return the string.
				return new ResponseEntity<>("One of the queried tags does not exist or query was not built properly.", HttpStatus.NOT_FOUND);
			}
		}
		
		//If everything checks out then return the results. If there were no results return an empty Set.
		Pageable sortedBy = PageRequest.of(pageFormat.getPage(), pageFormat.getSize(), Sort.by(pageFormat.getSort()));
		Page<Item> page = this.getItemRepo().findAllByTagsIn(queriedTags, sortedBy);
		
		//Return the page though specify if the content is empty if it is.
		if (page.getContent().isEmpty()) {
			return new ResponseEntity<>(page, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(page, HttpStatus.OK);
	}
	
	@GetMapping("/getDiscordUserFilteredItemPage")		//This will get a list of items matching queried userId.
	public ResponseEntity<?> getUserFilteredPage(@RequestParam final Map<String, String> params){
		
		//Check the pageFormat parameter values.
		PageFormat pageFormat = ParameterCheck.checkPageable(params,"pageableQuery", "item");
		if (pageFormat.isError()) {
			return pageFormat.getResponse();
		}
		
		//Check if the DiscordUser in the query exist in the database and retrieve it if so.
		DiscordUser discordUser = this.getDiscordUserRepo().findByuserId(params.get("q"));
		if (discordUser == null) {
			return new ResponseEntity<>("The queried userId does not exist or query was not built properly.", HttpStatus.NOT_FOUND);
		}
		
		//If everything checks out then return the results. If there were no results return an empty Set.
		Pageable sortedBy = PageRequest.of(pageFormat.getPage(), pageFormat.getSize(), Sort.by(pageFormat.getSort()));
		Page<Item> page = this.getItemRepo().findAllBydiscordUser(discordUser, sortedBy);
				
		//Return the page though specify if the content is empty if it is.
		if (page.getContent().isEmpty()) {
			return new ResponseEntity<>(page, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(page, HttpStatus.OK);
	}
	
	//Get a page of tags sorted by queried name. This can be used to get proposed searches for tags. Like a small box under the search bar where you can click a suggested tag.
	//Note this endpoint should be called like after the person has stopped typing or every 0.5-1s.
	@GetMapping("/getNameFilteredTagPage")
	public ResponseEntity<?> getNameFilteredTagPage(@RequestParam final Map<String, String> params) {
			
		//Check the pageFormat parameter values.
		PageFormat pageFormat = ParameterCheck.checkPageable(params,"pageableQuery", "tag");
		if (pageFormat.isError()) {
			return pageFormat.getResponse();
		}
			
		//If everything checks out then return the results. If there were no results return an empty Set.
		Pageable sortedBy = PageRequest.of(pageFormat.getPage(), pageFormat.getSize(), Sort.by(pageFormat.getSort()));
		Page<Tag> page = this.getTagRepo().findAllByNameStartingWithIgnoreCase(params.get("q"), sortedBy);
			
		//Return the page though specify if the content is empty if it is.
		if (page.getContent().isEmpty()) {
			return new ResponseEntity<>(page, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(page, HttpStatus.OK);
	}
	
	
	//Getter & Setters.
	private ItemRepository getItemRepo() {
		return this.itemRepo;
	}
	private TagRepository getTagRepo() {
		return this.tagRepo;
	}
	private CategoryRepository getCategoryRepo() {
		return this.categoryRepo;
	}
	private DiscordUserRepository getDiscordUserRepo() {
		return this.discordUserRepo;
	}
}
