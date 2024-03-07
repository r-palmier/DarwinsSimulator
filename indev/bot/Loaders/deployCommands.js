//imports
const { REST, Routes } = require('discord.js');
const fs = require('fs');

module.exports = async (clientId, guildId, token, commandFiles) => {

	const commands = [];

//Grabs the SlashCommandBuilder#toJSON() output of each command's data for deployment.
for (const file of commandFiles) {
	const command = require(`../commands/${file}`);
	commands.push(command.data.toJSON());
}

//Constructs and prepares an instance of the REST module.
const rest = new REST({ version: '10' }).setToken(token);

//Deployment of the commands to the server.
(async () => {
	try {
		console.log(`In'DevS >>> Started refreshing ${commands.length} application (/) commands.`);

		//The put method is used to fully refresh all commands in the guild with the current set
		const data = await rest.put(
			Routes.applicationGuildCommands(clientId, guildId),
			{ body: commands },
		);

		console.log(`In'DevS >>> Successfully reloaded ${data.length} application (/) commands.`);
	} catch (error) {
		//If an error occurs catch it :D .
		console.error(error);
	}
})();	

}

