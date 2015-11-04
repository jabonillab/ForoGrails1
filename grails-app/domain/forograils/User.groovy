package forograils

class User {

    String name
    String lastname
    Integer age
    String username
    String password

    static constraints = {
        name(blank: false, minSize: 3, maxSize: 50)
        lastname(blank: false, minSize: 3, maxSize: 50)
        age(min: 13)
        username(blank: false, unique: true)
        password(blank: false, minSize: 8, validator: {return it.matches('[a-z]') && it.matches('[A-Z]') && it.matches('[0-9]')})
    }
}
