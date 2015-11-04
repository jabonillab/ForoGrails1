package forograils

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Regular)
class RegularSpec extends Specification {

    def setup() {
        mockForConstraintsTests(Regular)
    }

    def cleanup() {
    }

    @Unroll ("test regular attribute postViews: #postViews, field: #field, valid: #valid ")
    def "Testing Domain Class Regular - postViews"(){
        when:
        def t_regular_postViews = new Regular(postViews: postViews, strikesNumber: 2, starsNumber: 4, name: 'prueba', lastname: 'prueba', age: 20, username: 'testing', password: 'Testing2015')
        t_regular_postViews.validate()

        then:
        t_regular_postViews.errors.hasFieldErrors("postViews") == false

        where:
        postViews | valid | field
        null | false | 'null'
        -1 | false | 'minValue'
        0 | true | 'Value'
        20 | true | 'Value'
    }

    @Unroll ("test regular attribute strikesNumber: #strikesNumber, field: #field, valid: #valid ")
    def "Testing Domain Class Regular - strikesNumber"(){
        when:
        def t_regular_strikesNumber = new Regular(postViews: 3, strikesNumber: strikesNumber, starsNumber: 4, name: 'prueba', lastname: 'prueba', age: 20, username: 'testing', password: 'Testing2015')
        t_regular_strikesNumber.validate()

        then:
        t_regular_strikesNumber.errors.hasFieldErrors("strikesNumber") == false

        where:
        strikesNumber | valid | field
        null | false | 'null'
        -1 | false | 'minValue'
        4 | false | 'maxValue'
        2 | true | 'Value'
    }

    @Unroll ("test regular attribute starsNumber: #starsNumber, field: #field, valid: #valid ")
    def "Testing Domain Class Regular - starsNumber"(){
        when:
        def t_regular_starsNumber = new Regular(postViews: 3, strikesNumber: 2, starsNumber: starsNumber, name: 'prueba', lastname: 'prueba', age: 20, username: 'testing', password: 'Testing2015')
        t_regular_starsNumber.validate()

        then:
        t_regular_starsNumber.errors.hasFieldErrors("starsNumber") == false

        where:
        starsNumber | valid | field
        null | false | 'null'
        -1 | false | 'minValue'
        6 | false | 'maxValue'
        4 | true | 'Value'
    }
}
