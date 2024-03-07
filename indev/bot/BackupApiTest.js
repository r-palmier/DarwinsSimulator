const fakeInteraction = require('./Class/fakeInteractionObject');
const test = require('./Commands/test');

let interaction = new fakeInteraction("728964487406682112", "ExtraPerry_Testing_Alt#1878");

test.execute(interaction);