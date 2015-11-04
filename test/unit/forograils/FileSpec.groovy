package forograils

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(File)
class FileSpec extends Specification {

    def setup() {
        mockForConstraintsTests(File)
    }

    def cleanup() {
    }

    @Unroll("test File attribute fileType: #fileType, field: #field, valid: #valid")
    void "test fileType constraint for domain class File"() {
        when:
        def f1 = new File(fileType: fileType, content: [1, 1, 1, 1] as byte[], 1000)
        f1.validate()

        then:
        f1.errors.hasFieldErrors("fileType") == false

        where:
        fileType    | valid     | field
        null        | false     | 'null'
        '/a'        | false     | 'matches'
        'a/b'       | true      | 'matches'
        'b/'        | false     | 'matches'
    }

    @Unroll("test File attribute content: #content, field: #field, valid: #valid")
    void "test content constraint for domain class File"() {
        when:
        def f2 = new File(fileType: "text/plain", content: content, 1000)

        then:
        f2.errors.hasFieldErrors("content") == false

        where:
        content     | valid     | field
        null        | false     | 'null'
        [] as byte[]| false     | 'null'

    }

    @Unroll("test File attribute size: #size, field: #field, valid: #valid")
    void "test size constraint for domain class File"() {
        when:
        def f3 = new File(fileType: "text/plain", content: [1, 1, 1, 1] as byte[], size: size)

        then:
        f2.errors.hasFieldErrors("size") == false

        where:
        size    | valid     | field
        null    | false     | 'null'
        -1      | false     | 'minValue'
        10E20   | false     | 'maxValue'
        10000   | true      | 'Value'
    }

}
