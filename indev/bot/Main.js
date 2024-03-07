/**
 * In'Dev - Console log types.
 *      - S >>> Setup.
 *      - R >>> Response.
 *      - W >>> Warning.
 *      - E >>> Error.
 */
 
//npm modules ==> discord.js - fs - path - node-fetch - random-words

//imports
const { Client, GatewayIntentBits, Collection, Events, EmbedBuilder } = require("discord.js");
const fs = require("fs");
const path = require("path");

const { token, clientId, guildId } = require('./config.json');
const commandsPath = path.join(__dirname, 'commands');
const commandFiles = fs.readdirSync(commandsPath).filter(file => file.endsWith('.js'));
const deployCommands = require("./Loaders/deployCommands");

//Client init.
const client = new Client({     //Assign intents to the client as well as intialise it.
    intents: [
        GatewayIntentBits.Guilds,
        GatewayIntentBits.GuildMessages,
        GatewayIntentBits.MessageContent,
        GatewayIntentBits.GuildPresences
    ]
});

client.login(token);        //Assign the token to the client for login.

//Deploy slash commands onto the server.
deployCommands(clientId, guildId, token, commandFiles);
console.log(`In'DevS >>> Slash command succesfully deployed to server.`)

//Commands init for the client (bot).
client.commands = new Collection();     //Create a collection to store all the commands inside of the client object.

for (const file of commandFiles) {
    const filePath = path.join(commandsPath, file);
    const command = require(filePath);

    if ('data' in command && 'execute' in command) {    //Add command to collection if it has the needed properties.
        console.log(`In'DevS >>> Attempting to load command module ${file} ...`);
        client.commands.set(command.data.name, command);
        console.log(`In'DevS >>> The command module ${file} has been loaded succesfully.`);
    }else{      //If the command doesn't have the needed properties ignore.
        console.log(`In'DevW >>> The command module ${file} at ${filePath} is missing "data" or "execute" properties !`);
    }
}

//Client start.
client.on("ready", () => {      //Startup log message.
    console.log(`In'DevS >>> ${client.user.tag} is now Online !`);
})



//Client SlashCommand interaction response.
client.on(Events.InteractionCreate, async interaction => {
	if (!interaction.isChatInputCommand()) return;      //If the interaction is a Command submited by a user through the chat input  don't ignore.

	const command = interaction.client.commands.get(interaction.commandName);   //Match interation Command to client command, null (false) if no match is found.

	if (!command) {     //Check if the command is valid.
		console.log(`In'DevE >>> No command matching ${interaction.commandName} was found for ${interaction.user.id}.`);
		return;
	}
	
	//let adminBool;
	//interaction.guild.members.fetch(interaction.user).then(member => member.permissions.has("ADMINISTRATOR")).then(admin => adminBool = admin);
	
	try {       //Attempt to execute the Command.
		await command.execute(interaction);
        console.log(`In'DevR >>> Replied to ${interaction.user.id} for "${interaction.commandName}" Command.`);
	} catch (error) {       //Return an error message if something went wrong.
		console.error(error);
		await interaction.reply({
			embeds: [new EmbedBuilder()
			.setColor('DarkRed')
			.setDescription('There was an error while executing this command !')
			],
			ephemeral: true
		});
	}
});


//Client ModalSubmit interaction response.
client.on(Events.InteractionCreate, async interaction =>{
	if (!interaction.isModalSubmit()) return;		//If the interaction is a Modal submited by a user don't ignore.
	
	const command = interaction.client.commands.get(interaction.customId);	//Match interaction ModalSubmit to client command, null (false) if no match is found.
	
	if (!command) {     //Check if the command is valid.
		console.log(`In'DevE >>> No ModalSubmit response matching ${interaction.customId} was found for ${interaction.user.id}.`);
		return;
	}
	
	//let adminBool;
	//interaction.guild.members.fetch(interaction.user).then(member => member.permissions.has("ADMINISTRATOR")).then(admin => adminBool = admin);
	
	try {       //Attempt to execute the response to the ModalSubmit.
		await command.respond(interaction);
        console.log(`In'DevR >>> Replied to ${interaction.user.id} for "${interaction.customId}" ModalSubmit.`);
	} catch (error) {       //Return an error message if something went wrong.
		console.error(error);
		await interaction.reply({
			embeds: [new EmbedBuilder()
			.setColor('DarkRed')
			.setDescription('There was an error while executing this command !')
			],
			ephemeral: true
		});
	}
});
