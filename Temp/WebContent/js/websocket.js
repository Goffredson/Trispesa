var ws;

function connect(username) {
	// var host = document.location.host;
	// var pathname = document.location.pathname;

	ws = new WebSocket("ws://localhost:8080/Trispesa/user/chat/" + username);

	ws.onmessage = function(event) {
		var log = document.getElementById("log");
		console.log(event.data);
		var message = JSON.parse(event.data);
		if (message.content === "DISCONNECTED") 
			$('#adminOnlyModal').modal('show');
		else {
			$('#adminOnlyModal').modal('hide');
			var receivedMessage = '<div class="incoming_msg">' + '<div class="incoming_msg_img">'
					+ '<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil"></div>' + '<div class="received_msg">'
					+ '<div class="received_withd_msg">' + '<p style="background-color: #e9b96e;">' + message.content + '</p>' + '<span class="time_date">' + message.date + " | "
					+ message.from + '</span>' + '</div>' + '</div>' + '</div>';
			$("#msgHistory").append(receivedMessage);
		}
		// log.innerHTML += message.from + " : " + message.content + "\n";
	};
}

function send(username) {
	var content = $("#sendBox").val();

	var json = JSON.stringify({
		"content" : content,
		"from" : username
	});
	var date = new Date();
	var currentTime = date.getHours().toString() + date.getMinutes().toString();
	var sentMessage = '<div class="outgoing_msg">' + '<div class="sent_msg">' + '<p>' + content
	'</p>' + '<span class="time_date">' + currentTime + ' | ' + username + '</span>' + '</div>' + '</div>';
	$("#sendBox").val("");
	$("#msgHistory").append(sentMessage);
	// log.innerHTML += username + " : " + content + "\n";

	ws.send(json);
}