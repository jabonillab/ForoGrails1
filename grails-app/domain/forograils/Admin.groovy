package forograils

class Admin extends User {

    Integer level
    Double rating

    static hasMany = [forum:Forum]

    static constraints = {
        level(range: 1..5)
        rating(range: 0..100)
    }
}
