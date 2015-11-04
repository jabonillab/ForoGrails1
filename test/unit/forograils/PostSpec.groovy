package forograils

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(Post)
class PostSpec extends Specification {

    def setup() {
        mockForConstraintsTests(Post)
    }

    def cleanup() {
    }

    @Unroll ("test Post attribute topic: #topic, field: #field, valid: #valid ")
    def "Testing Domain Class Post - topic"(){
        when:
        def obj = new Post(topic: topic,dateCreated: new Date(), lastUpdate: new Date(),itsAllowed: true)
        obj.validate()

        then:
        obj.errors.hasFieldErrors("topic") == false

        where:
        name   | valid | field
        'ab'   | false | 'minSize'
        'abc'  | true  | 'minSize'
        'abcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcde'  | false | 'maxSize'
        'abcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcde'  | true | 'maxSize'
    }

    @Unroll("test Post attribute dateCreated: #dateCreated, field: #field, valid: #valid")
    def "Testing Domain Class Post - dateCreated"(){
        when:
        def obj = new Post(topic: "programacion",dateCreated: dateCreated, lastUpdate: new Date(),itsAllowed: true)
        obj.validate()

        then:
        obj.hasFieldErrors("dateCreated") == false

        where:
        dateCreated 	| valid		| field
        new Date()		| true		| 'min'
        new Date()-3		| false		| 'min'

    }

    @Unroll("test Post attribute lastUpdate: #lastUpdate, field: #field, valid: #valid")
    def "Testing Domain Class Post - lastUpdate"(){
        when:
        def obj = new Post(topic: "programacion",dateCreated: new Date(), lastUpdate: lastUpdate,itsAllowed: true)
        obj.validate()

        then:
        obj.hasFieldErrors("lastUpdate") == false

        where:
        dateCreated 	| valid		| field
        new Date()		| true		| 'min'
        new Date()-3		| false		| 'min'
    }

}
