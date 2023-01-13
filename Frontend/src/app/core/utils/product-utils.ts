/*
 * Class Product contains all functions that we use to manipulate with names of products or similar
 * action like that
 */

import { Bid } from "../models/bid";
import { Product } from "../models/product";
import { ProductImages } from "../models/product-images";

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
            date = Date.parse(date.toString())
            let parsed = Date.parse(product?.endDate.toString());
            let diffInMs = parsed - date;
            timeLeft = Number((diffInMs / (1000 * 60 * 60 * 24))).toFixed(0);
            if (Number(timeLeft) <= 86400 && Number(timeLeft) > 0) {
                var diff = diffInMs / 3600000;
            }
            else {
                timeLeft = "0";
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

    /**
     * A method that returns a list of products after we have found matching images
     * for them
     */

    static productsWithListOfImages(listOfProducts: Product[], productImages: ProductImages[]) {
        let products =listOfProducts.map((product: Product) => {
          let listOfProductImag = productImages.filter((item) => item.productId == product.id);
          product.imageName.splice(0);
          listOfProductImag.map((productImg: any) => {
            product.imageName.push(productImg.images);
          })
          return product;
        });
        return products;
    }

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

}
