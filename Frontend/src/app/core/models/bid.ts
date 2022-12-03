export class Bid {
    id: number;
    amount: number;
    dateOfBid: Date;
    productId: number;

    constructor(
        id: number,
        amount: number,
        dateOfBid: Date,
        productId: number,
    ) {
        this.id = id;
        this.amount = amount;
        this.dateOfBid = dateOfBid;
        this.productId = productId;
    };
}
