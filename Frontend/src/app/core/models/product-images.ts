export class ProductImages {
    productId?: number;
    images?: string;

    constructor(productId: number, images: string) {
        this.productId = productId as number;
        this.images = images as string;
    }
}
