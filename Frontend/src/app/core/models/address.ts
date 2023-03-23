export class Address {
    id: number;
    country: string;
    city: string;
    postcode: string;
    streetName: string;
    streetNumber: string;

    constructor(
        id?: number,
        country?: string,
        city?: string,
        postcode?: string,
        streetName?: string,
        streetNumber?: string
    ) {
        this.id = id as number,
        this.country = country as string, 
        this.city = city as string,
        this.postcode =  postcode as string,
        this.streetName = streetName as string,
        this.streetNumber = streetNumber as string
    };
}

