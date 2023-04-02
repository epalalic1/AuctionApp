export class PaymentRequest {
    curency: string;
    description: string;
    amount: number;
    stripeEmail: string;
    stripeToken: string;
    productId:number;

    constructor(
        curency?: string ,
        description?: string,
        amount?: number,
        stripeEmail?: string,
        stripeToken?: string,
        productId?:number
    ) {
        this.curency = curency as string;
        this.description = description as string;
        this.amount = amount as number;
        this.stripeEmail = stripeEmail as string;
        this.stripeToken = stripeToken as string;
        this.productId = productId as number;
    }
}

