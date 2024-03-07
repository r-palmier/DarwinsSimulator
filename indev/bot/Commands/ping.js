const { SlashCommandBuilder, EmbedBuilder } = require('discord.js');

module.exports = {
	data: new SlashCommandBuilder()
		.setName('ping')
		.setDescription('Replies with latency in ms.'),

	async execute(interaction) {
		await interaction.reply({
			embeds: [new EmbedBuilder()
			.setColor('Green')
			.setDescription(`Latency is ${interaction.client.ws.ping} ms.`)
			],
			ephemeral: true
		});
	},
};