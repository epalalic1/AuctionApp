export class ItemInTable {
    name: string;
    timeLeft: string;
    userBid: number;
    numberofBids: number;
    highestBid: number;

    constructor(
        name: string,
        timeLeft: string,
        userBid: number,
        numberofBids: number,
        highestBid: number
    ) {
        this.name = name;
        this.timeLeft = timeLeft;
        this.userBid = userBid;
        this.numberofBids = numberofBids;
        this.highestBid = highestBid;
    };
}
