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
        id?: number,
        name?: string,
        dateOfArriving?: Date,
        endDate?: Date,
        startPrice?: number,
        details?: string,
        status?: boolean,
        price?: number,
        subcategoryId?: number,
        userId?: number,
        imageName?: string [],
        categoryId?:number
    ) {
        this.id = id as number;
        this.name = name as string;
        this.dateOfArriving = dateOfArriving as Date;
        this.endDate = endDate as Date;
        this.startPrice = startPrice as number;
        this.details = details as string;
        this.status = status as boolean;
        this.price = price as number;
        this.subcategoryId = subcategoryId as number;
        this.userId = userId as number;
        this.imageName = imageName as string [];
        this.categoryId = categoryId as number;
    }

}
