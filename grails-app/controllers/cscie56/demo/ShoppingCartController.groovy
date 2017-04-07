package cscie56.demo

import grails.plugin.springsecurity.annotation.Secured

class ShoppingCartController {

    ShoppingCartService shoppingCartService

    @Secured([Role.ROLE_USER,Role.ROLE_ADMIN,Role.ROLE_ANONYMOUS,"ROLE_TESTER"])
    def index() {
        render "It works!"
    }

    @Secured([Role.ROLE_USER,Role.ROLE_ADMIN])
    def addToCart(ShoppingCart cart, item, Integer quantity) {
        if (cart) {
            shoppingCartService.addItemToCart(cart,item,quantity)
        }
    }
}
