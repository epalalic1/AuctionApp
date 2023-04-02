export class User {
    id: number;
    name: string;
    surname: string;
    email: string;
    password: string;
    phone: string;
    gender: string;
    dateOfBirth: string;
    roleId: number;

    constructor(
        id?: number,
        name?: string,
        surname?: string,
        email?: string,
        password?: string,
        phone?: string,
        gender?: string,
        dateOfBirth?: string,
        roleId?: number
    ) {
        this.id = id as number;
        this.name = name as string;
        this.surname = surname as string;
        this.email = email as string;
        this.password = password as string;
        this.phone = phone as string;
        this.gender = gender as string;
        this.dateOfBirth = dateOfBirth as string;
        this.roleId = roleId as number;
    }

 
}
