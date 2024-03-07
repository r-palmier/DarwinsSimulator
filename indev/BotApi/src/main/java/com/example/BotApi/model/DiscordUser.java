package com.example.BotApi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class made to represent a DiscordUser. It is the user in sense of the website and of discord.
 * 
 * @author Gervais Pierre
 *
 */
@Entity
@JsonIgnoreProperties({ "items" })	//To get the list of items a user owns and endpoint will have to be built for that.
public class DiscordUser {
	//DB id value attribute.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="discord_user_id")
	private int id;
	//Attributes.
	@Column(name="discord_user_name")
	private String name;							//NameTag of the discord user.
	private String userId;							//UserId of the discord user.
	
	@OneToMany(mappedBy = "discordUser")			//MappedBy relates to the variable name in the other class it is associated to.
	@JsonBackReference								//DiscordUser is the Child of the Item_DiscordUser relationship.
	private Set<Item> items = new HashSet<Item>();	//Association to the items created by the discord user.
	
	@OneToMany(mappedBy = "discordUser")			//MappedBy relates to the variable name in the other class it is associated to.
	@JsonBackReference								//DiscordUser is the Child of the Tag_DiscordUser relationship.
	private Set<Tag> tags = new HashSet<Tag>();		//Association to the items created by the discord user.
	
	@OneToMany(mappedBy = "discordUser")			//MappedBy relates to the variable name in the other class it is associated to.
	@JsonBackReference								//DiscordUser is the Child of the Category_DiscordUser relationship.
	private Set<Category> categories = new HashSet<Category>();//Association to the items created by the discord user.

	//Constructor.
	public DiscordUser() {
		
	}
	
	public DiscordUser(final String name, final String userId) {
		this.setName(name);
		this.setUserId(userId);
	}
	
	//Getter & Setter
	public int getId() {
    	return this.id;
    }
	public String getName() {
		return name;
	}
	private void setName(final String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	private void setUserId(final String userId) {
		this.userId = userId;
	}
	public Set<Item> getItems(){
		return this.items;
	}
	public Set<Tag> getTags(){
		return this.tags;
	}
	public Set<Category> getCategories(){
		return this.categories;
	}
	
	//Custom items attribute.
	public void addItem(final Item item) {
		this.getItems().add(item);
	}
	public void removeItem(final Item item) {
		this.getItems().remove(item);
	}
	public void addTag(final Tag tag) {
		this.getTags().add(tag);
	}
	public void removeTag(final Tag tag) {
		this.getTags().remove(tag);
	}
	public void addCategory(final Category category) {
		this.getCategories().add(category);
	}
	public void removeCategory(final Category category) {
		this.getCategories().remove(category);
	}
	
}
