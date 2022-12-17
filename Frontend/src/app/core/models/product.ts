export class Product {
    id: number;
    name: string;
    dateOfArriving: Date;
    endDate: Date;
    startPrice: number;
    details: string;
    status: boolean;
    price: number;
    subcategoryId: number;
    userId: number;
    imageName: string [];
    categoryId:number;

    constructor(
        id: number,
        name: string,
        dateOfArriving: Date,
        endDate: Date,
        startPrice: number,
        details: string,
        status: boolean,
        price: number,
        subcategoryId: number,
        userId: number,
        imageName: string [],
        categoryId:number
    ) {
        this.id = id;
        this.name = name;
        this.dateOfArriving = dateOfArriving;
        this.endDate = endDate;
        this.startPrice = startPrice;
        this.details = details;
        this.status = status;
        this.price = price;
        this.subcategoryId = subcategoryId;
        this.userId = userId;
        this.imageName = imageName;
        this.categoryId = categoryId;
    }

}
