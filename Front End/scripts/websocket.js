

const socket = new WebSocket("ws://localhost:8080/websocket");

socket.onopen = function () {
    console.log("WebSocket connection established.");
};

socket.onmessage = function (event) {
    console.log("Message received from server:", event.data);
    displayMessage(event.data);
};

socket.onclose = function (event) {
    console.log("WebSocket connection closed:", event);
};

function sendMessage() {
    const messageInput = document.getElementById('message');
    const message = messageInput.value.trim();

    if (message !== '') {
        socket.send(message); 
        messageInput.value = '';
    }
}

function displayMessage(message) {
    const messageLog = document.getElementById('messagesLog');
    if (!messageLog) {
        console.error("messagesLog div not found in the DOM!");
        return;
    }
    const messageElement = document.createElement('p');
    messageElement.textContent = message;
    messageLog.appendChild(messageElement);
}

function changeAlias(){
    var alias = document.getElementById('username').value;
    alias = alias.trim();
    if(alias!=null && alias.length<10 && alias.length> 1){
        console.log("Enter API fetch");
           fetch('http://localhost:8080/changeAlias/{aliasName}', {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(alias)

           })
    .then(response => response.text())
    .then(data => console.log('Success:', data))
    .catch(error => console.error('Error:', error));

   

     }
    
    }
