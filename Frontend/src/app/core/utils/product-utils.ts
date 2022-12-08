export class ProductUtils {

    static compareTwoStrings(first: string, second: string) {
        first = first.toLowerCase();
        second = first.toLowerCase();
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
