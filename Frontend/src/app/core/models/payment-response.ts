export class PaymentResponse {
    clientSecret:string;

    constructor(clientSecret?:string){
        this.clientSecret = clientSecret as string;
    }
}
