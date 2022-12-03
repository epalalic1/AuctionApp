export class Category {
    id: number;
    name: string;
    subcategory: string[];
    isChecked: boolean | false;

    constructor(
        id: number,
        name: string,
        subcategory: string[],
        isChecked: boolean
    ) {
        this.id = id;
        this.name = name;
        this.subcategory = subcategory;
        this.isChecked = isChecked;
    }
}