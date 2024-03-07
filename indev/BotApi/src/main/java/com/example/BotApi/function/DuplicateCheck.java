package com.example.BotApi.function;

import java.util.ArrayList;
import java.util.List;

import com.example.BotApi.model.Category;
import com.example.BotApi.model.DiscordUser;
import com.example.BotApi.model.Item;
import com.example.BotApi.model.Tag;
import com.example.BotApi.repository.DiscordUserRepository;
import com.example.BotApi.repository.TagRepository;

/**
 * Class Used to check for duplicates inside of the database and then merge those duplicates back into one.
 * 
 * @author Gervais Pierre
 *
 */
public class DuplicateCheck {
	
	//Functions.
	
	/**
	 * A function to check for duplicates inside of the {@link DiscordUserRepository} database. If duplicates are found it will merge them back into one.
	 * 
	 * @param userId : The Discord userId that the bot uses to refer to a user that is inside the database.
	 * @param discordUserRepo : The database repository that stores the discordUsers for the api.
	 * @return null : if no match was found || discordUser : if one or multiple matches were found after being merged back into one.
	 * 
	 * @author Gervais Pierre
	 * 
	 */
	public static DiscordUser isDiscordUserDuplicate(final String userId, DiscordUserRepository discordUserRepo) {
		
		//Retrieve a list of all elements matching the userId from the database.
		ArrayList<DiscordUser> discordUsersArray = discordUserRepo.findAllByuserId(userId);
		
		//Check if the array list is empty and return null if no object found.
		if(discordUsersArray.isEmpty()) {	
			return null;
		}
		
		//If the ArrayList isn't empty then it contains at least one user. So retrieve one that will be considered the original.
		DiscordUser original = discordUsersArray.get(0);
		//Check if the array list only has one object. If it does then return the object.
		if(discordUsersArray.size() == 1) {	
			return original;
		}
		
		List<DiscordUser> discordUsers = discordUsersArray;
		//If there is more than 1 then remove the original from the ArrayList.
		discordUsers.remove(original);	
		//Now that the original has been removed cycle through the duplicates. And then merge em back into the original.
		for(DiscordUser duplicate : discordUsers) {	
			//Transfer all items from the duplicate into the original. And reassign relation. 
			for(Item item : duplicate.getItems()) { 
				item.setDiscordUser(original);
				original.addItem(item);
				duplicate.removeItem(item);
			}
			//Transfer all tags from the duplicate into the original. And reassign relation.
			for(Tag tag : duplicate.getTags()) {	
				tag.setDiscordUser(original);
				original.addTag(tag);
				duplicate.removeTag(tag);
			}
			//Transfer all categories from the duplicate into the original. And reassign relation.
			for(Category category : duplicate.getCategories()) {	
				category.setDiscordUser(original);
				original.addCategory(category);
				duplicate.removeCategory(category);
			}
		}
		
		//Finally return the original after it has been merged with the duplicates.
		return original;
	}
	
	
	/**
	 * A function to check for duplicates inside of the {@link TagRepository} database. If duplicates are found it will merge them back into one.
	 * 
	 * @param name : The tag name that the bot uses to refer to a tag that is inside the database.
	 * @param tagRepo : The database repository that stores the Tags for the api.
	 * @return null : if no match was found || tag : if one or multiple matches were found after being merged back into one.
	 * 
	 * @author Gervais Pierre
	 * 
	 */
	public static Tag isTagDuplicate(final String name, TagRepository tagRepo) {
		
		//Retrieve a list of all elements matching the name from the database.
		ArrayList<Tag> tagsArray = tagRepo.findAllByNameIgnoreCase(name);
		
		//Check if the array list is empty and return null if no object found.
		if(tagsArray.isEmpty()) {	
			return null;
		}
		
		//If the ArrayList isn't empty then it contains at least one user. So retrieve one that will be considered the original.
		Tag original = tagsArray.get(0);
		//Check if the array list only has one object. If it does then return the object.
		if(tagsArray.size() == 1) {	
			return original;
		}
		
		List<Tag> tags = tagsArray;
		//If there is more than 1 then remove the original from the ArrayList.
		tags.remove(original);	
		//Now that the original has been removed cycle through the duplicates. And then merge em back into the original.
		for(Tag duplicate : tags) {	
			//Transfer all items from the duplicate into the original. And reassign relation. 
			for(Item item : duplicate.getItems()) {
				item.addTag(original);
				item.removeTag(duplicate);
				original.addItem(item);
				duplicate.removeItem(item);
			}
			//Transfer all categories from the duplicate into the original. And reassign relation. 
			for(Category category : duplicate.getCategories()) {
				category.addTag(original);
				category.removeTag(duplicate);
				original.addCategory(category);
				duplicate.removeCategory(category);
			}
		}
		
		//Finally return the original after it has been merged with the duplicates.
		return original;
	}
	
}