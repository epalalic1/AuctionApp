export class WsNotification {
    message: string;
    userId: number;
    productId: number;
    status:boolean;

    constructor(
        message?:string,
        userId?:number,
        productId?:number,
        status?:boolean
    ) {
        this.message = message as string;
        this.userId = userId as number;
        this.productId = productId as number;
        this.status = status as boolean;
    }
    
}

