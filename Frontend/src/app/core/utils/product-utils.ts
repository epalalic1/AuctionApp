/*
 * Class Product contains all functions that we use to manipulate with names of products or similar
 * action like that 
 */

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
}
