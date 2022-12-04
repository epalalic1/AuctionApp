export class Bid {
    id:number;
    amount:number;
    dateOfBid:Date;
    productId:number;
    userId:number;

    constructor(id:number,amount:number,dateOfBid:Date,productId:number,userId:number) {
        this.id = id;
        this.amount = amount;
        this.dateOfBid = dateOfBid;
        this.productId = productId;
        this.userId = userId;
    };
}
