package forograils

class File {

    String fileType
    Byte[] content
    Double size

    static belongsTo = [forum:Forum]

    static constraints = {
        content(maxSize: 10000000)
        fileType(matches: "[a-zA-Z]+/[a-zA-Z]+")
    }
}
