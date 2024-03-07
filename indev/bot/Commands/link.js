const { SlashCommandBuilder, ActionRowBuilder, ModalBuilder, TextInputBuilder, TextInputStyle, EmbedBuilder } = require('discord.js');
var DiscordMessage = require('../Class/DiscordMessage');
const postMessage = require('../Functions/postMessage');

module.exports = {
	data: new SlashCommandBuilder()
		.setName('link')
		.setDescription('A link to submit.'),


	//What should be done if the interaction was a Command by a user in chat input.
	async execute(interaction) {
		
		//Create the modal object.
		
		const modal = new ModalBuilder()
			.setCustomId(interaction.commandName)		//customId should be the same name as the command.	
			.setTitle('Link post form.');				//Since we'll use this to call this CommandFile in order to handle the response.
		
		//Build the initial text zones.
		const fields = {
			
			title: new TextInputBuilder()		//First input of text. The title of the post.
				.setCustomId('title')
				.setLabel('Title of the post.')
				.setStyle(TextInputStyle.Short)
				.setMinLength(10)
				.setMaxLength(60)
				.setRequired(true),
		
			link: new TextInputBuilder()		//Second input of text. The link of the post.
				.setCustomId('link')
				.setLabel('Link to the website.')
				.setStyle(TextInputStyle.Short)
				.setRequired(true),
			
			tags: new TextInputBuilder()		//Third input of text. The tags of the post.
				.setCustomId('tags')
				.setLabel('Tags. [Seperate with spaces]')
				.setStyle(TextInputStyle.Paragraph)
				.setRequired(true),
			
			description: new TextInputBuilder()		//Fourth input of text. The description of the post.
				.setCustomId('description')
				.setLabel('Description of the website.')
				.setStyle(TextInputStyle.Paragraph)
				.setRequired(true)
		};
			
		//Assemble the text zones into ActionRows.
		
		const firstActionRow = new ActionRowBuilder().addComponents(fields.title);
		const secondActionRow = new ActionRowBuilder().addComponents(fields.link);
		const thirdActionRow = new ActionRowBuilder().addComponents(fields.tags);
		const fourthActionRow = new ActionRowBuilder().addComponents(fields.description);
		
		//Add the ActionsRows into a modal form.
		
		modal.addComponents(firstActionRow, secondActionRow, thirdActionRow, fourthActionRow);
		
		//Send the modal to the user.
		
		await interaction.showModal(modal);
		
	},
	
	
	//What should be done if the interaction was a ModalSubmit that the user responded to using the Command.
	async respond(interaction) {
		
		//Retrieve the data from the modal form.
		const title = interaction.fields.getTextInputValue('title');
		const link = interaction.fields.getTextInputValue('link');
		const tags = interaction.fields.getTextInputValue('tags').split(' ');
		const description = interaction.fields.getTextInputValue('description');
		
		const user = interaction.user;
		
		//Prepare text for the embed.
		let embedTags = '';
		for(const tag of tags){
			if(embedTags !== '') embedTags += ' | ';
			embedTags += tag;
		}
		
		//Build an Embed for the user to see what they submitted.
		let embed;
		try{
			embed = new EmbedBuilder()
				.setColor('Green')
				.setAuthor({ name: `In'Dev`, iconURL: `https://cdn.discordapp.com/attachments/1027155649223729210/1047527511711563887/index.png`})
				.setThumbnail('https://s3-eu-west-1.amazonaws.com/assets.atout-on-line.com/images/ingenieur/Logos_Ecoles/2018_2021/intech_300.jpg')
				.setTitle(link)
				.setURL(link)
				.setFields(
					{ name: 'Title :', value: `${title}`},
					{ name: 'Tags :', value: `${embedTags}` },
					{ name: 'Description :', value: `${description}`}
				)
				.setFooter({ text: `${user.tag}`, iconURL: `${user.displayAvatarURL()}` })
				.setTimestamp();
		}catch(error){
			console.error(error);
			await interaction.reply({
				embeds: [new EmbedBuilder()
					.setColor('DarkRed')
					.setDescription(`Something went wrong, maybe your URL link was invalid ?`)
					],
				ephemeral: true
			});
			return;
		}
		
		//Build a JSON to send to the API.
		let message = new DiscordMessage(
			title, 
			link, 
			tags, 
			true, 
			description, 
			user.tag, 
			user.id,
			null, 
			new Date().getUTCMilliseconds()
		);
		let json = JSON.stringify(message);
		console.log(json);
		try{
			postMessage(json); //Somehow still crashes the instance even though it should be caught by a try-catch . . .
			//When the api is not able to recieve. 
		}catch(error){
			await interaction.reply({
				embeds: [new EmbedBuilder()
					.setColor('DarkRed')
					.setDescription(`Unable to send the contents to the api.`)
					],
				ephemeral: true
			});
			return;
		}
		
		//Send to user data retrived.
		await interaction.reply({
			content: user.toString(),
			embeds: [embed],
			components: [],
			ephemeral: false
		})
			.then( sent => {
			console.log("<--->");
			console.log(sent.id);
			console.log("<--->");
		});
		
	}
}