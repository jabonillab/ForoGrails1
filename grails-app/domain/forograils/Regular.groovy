package forograils

class Regular extends User {

    Integer postViews
    Integer strikesNumber
    Integer starsNumber
    Date dateCreated

    static hasMany = [post:Post]

    static constraints = {
        postViews(min: 0)
        strikesNumber(range: 0..3)
        starsNumber(range: 0..5)
        dateCreated(blank:false)
    }
}
