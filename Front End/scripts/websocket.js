let ws;
(async function startWorkflow() {
    console.log('Starting workflow');
    await setSessionCookie(); // Ensure the cookie is set
    console.log('session cookie set');
    initiateWebSocketConnection(); // Initiate WebSocket connection
   
})();
async function setSessionCookie() {
    try {
        const response = await fetch('http://localhost:8080/set-cookie', {
            method: 'GET',
            credentials: 'include' // Ensures cookies are included in the request/response
        });

        if (response.ok) {
            console.log('Session cookie set successfully.');
        } else {
            console.error('Failed to set session cookie.');
            throw new Error('Cookie setup failed');
        }
    } catch (error) {
        console.error('Error during cookie setup:', error);
    }
}

// Function to initiate the WebSocket connection
     function initiateWebSocketConnection() {
        console.log('Attempting WebSocket connection...');
     ws = new WebSocket('ws://localhost:8080/websocket');

    ws.onopen = () => {
        console.log('WebSocket connection established.');
    };

    ws.onmessage = (event) => {
        console.log('Message from server:', event.data);
    };

    ws.onclose = () => {
        console.log('WebSocket connection closed.');
    };

    ws.onerror = (error) => {
        console.error('WebSocket error:', error);
    };
}

// Ensure cookie is set before connecting to WebSocket




function sendMessage() {
    const messageInput = document.getElementById('message');
    const message = messageInput.value.trim();

    if (message !== '') {
        ws.send(message); 
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
           fetch(`http://localhost:8080/changeAlias/${alias}`, {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(alias)

           })
    .then(response => response.text())
    .then(data => console.log('Success:', data))
    .catch(error => console.error('Error:', error));

   

     }
    
    }
