//imports
const fetch = (...args) => import('node-fetch').then(({ default: fetch }) => fetch(...args));
const { apiIP } = require('../config.json');

module.exports = async (json) => {
		fetch(`${apiIP}/addMessage`, {	//Take the base adresse and call the proper endpoint with it.
       	 	method: 'POST', 
        	headers:{'content-type': 'application/json'},
        	body: json
    	})
    	.then(res => res.text()) // Wait for json response
    	.then(text => console.log(`In'DevR >>> Message sent to api successfuly. Response : ${text}`));
}