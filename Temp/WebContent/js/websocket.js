var ws;

function connect(username) {

	// var host = document.location.host;
	// var pathname = document.location.pathname;

	ws = new WebSocket("ws://localhost:8080/Trispesa/user/chat/" + username);

	ws.onmessage = function(event) {
		var log = document.getElementById("log");
		console.log(event.data);
		var message = JSON.parse(event.data);
		if (message.content === "Disconnesso.")
			log.innerHTML = "Disconnesso.";
		else
			log.innerHTML += message.from + " : " + message.content + "\n";
	};
}

function send(username) {
	var content = document.getElementById("msg").value;
	var json = JSON.stringify({
		"content" : content,
		"from" : username
	});
	log.innerHTML += username + " : " + content + "\n";

	ws.send(json);
}