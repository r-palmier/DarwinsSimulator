package com.example.BotApi.controller;

import com.example.BotApi.function.DuplicateCheck;
import com.example.BotApi.model.DiscordUser;
import com.example.BotApi.model.Item;
import com.example.BotApi.model.Tag;
import com.example.BotApi.model.Contract.DiscordMessage;
import com.example.BotApi.repository.DiscordUserRepository;
import com.example.BotApi.repository.ItemRepository;
import com.example.BotApi.repository.TagRepository;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * 
 * @author Gervais Pierre
 *
 */
@RestController
public class DiscordController {
	
	//Attributes.
	@Autowired
	private ItemRepository itemRepo;
	@Autowired
	private TagRepository tagRepo;
	@Autowired
	private DiscordUserRepository discordUserRepo;
	
	//Endpoints.
	@PostMapping("/addMessage")	//Add a modal form report from the discord bot to the database.
    public ResponseEntity<?> addMessage(@RequestBody final DiscordMessage discordMessage) {
		
		//Check if the discord user exists else make a new user. <-----> Check first if more than one user with the same id exist. If so merge them back together.
		DiscordUser discordUser = DuplicateCheck.isDiscordUserDuplicate(discordMessage.getUserId(), this.getDiscordUserRepo());		
		if (discordUser == null) {	
			//Make a new user and store it in the local variable then into the database.
			discordUser = this.getDiscordUserRepo().save(
					new DiscordUser(
							discordMessage.getUserTag(), 
							discordMessage.getUserId()
					));
		}
		
		//Check if each tag in the list exists else make a new tag for the list if needed.	<----->
		Set<Tag> tagSet = new HashSet<Tag>();
		for (String messageTag : discordMessage.getTags()) {
			messageTag.toLowerCase();
			//Check if tag exists.
			Tag tag = DuplicateCheck.isTagDuplicate(messageTag, this.getTagRepo());
			if (tag == null) {
				//Make a new tag and store it in the local variable then into the database.
				tag = this.getTagRepo().save(new Tag(messageTag, discordUser));
			}
			//Save tag to the tag list.
			tagSet.add(tag);
		}
		
		//Create a new Item to store the message date into the database.	<----->
		Item newItem = this.getItemRepo().save(
				new Item(
					discordMessage.getTitle(),
					discordMessage.getLink(),
					tagSet,
					discordMessage.isModal(),
					discordMessage.getDescription(),
					discordUser,
					discordMessage.getMessageId(),
					discordMessage.getTime()
				));
		
		//Sync up the relation between the tags & user to the item.
		newItem.getDiscordUser().addItem(newItem);
		for (Tag tag : newItem.getTags()) {
			tag.addItem(newItem);
		}
		
		//Send response message to confirm everything was done correctly.
        return new ResponseEntity<>("Message item added succesfully.", HttpStatus.OK);
    }
	
    @PostMapping("/addMessageFallback")	//It's a testing endpoint for the discord bot to see if it connects properly. (Basically just sending a response).
    public ResponseEntity<?> fallback() {
        return new ResponseEntity<>("Nothing added just returned that we recieved something.", HttpStatus.OK);
    }

    //Getter & Setters.
	public ItemRepository getItemRepo() {
		return this.itemRepo;
	}
	public TagRepository getTagRepo() {
		return this.tagRepo;
	}
	public DiscordUserRepository getDiscordUserRepo() {
		return this.discordUserRepo;
	}
	
	//Custom.
	
}