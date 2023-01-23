import { validateArgCount } from "@firebase/util";

export class Validation {
    
    /**
     * A method that checks whether the string representing the email
     * contains the required characters
     * @param email that we need to check
     * @returns boolean depending on whether the email contains or does not contain
     * the required characters
     */

    static checkIfEmailIsValid(email : string) {
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

    static chackIfPasswordIsValid (password : string) {
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

    static checkIfPhoneIsValid (phone : string) {
        let phoneRegex = /^\d+$/;
        return !phone.match(phoneRegex) || phone[0] != "0" || phone.length != 9 ? false : true
    }
}