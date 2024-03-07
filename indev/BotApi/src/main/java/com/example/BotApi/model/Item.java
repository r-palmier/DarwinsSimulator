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

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Class made to represent an item. It is the link that was shared by a user and all relevant data associated to it.
 * 
 * @author Gervais Pierre
 *
 */
@Entity
public class Item {
	
	//DB id value attribute.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="item_id")
	private int id;
	//Attributes.
	@Column(name="item_name")
	private String name;							//The title of the message. Gives an idea of what it is in one sentence or a few words.
    private String link;							//The link that the message will contain.
    
    @ManyToMany
    @JsonManagedReference							//Item is the Parent of the Item_Tag relationship.
    private Set<Tag> tags = new HashSet<Tag>();		//Association to tags existing in the database.
    
    private Boolean modal;							//A boolean to keep track of whether the message originated from a modal or a normal message that was caught.
    private String description;						//The description given by the user OR the content of the message if it wasn't a modal form.
    
    @ManyToOne
    @JsonManagedReference							//Item is the Parent of the Item_DiscordUser relationship.
    private DiscordUser discordUser;				//Association to the user who made the message existing in the database.
    
    private String messageId;						//The id of the message in discord to which this is associated.
    private Long time;								//The time attribute should be in milliseconds as a Long value. This will help with keeping the data compatible between any type of language in theory.
	
    @ManyToMany
    @JsonManagedReference							//Item is the Parent of the Item_Category relationship.
    private Set<Category> categories = new HashSet<Category>();	//Association to categories existing in the database.
    
    //Constructor.
    public Item() {
    	
    }
    
    public Item(final String name, final String link, final Set<Tag> tags,final Boolean modal, final String description,final DiscordUser discordUser, final String messageId, final Long time) {
    	this.setName(name);
    	this.setLink(link);
    	this.setTags(tags);
    	this.setModal(modal);
    	this.setDescription(description);
    	this.setDiscordUser(discordUser);
    	this.setMessageId(messageId);
    	this.setTime(time);
    }
    
    //Getter & Setter.
    public int getId() {
    	return this.id;
    }
    public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(final String link) {
		this.link = link;
	}
	public Set<Tag> getTags() {
		return this.tags;
	}
	private void setTags(final Set<Tag> tags) {
		this.tags = tags;
	}
	public Boolean getModal() {
		return modal;
	}
	public void setModal(final Boolean modal) {
		this.modal = modal;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}
	public DiscordUser getDiscordUser() {
		return this.discordUser;
	}
	public void setDiscordUser(final DiscordUser discordUser) {
		this.discordUser = discordUser;
	}
	public String getMessageId() {
		return messageId;
	}
	private void setMessageId(final String messageId) {
		this.messageId = messageId;
	}
	public Long getTime() {
		return time;
	}
	private void setTime(final Long time) {
		this.time = time;
	}
    public Set<Category> getCategories(){
    	return this.categories;
    }
	
	//Custom tags attribute.
	public void addTag(final Tag tag) {
		this.getTags().add(tag);
	}
	
	public void removeTag(final Tag tag) {
		this.getTags().remove(tag);
	}
	
	//Custom categories attribute.
	public void addCategory(final Category category) {
		this.getCategories().add(category);
	}
	
	public void removeCategory(final Category category) {
		this.getCategories().remove(category);
	}
}
