var latestMsg;
var socket;
if (window.WebSocket) {
    socket = new WebSocket("ws://localhost:8080/myapp");
    socket.onmessage = function (event) {
        var chat = document.getElementById('chat');
        chat.innerHTML = chat.innerHTML + event.data + "<br />";
        latestMsg = JSON.parse(event.data);
        cleanUpOptions();
        createNewOptions();
        populateHeader();
    };
} else {
    alert("Your browser does not support Websockets. (Use Chrome)");
}

function send(message) {
    if (!window.WebSocket) {
        return false;
    }
    if (socket.readyState == WebSocket.OPEN) {
        socket.send(message);
    } else {
        alert("The socket is not open.");
    }
    return false;
}

function cleanUpOptions() {
    const optionsParent = document.getElementById("options");
    while (optionsParent.firstChild) {
        optionsParent.removeChild(optionsParent.firstChild);
    }
}
function createNewOptions() {

    const optionsParent = document.getElementById("options");
    for (let prop in latestMsg.plotEvent.options) {
        const btn = document.createElement("BUTTON");
        btn.innerText = latestMsg.childOptionsToChildOptionText[latestMsg.plotEvent.options[prop]]
        btn.onclick = function() {
            send(latestMsg.plotEvent.options[prop]);
        }
        optionsParent.appendChild(btn);
    }
}

function populateHeader() {
    const header = document.getElementById("currentPlotEvent");
    header.innerText =  latestMsg.plotEvent.eventName;

}
