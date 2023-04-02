export class ItemInTable {
    name: string;
    timeLeft: string;
    userBid: number;
    numberofBids: number;
    highestBid: number;
    images: string[]

    constructor(
        name?: string,
        timeLeft?: string,
        userBid?: number,
        numberofBids?: number,
        highestBid?: number,
        images?: string[]
    ) {
        this.name = name as string;
        this.timeLeft = timeLeft as string;
        this.userBid = userBid as number;
        this.numberofBids = numberofBids as number;
        this.highestBid = highestBid as number;
        this.images = images as string[];
    };
}
