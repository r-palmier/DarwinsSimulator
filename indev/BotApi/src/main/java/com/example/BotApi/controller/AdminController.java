package com.example.BotApi.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BotApi.function.ParameterCheck;
import com.example.BotApi.model.Category;
import com.example.BotApi.model.Item;
import com.example.BotApi.model.Tag;
import com.example.BotApi.model.Contract.AdminFormat;
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
public class AdminController {
	
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
	@DeleteMapping("/deleteItem")
	public ResponseEntity<?> deleteItem(@RequestParam final Map<String, String> params) {
		
		//Check if the http parameters are correct. If not return relevant error code.
		AdminFormat admin = ParameterCheck.checkAdminDelete(params);
		if (admin.isError()) {
			return admin.getResponse();
		}
		//Check if the id exist inside the item repository. If not return relevant error code.
		Optional<Item> item = this.getItemRepo().findById(admin.getId());
		if (item.isEmpty()) {
			return new ResponseEntity<>("The queried Id does not exist in the Item Repository.", HttpStatus.NOT_FOUND);
		}
		//If everything checks out then delete the item and it's relations then send a confirmation response.
		for(Tag tag : item.get().getTags()) {
			tag.removeItem(item.get());
		}
		for(Category category : item.get().getCategories()) {
			category.removeItem(item.get());
		}
		item.get().getDiscordUser().removeItem(item.get());
		
		this.getItemRepo().deleteById(admin.getId());
		return new ResponseEntity<>("Item : [" + item.get().getName() + "] with id [" + item.get().getId() + "] was succesfully deleted." , HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteTag")
	public ResponseEntity<?> deleteTag(@RequestParam final Map<String, String> params) {
		
		//Check if the http parameters are correct. If not return relevant error code.
		AdminFormat admin = ParameterCheck.checkAdminDelete(params);
		if (admin.isError()) {
			return admin.getResponse();
		}
		//Check if the id exist inside the item repository. If not return relevant error code.
		Optional<Tag> tag = this.getTagRepo().findById(admin.getId());
		if (tag.isEmpty()) {
			return new ResponseEntity<>("The queried Id does not exist in the Tag Repository.", HttpStatus.NOT_FOUND);
		}
		//If everything checks out then delete the item and it's relations then send a confirmation response.
		for(Item item : tag.get().getItems()) {
			item.removeTag(tag.get());
		}
		for(Category category : tag.get().getCategories()) {
			category.removeTag(tag.get());
		}
		tag.get().getDiscordUser().removeTag(tag.get());
		
		this.getItemRepo().deleteById(admin.getId());
		return new ResponseEntity<>("Tag : [" + tag.get().getName() + "] with id [" + tag.get().getId() + "] was succesfully deleted." , HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteCategory")
	public ResponseEntity<?> deleteCategory(@RequestParam final Map<String, String> params) {
		
		//Check if the http parameters are correct. If not return relevant error code.
		AdminFormat admin = ParameterCheck.checkAdminDelete(params);
		if (admin.isError()) {
			return admin.getResponse();
		}
		//Check if the id exist inside the category repository. If not return relevant error code.
		Optional<Category> category = this.getCategoryRepo().findById(admin.getId());
		if (category.isEmpty()) {
			return new ResponseEntity<>("The queried Id does not exist in the Category Repository.", HttpStatus.NOT_FOUND);
		}
		//If everything checks out then delete the category and it's relations then send a confirmation response.
		for(Item item : category.get().getItems()) {
			item.removeCategory(category.get());
		}
		for(Tag tag : category.get().getTags()) {
			tag.removeCategory(category.get());
		}
		category.get().getDiscordUser().removeCategory(category.get());
		
		this.getCategoryRepo().deleteById(admin.getId());
		return new ResponseEntity<>("Category : [" + category.get().getName() + "] with id [" + category.get().getId() + "] was succesfully deleted." , HttpStatus.OK);
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
	
	
	//Custom functions.
}
