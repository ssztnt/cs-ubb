// websocket.js
import SockJS from 'sockjs-client';
import { Client } from '@stomp/stompjs';

const socketUrl = 'http://localhost:8080/ws';

export function connectToWebSocket(onMessageCallback) {
    const client = new Client({
        webSocketFactory: () => new SockJS(socketUrl),
        reconnectDelay: 5000,
        onConnect: () => {
            console.log("✅ WebSocket connected");
            client.subscribe("/topic/probe", (message) => {
                const payload = JSON.parse(message.body);
                onMessageCallback(payload);
            });
        },
    });

    client.activate();
}