package forograils

class Post {

    String topic
    Date dateCreate
    Date lastUpdate
    Boolean itsAllowed

    Forum forum
    Regular regular
    static hasMany = [file:File]
    static belongsTo = Regular
    static embedded = ['forum']

    static constraints = {
        topic(minSize: 3, maxSize: 50)
        dateCreate(validator: {return it > new Date()}, blank:false )
        lastUpdate(validator: {return it > new Date()})

    }

    static mapping = {
        regular column: 'owner_id'
        forum column: 'fatherForum_id'
    }
}
