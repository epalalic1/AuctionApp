import * as CryptoJS from 'crypto-js';
import { environment } from 'src/environments/environments';

/**
 * A class that contains methods used for encryption and description of the token
 * that we store in local storage during user login/registration.
 */

export class SecurityUtils {

  static key = environment.localStorageSecurity.key;

  /**
   * The method used to get the token saved in local storage
   * @param key whose item we want to find
   * @returns item that we have decrypted
   */

  static getData(key: string) {
    let data = localStorage.getItem(key) || "";
    return this.decrypt(data);
  }

  /**
   * A method used for encryption
   * @param txt string we want to encrypt
   * @returns encrypted string
   */

  static encrypt(txt: string): string {
    return CryptoJS.AES.encrypt(txt, this.key).toString();
  }

  /**
   * The method used for decryption
   * @param txtToDecrypt string we want to decrypt
   * @returns decrypted string
   */

  static decrypt(txtToDecrypt: string) {
    return CryptoJS.AES.decrypt(txtToDecrypt, this.key).toString(CryptoJS.enc.Utf8);
  }
}

