grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility
    repositories {
        grailsCentral()
        mavenRepo "http://maven.thebuzzmedia.com"
        mavenRepo "http://repo1.maven.org/maven2"
        mavenRepo "http://repository.codehaus.org"
        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        //mavenLocal()
        //mavenCentral()
        //mavenRepo "http://snapshots.thumbnail.repository.codehaus.org"
        //mavenRepo "http://thumbnail.repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://thumbnail.repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.21'
        runtime group: 'org.codehaus.groovy.modules.http-builder',
                name: 'http-builder',
                version: '0.5.2'
        compile group: 'org.imgscalr',
                name: 'imgscalr-lib',
                version:'4.2'
        compile group: 'org.apache.tika',
                name:  'tika-core',
                version: '1.3'
    }

    plugins {
        runtime ":hibernate:$grailsVersion"
        build(":tomcat:$grailsVersion",
              ":release:2.2.0",
              ":rest-client-builder:1.0.3") {
            export = false
        }
    }
}

grails.plugin.location.Routing = "../Diagnostics"
