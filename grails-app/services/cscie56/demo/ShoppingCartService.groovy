package cscie56.demo

import grails.transaction.Transactional

@Transactional
class ShoppingCartService {

    def grailsApplication, springSecurityService

    def addItemToCart(ShoppingCart cart, item, Integer quantity) {

        LineItem alreadyInCart = cart.lineItems.find { it.item == item }
        if (alreadyInCart) {
            alreadyInCart.quantity += quantity
        } else {
            LineItem newLineItem = new LineItem(item: item, quantity:quantity)
            if (grailsApplication.isDomainClass(item.class)) {
                newLineItem.itemClass = item.class
            }
            cart.addToLineItems( newLineItem )
        }
        cart.grandTotal += item.price * quantity

        cart.save(flush:true)
    }

    def updateItemQuantityInCart(ShoppingCart cart, item, Integer quantity) {

        LineItem alreadyInCart = cart.lineItems.find { it.item == item }
        if (quantity > 0) {
            if (alreadyInCart) {
                alreadyInCart.quantity = quantity
            } else {
                LineItem newLineItem = new LineItem(item: item, quantity: quantity)
                if (grailsApplication.isDomainClass(item.class)) {
                    newLineItem.itemClass = item.class
                }
                cart.addToLineItems(newLineItem)
            }
        } else if (alreadyInCart) {
            cart.lineItems.remove(alreadyInCart)
        }
        cart.grandTotal += item.price * quantity

        cart.save(flush:true)
    }

    ShoppingCart createCart(){
        ShoppingCart cart =  new ShoppingCart(grandTotal: 0)
        cart.save(flush:true)
        cart
    }
}
