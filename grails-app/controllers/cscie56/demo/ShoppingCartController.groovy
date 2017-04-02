package cscie56.demo

class ShoppingCartController {

    ShoppingCartService shoppingCartService

    def index() { }

    def addToCart(ShoppingCart cart, item, Integer quantity) {
        if (cart) {
            shoppingCartService.addItemToCart(cart,item,quantity)
        }
    }
}
