import { Router } from "@angular/router";
import { Stomp } from "@stomp/stompjs";
import * as SockJS from "sockjs-client";
import { AppComponent } from "src/app/app.component";
/*declare var SockJS: new (arg0: string) => any;
declare var Stomp: { over: (arg0: any) => any; };*/


/*import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';*/

export class WebSocketAPI {
    topic: string = "/user/queue/notification";
    stompClient: any;
    appComponent: AppComponent;
    constructor(appComponent: AppComponent) {
        this.appComponent = appComponent;
    }
    _connect() {
        console.log("Initialize WebSocket Connection");
        let ws = new SockJS('http://localhost:4200/ws');
        console.log(ws);
        this.stompClient = Stomp.over(ws);
        const _this = this;
        this.stompClient.connect({}, function (frame :any) {
            console.log(frame);
            _this.stompClient.subscribe(_this.topic, (sdkEvent: any) => {
                _this.onMessageReceived(sdkEvent);
            });
        });
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
    _send(message: any) {
        console.log("calling logout api via web socket");
        this.stompClient.send("/auctionapp/message", {}, JSON.stringify(message));
    }

    onMessageReceived(message: any) {
        console.log("Message Recieved from Server :: " + message);
        this.appComponent.handleMessage(JSON.stringify(message.body));
    }
}
