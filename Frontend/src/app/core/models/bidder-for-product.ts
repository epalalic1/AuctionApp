export class BidderForProduct {
    name: string;
    dateOfBid: Date;
    amount: number;

    constructor(
        name?: string,
        dateOfBid?: Date,
        amount?: number,
    ) {
        this.name = name as string,
        this.dateOfBid = dateOfBid as Date,
        this.amount = amount as number
    };
}
