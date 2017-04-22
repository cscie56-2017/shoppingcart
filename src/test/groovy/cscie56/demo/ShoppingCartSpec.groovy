package cscie56.demo

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ShoppingCart)
@Mock([LineItem])
class ShoppingCartSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test grandTotal matches"() {
        when:
            Item item = new Item(price: 1000)
            LineItem lineItem = new LineItem( quantity: 1)
            lineItem.item = item
            ShoppingCart cart = new ShoppingCart(lineItems: [lineItem], grandTotal: 1000, owner: new User())
        then:
            cart.grandTotal == lineItem.extension
          //  cart.validate()
    }

    void "test credit card is valid "(){
        when: "invalid number - will not validate"
        Item item = new Item(price: 1000)
        LineItem lineItem = new LineItem( quantity: 1)
        lineItem.item = item
        ShoppingCart cart = new ShoppingCart(lineItems: [lineItem], grandTotal: 1000, owner: new User(), creditCard: "1234567890123456")
        cart.validate()
        then:
        cart.grandTotal == lineItem.extension
        cart.hasErrors()

        when: "valid visa: "
        cart.clearErrors()
        cart.creditCard = "4400692546742497"
        then:
        cart.validate()

        when: "valid AMEX 377895944271160"
        cart.clearErrors()
        cart.creditCard = "4400692546742497"
        then:
        cart.validate()
    }
}

//Dummy test class, as plugin should essentially work with any class that has a price
class Item {
    Integer price
}
