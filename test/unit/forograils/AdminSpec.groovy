package forograils

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Admin)
class AdminSpec extends Specification {

    def setup() {
        mockForConstraintsTests(Admin)
    }

    def cleanup() {
    }

    @Unroll ("test admin attribute level: #level, field: #field, valid: #valid ")
    def "Testing Domain Class Admin - level"(){
        when:
        def t_admin_level = new Admin(level: level, rating: 85, name: 'prueba', lastname: 'prueba', age: 20, username: 'testing', password: 'Testing2015')
        t_admin_level.validate()

        then:
        t_admin_level.errors.hasFieldErrors("level") == false

        where:
        level | valid | field
        null | false | 'null'
        0 | false | 'minRange'
        6 | false | 'maxRange'
        3 | true | 'Range'
    }

    @Unroll ("test admin attribute rating: #rating, field: #field, valid: #valid ")
    def "Testing Domain Class Admin - rating"(){
        when:
        def t_admin_rating = new Admin(level: 3, rating: rating, name: 'prueba', lastname: 'prueba', age: 20, username: 'testing', password: 'Testing2015')
        t_admin_rating.validate()

        then:
        t_admin_rating.errors.hasFieldErrors("rating") == false

        where:
        rating | valid | field
        null | false | 'null'
        -1 | false | 'minRange'
        101 | false | 'maxRange'
        56 | true | 'Range'
    }
}
