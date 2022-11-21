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

    constructor(id:number,name:string,surname:string,email:string,password:string,phone:string,
        gender:string, dateOfBirth:string,roleId:number){
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
