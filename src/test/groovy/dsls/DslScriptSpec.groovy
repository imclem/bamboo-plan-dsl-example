package dsls

import ch.mibex.bamboo.plandsl.dsl.DslScriptContext
import ch.mibex.bamboo.plandsl.dsl.DslScriptParserImpl
import spock.lang.Specification
import spock.lang.Unroll

class DslScriptSpec extends Specification {

    @Unroll
    def 'test DSL script #file.name'(File file) {
        given:

        when:
        def loader = new DslScriptParserImpl()
        def additionalClassPath = new File('src/main/groovy').toURI().toURL()
        loader.parse(new DslScriptContext(file.toURI().toString(), null, additionalClassPath))

        then:
        noExceptionThrown()

        where:
        file << findDslFiles()
    }

    private static def findDslFiles() {
        List<File> files = []
        new File('dsls').eachFileRecurse {
            if (it.name.endsWith('.groovy')) {
                files << it
            }
        }
        files
    }
}