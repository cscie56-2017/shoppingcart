package cscie56.demo

class LineItem {

    Object item
    Class itemClass
    Integer quantity

    static transients = ['extension']

    static constraints = {
        quantity min:0
    }

    Object getItem () {
        if (itemClass  && item) {
            return itemClass.get(item?.id)
        } else {
            return item
        }
    }

    Integer getExtension() {

        item?.price?:0 * quantity
    }
}
