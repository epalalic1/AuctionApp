import { Injectable } from "@angular/core";
import { Client, Stomp } from "@stomp/stompjs";
import * as SockJS from "sockjs-client";
import { AppComponent } from "src/app/app.component";
import { ApiService } from "../services/api.service";
import { User } from "./user";
import { WsNotification } from "./ws-notification";

@Injectable({
    providedIn: 'root',
})


export class WebSocketAPI {

    listOfWsNotifications: WsNotification[] = [];
    stompClient: any;
    constructor(private appComponent: AppComponent, private apiServis: ApiService) {
    }
    _connect() {
        console.log("Initialize WebSocket Connection");
        let ws = new SockJS('http://localhost:8080/ws');
        this.stompClient = new Client();
        this.stompClient = Stomp.over(ws);
        const _this = this;
        this.apiServis.getCurrentUser().subscribe(user => {
            let user1 = <User>JSON.parse(JSON.stringify(user));
            let route = "/specific/" + user1.email
            this.stompClient.connect({}, function (frame: any) {
                _this.stompClient.subscribe(route, (sdkEvent: any) => {
                    console.log(sdkEvent.body);
                });
            });
        })

    };


    _disconnect() {
        if (this.stompClient !== null) {
            this.stompClient.disconnect();
        }
        console.log("Disconnected");
    }
    errorCallBack(error: any) {
        console.log("errorCallBack -> " + error)
        setTimeout(() => {
            this._connect();
        }, 5000);
    }

    /**
     * Send message to sever via web socket
     * @param {*} message 
     */

    _sendPrivate(wsNotification: WsNotification) {
        this.stompClient?.send('/auctionapp/private', {}, JSON.stringify({ message: wsNotification.message, userId: wsNotification.userId, productId: wsNotification.productId, status: wsNotification.status }));
    }
}
