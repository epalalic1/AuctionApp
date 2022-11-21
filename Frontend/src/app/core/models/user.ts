export class User {
    id:number;
    name:string;
    surname:string;
    email:string;
    password:string;
    phone:string;
    gender:string;
    dateOfBirth:string;
    roleId:number;

<<<<<<< HEAD
    constructor(
        id:number,
        name:string,
        surname:string,
        email:string,
        password:string,
        phone:string,
        gender:string, 
        dateOfBirth:string,
        roleId:number){
=======
    constructor(id:number,name:string,surname:string,email:string,password:string,phone:string,
        gender:string, dateOfBirth:string,roleId:number){
>>>>>>> 8f14b1d9 (First version of landing page)
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.email = email;
            this.password = password;
            this.phone = phone;
            this.gender = gender;
            this.dateOfBirth = dateOfBirth;
            this.roleId = roleId;

        }
}
