export class AddItem {
    name: string;
    category: string;
    subcategory: string;
    description: string;
    imageName: string;
    startPrice: number;
    startDate: Date;
    endDate: Date;

    constructor(
        name?: string,
        category?: string,
        subcategory?: string,
        description?: string,
        imageName?: string,
        startPrice?: number,
        startDate?: Date,
        endDate?: Date,
    ) {
        this.name = name as string,
            this.category = category as string,
            this.subcategory = subcategory as string,
            this.description = description as string,
            this.imageName = imageName as string,
            this.startPrice = startPrice as number,
            this.startDate = startDate as Date,
            this.endDate = endDate as Date
    };
}
