package com.example.BotApi.model.Contract;

import java.util.Date;

/**
 * Class used to standardize the information transaction between the discord bot and the api.
 * 
 * @author Gervais Pierre
 *
 */
public class DiscordMessage {
	
	//Attributes.
    private String title;		//The title of the message. Gives an idea of what it is in one sentence or a few words.
    private String link;		//The link that the message will contain.
    private String[] tags;		//The tags used to describe the contents of the message for archiving purposes.
    private Boolean modal;		//A boolean to keep track of whether the message originated from a modal or a normal message that was caught.
    private String description;	//The description given by the user OR the content of the message if it wasn't a modal form.
    private String userTag;		//The tag of the discord user by which the associated user id should be called.
    private String userId;		//The id of the discord user to which this is associated.
    private String messageId;	//The id of the message in discord to which this is associated.
    private Long time;			//The time attribute should be in milliseconds as a Long value. This will help with keeping the data compatible between any type of language in theory.
    
    //Constructors.
    public DiscordMessage() {
   
    }
    
    public DiscordMessage(final String title, final String link, final String[] tags, final Boolean modal, final String description,final String userTag, final String userId, final String messageId, final Long time) {
    	this.setTitle(title);
    	this.setLink(link);
    	this.setTags(tags);
    	this.setModal(modal);
    	this.setDescription(description);
    	this.setUserTag(userTag);
    	this.setUserId(userId);
    	this.setMessageId(messageId);
    	this.setTime(time);
    }
    
    //Getter & Setters.
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	private void setLink(String link) {
		this.link = link;
	}
	public String[] getTags() {
		return tags;
	}
	private void setTags(String[] tags) {
		this.tags = tags;
	}
	public Boolean isModal() {
		return modal;
	}
	private void setModal(Boolean modal) {
		this.modal = modal;
	}
	public String getDescription() {
		return description;
	}
	private void setDescription(String description) {
		this.description = description;
	}
	public String getUserTag() {
		return userTag;
	}
	private void setUserTag(String userTag) {
		this.userTag = userTag;
	}
	public String getUserId() {
		return userId;
	}
	private void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMessageId() {
		return messageId;
	}
	private void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public Long getTime() {
		return time;
	}
	private void setTime(Long time) {
		if (time == null) {
			time = new Date().getTime();
		}
		
		this.time = time;
	}
	
}