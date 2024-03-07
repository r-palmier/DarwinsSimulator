//Message.js
module.exports = 
		//Class - This class is only needed to store specified information and then serialise it.
		class DiscordMessage {
			//Constructor
			constructor(title, link, tags, modal, description, userTag, userId, messageId, time){
				//Attributes.
				this.title = title;					//The title of the message. Gives an idea of what it is in one sentence or a few words.
				this.link = link;					//The link that the message will contain.
				this.tags = tags;					//The tags used to describe the contents of the message for archiving purposes.
				this.modal = modal;					//A boolean to keep track of whether the message originated from a modal or a normal message that was caught.
				this.description = description;		//The description given by the user OR the content of the message if it wasn't a modal form.
				this.userTag = userTag;				//The tag of the discord user by which the associated user id should be called.
				this.userId = userId;				//The id of the discord user to which this is associated.
				this.messageId = messageId;			//The id of the message in discord to which this is associated.
				this.time = time;					//The time attribute should be in milliseconds as a Long value. This will help with keeping the data compatible between any type of language in theory.
			}
}

/**
	Context ==>
	
	Discord Snowflake : https://discord.js.org/#/docs/discord.js/main/typedef/Snowflake
	Date : https://developer.mozilla.org/fr/docs/Web/JavaScript/Reference/Global_Objects/Date
	TimeStamp : https://docs.oracle.com/javase/8/docs/api/java/sql/Timestamp.html
*/