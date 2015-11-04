package forograils

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    def setup() {
        mockForConstraintsTests(User)
    }

    def cleanup() {
    }

    @Unroll ("test user attribute name: #name, field: #field, valid: #valid ")
    def "Testing Domain Class User - name"(){
        when:
        def t_user_name = new User(name: name, lastname: 'prueba', age: 20, username: 'pablito', password: 'Testing10')
        t_user_name.validate()

        then:
        t_user_name.errors.hasFieldErrors("name") == false

        where:
        name | valid | field
        'ab' | false | 'minSize'
        null | false | 'null'
        'abc' | true | 'size'
        'aaaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeee' | true | 'size'
        'aaaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeef' | false | 'maxSize'
        '' | false | 'blank'
    }

    @Unroll ("test user attribute lastname: #lastname, field: #field, valid: #valid ")
    def "Testing Domain Class User - lastname"(){
        when:
        def t_user_lastname = new User(name: 'prueba', lastname: lastname, age: 20, username: 'pablito', password: 'Testing10')
        t_user_lastname.validate()

        then:
        t_user_lastname.errors.hasFieldErrors("lastname") == false

        where:
        lastname | valid | field
        'ab' | false | 'minSize'
        null | false | 'null'
        'abc' | true | 'size'
        'aaaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeee' | true | 'size'
        'aaaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeef' | false | 'maxSize'
        '' | true | 'blank'
    }

    @Unroll ("test user attribute age: #age, field: #field, valid: #valid ")
    def "Testing Domain Class User - age"(){
       when:
        def t_user_age = new User(name: 'prueba', lastname: 'prueba', age: age, username: 'pablito', password: 'Testing10')
        t_user_age.validate()

        then:
        t_user_age.errors.hasFieldErrors("age") == false

        where:
        age | valid | field
        13 | false | 'minAge'
        14 | true | 'Age'
    }

    @Unroll ("test user attribute username: #username, field: #field, valid: #valid ")
    def "Testing Domain Class User - username"(){
        setup:
        mockForConstraintsTests(User, [new User(username: 'pablo')])

        when:
        def t_user_username = new User(name: 'prueba', lastname: 'prueba', age: 20, username: username, password: 'Testing10')
        t_user_username.validate()

        then:
        t_user_username.errors.hasFieldErrors("username") == false

        where:
        username | valid | field
        '' | false | 'blank'
        null | false | 'null'
        'pablo' | false | 'unique'
        'pedro' | true | 'username'
    }

    @Unroll ("test user attribute password: #password, field: #field, valid: #valid ")
    def "Testing Domain Class User - password"(){
        when:
        def t_user_password = new User(name: 'prueba', lastname: 'prueba', age: 20, username: 'username', password: password)
        t_user_password.validate()

        then:
        t_user_password.errors.hasFieldErrors("password") == false

        where:
        password | valid | field
        '' | false | 'blank'
        null | false | 'null'
        'aaaaaA1' | false | 'minSize'
        'aaaaaaaaa' | false | 'notCorrect'
        'aaaaaaaaa1' | false | 'notCorrect'
        '1111111111' | false | 'notCorrect'
        '111111111A' | false | 'notCorrect'
        'AAAAAAAAAa' | false | 'notCorrect'
        '11111a1111' | false | 'notCorrect'
        'Prueba2015' | true | 'CorrectPassword'
        'comoPuede5' | true | 'CorrectPassword'
    }
}
