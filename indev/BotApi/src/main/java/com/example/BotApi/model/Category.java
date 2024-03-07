package com.example.BotApi.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Class made to represent a category. It groups up multiple tags.
 * 
 * @author Gervais Pierre
 *
 */
@Entity
public class Category {
		
	//DB id value attribute.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id")
	private int id;
	//Attributes.
	@Column(name="category_name")
	private String name;
	private int itemCount;
	private int tagCount;
	
	@ManyToMany(mappedBy = "categories")	//MappedBy relates to the variable name in the other class it is associated to.
	@JsonBackReference						//Category is the Child of the Item_Category relationship.
	private Set<Item> items = new HashSet<Item>();
	
	@ManyToMany			
	@JsonManagedReference					//Category is the Parent of the Category_Tag relationship.
	private Set<Tag> tags = new HashSet<Tag>();
	
	@ManyToOne
    @JsonManagedReference					//Category is the Parent of the Category_DiscordUser relationship.
    private DiscordUser discordUser;		//Association to the user who made the tag existing in the database.
		
	//Constructor.
	public Category() {
		
	}
		
	public Category(final String name, final DiscordUser discordUser) {
		this.setName(name);
		this.setDiscordUser(discordUser);
	}
		
	//Getter & Setter.
	public int getId() {
    	return this.id;
    }
	public String getName() {
		return name;
	}
	private void setName(final String name) {
		this.name = name;
	}
	public int getItemCount() {
		return this.itemCount;
	}
	private void setItemCount(final int itemCount) {
		this.itemCount = itemCount;
	}
	public int getTagCount() {
		return this.tagCount;
	}
	private void setTagCount(final int tagCount) {
		this.tagCount = tagCount;
	}
	public DiscordUser getDiscordUser() {
		return this.discordUser;
	}
	public void setDiscordUser(final DiscordUser discordUser) {
		this.discordUser = discordUser;
	}
	public Set<Item> getItems(){
		return this.items;
	}
	public Set<Tag> getTags(){
		return this.tags;
	}
		
	//Custom items attribute.
	public void addItem(final Item item) {
		this.getItems().add(item);
		this.setItemCount(this.getItems().size());
	}

	public void removeItem(final Item item) {
		this.getItems().remove(item);
		this.setItemCount(this.getItems().size());
	}
	
	public void addTag(final Tag tag) {
		this.getTags().add(tag);
		this.setTagCount(this.getTags().size());
	}
	
	public void removeTag(final Tag tag) {
		this.getTags().remove(tag);
		this.setTagCount(this.getTags().size());
	}
}

