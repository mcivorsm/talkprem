

const socket = new WebSocket("/websocket");
const stompClient = Stomp.over(socket);

stompClient.connect({}, function(frame){
    console.log('Connected ' + frame);
    stompClient.subscribe('/chatroom/messages', function (response) {
        const message = response.body;
        displayMessage(message);
});
});

function sendMessage() {
    const messageInput = document.getElementById('messageInput');
    const message = messageInput.value.trim();

    if (message !== '') {
        stompClient.send('/app/sendM', {}, message); 
        messageInput.value = ''; 
    }
}


function displayMessage(message) {
    const messageLog = document.getElementById('messageLog');
    const messageElement = document.createElement('p');
    messageElement.textContent = message;
    messageLog.appendChild(messageElement);
}

window.onbeforeunload = () => {
    socket.close();
};

socket.onclose = (event) => {
    console.log('WebSocket closed:', event);
};