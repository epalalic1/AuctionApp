export class UpdateUser {
    firstName: string;
    lastName: string;
    email: string;
    phone: string;
    constructor(
        name?: string,
        surname?: string,
        email?: string,
        phone?: string,
    ) {
        this.firstName = name as string;
        this.lastName = surname as string;
        this.email = email as string;
        this.phone = phone as string;
    }
}

