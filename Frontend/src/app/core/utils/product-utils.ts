/*
 * Class Product contains all functions that we use to manipulate with names of products or similar
 * action like that 
 */

import { Bid } from "../models/bid";
import { Product } from "../models/product";

export class ProductUtils {

    /**
     * The method compare two strings compares two strings in such a way that it counts the number 
     * of common characters and after multiplying it by two, divides it by the sum of their lengths.
     * @param first is first string that we receive to compare
     * @param second is second string that we receive to compare
     * @returns a number in the range from 0 to 1 where 0 indicates that the strings are not the same
     *  at all and 1 returns the greatest match 
     */

    static compareTwoStrings(first: string, second: string) {
        first = first.toLowerCase();
        second = second.toLowerCase();
        let count = 0;
        for (var i = 0; i < first.length; i++) {
            if (first.includes(second[i])) {
                count++;
            }
        }
        let final = ((2 * count) / (first.length + second.length))
        return final
    }

    /**
     * A method that returns the highest bid for a given list of bids
     * @param bidOfProduct list of bids for product
     * @returns highest bid 
     */

    static findHighestBid(bidOfProduct: Bid[]) {
        let highetBid = Math.max.apply(Math, bidOfProduct.map(function (bid) { return bid.amount; }))
        return highetBid != -Infinity ? highetBid : 0;
    }

    /**
     * A method that returns how many days or hours are left before 
     * the bidding deadline for that product expires
     * @param product  for which we want to know how much time is left
     * @returns string that contains information that tells how much time is left
     */

    static findTimeLeftForProduct(product: Product) {
        let timeLeft = "";
        if (product != null && product != undefined) {
            let date: any = new Date();
            let parsed = Date.parse(product?.endDate.toString());
            let diffInMs = Math.abs(parsed - date);
            timeLeft = Number((diffInMs / (1000 * 60 * 60 * 24))).toFixed(0);
            if (Number(timeLeft) <= 0) {
                var diff = Math.abs(product.endDate.getTime() - date.getTime()) / 3600000;
                return diff.toString() + " hours";
            }
        }
        return timeLeft + " days";
    }

    /**
     * A method that returns how many days or hours are left until the bidding deadline for that 
     * product expires based on the id of the product
     * @param id of product
     * @param products list of products
     * @returns string that contains information that tells how much time is left
     */

    static findTimeLeftForProductWithId(id: Number, products: Product[]) {
        let product = products.find((product) => product.id == id) as Product;
        return this.findTimeLeftForProduct(product);
    }

}
