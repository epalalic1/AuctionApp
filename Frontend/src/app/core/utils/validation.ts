import { ComponentFactoryResolver } from "@angular/core";
import { validateArgCount } from "@firebase/util";

export class Validation {

    /**
     * A method that checks whether the string representing the email
     * contains the required characters
     * @param email that we need to check
     * @returns boolean depending on whether the email contains or does not contain
     * the required characters
     */

    static checkIfEmailIsValid(email: string) {
        let validRegexEmail = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
        return email.match(validRegexEmail);
    }

    /**
     * A method that checks whether the string representing the password
     * contains the required characters
     * @param password  that we need to check
     * @returns boolean depending on whether the password contains or does not contain
     * the required characters
     */

    static chackIfPasswordIsValid(password: string) {
        let validRegexPassword = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W)/;
        return password.match(validRegexPassword);
    }

    /**
     * A method that checks whether the string representing the phone
     * contains the required characters
     * @param phone that we need to check
     * @returns boolean depending on whether the phone contains or does not contain
     * the required characters
     */

    static checkIfPhoneIsValid(phone: string) {
        var re = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im
        return !phone.match(re) ? false : true
    }

    /**
     * A method that checks whether the string representing the name or surname
     * contains the required characters
     * @param text that we need to check
     * @returns boolean depending on whether the name or surname contains or does not contain
     * the required characters
     */

    static checkIsNameSurnameValid(text: string) {
        let nameSurnameRegex = /^[a-zA-Z]*$/;
        return !text.match(nameSurnameRegex) || text.length < 3 || text[0] == text[0].toLocaleLowerCase() ? false : true
    }

    /**
     * A method that checks if the first date is in the past and if the second date is after the first
     * @param startDate string representing the first date
     * @param endDate string representing the second date
     * @returns boolean depending on whether the dates are valid
     */

    static checkAreDatesValid(startDate: string, endDate: string) {
        let firstDate = startDate as unknown as Date;
        let secondDate = endDate as unknown as Date;
        let currentDate = new Date().toISOString().slice(0, 10) as unknown as Date;
        return firstDate < currentDate || secondDate < firstDate || secondDate == undefined || firstDate == undefined ? false : true;
    }

    static validateBirhtday(day: string, month: number, year: string) {
        if (day != "" && month != undefined && year != "") {
            let listofDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
            if (month == 1 || month > 2) {
                return Number(day) > listofDays[month-1] ? false : true;
            }
            else if (month == 2) {
                let leapYear = false;
                if ((!(Number(year) % 4) && Number(year) % 100) || !(Number(year) % 400)) {
                    leapYear = true;
                }
                if ((leapYear == false) && (Number(day) >= 29)) {
                    console.log("Ovdje smo ipak");
                    return false
                }
                if ((leapYear == true) && (Number(day) > 29)) {
                        return false;
                }
                return true;

            }
        }
        return false;
    }
}
