package forograils

class Forum {

    String name
    Date dateCreated
    String category

    static constraints = {
        name(minSize: 3, maxSize: 20, unique: true)
        category(minSize: 3, maxSize: 15)
        dateCreated(validator: {return it > new Date()})
    }
}
