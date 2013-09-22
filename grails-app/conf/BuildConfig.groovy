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
    legacyResolve true // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility
    repositories {
        grailsCentral()

        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        mavenLocal()
        mavenCentral()
        mavenRepo "http://maven.thebuzzmedia.com"
        mavenRepo "http://repo1.maven.org/maven2"
        mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://snapshots.thumbnail.repository.codehaus.org"
        //mavenRepo "http://thumbnail.repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://thumbnail.repository.jboss.com/maven2/"
    }
    dependencies {

        compile group: 'org.jumpmind.symmetric.jdbc',
                name: 'postgresql',
                version: '9.2-1002-jdbc4'
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.21'
        compile group: 'org.ccil.cowan.tagsoup',
                name: 'tagsoup',
                version: '1.2'
        compile group: 'org.imgscalr',
                name: 'imgscalr-lib',
                version:'4.2'
        compile group: 'org.apache.tika',
                name:  'tika-core',
                version: '1.3'
        runtime("org.apache.maven:maven-ant-tasks:2.1.3") {
            excludes "commons-logging", "xml-apis", "groovy"
        }
    }

    plugins {
        runtime ":hibernate:$grailsVersion"
        build(":tomcat:$grailsVersion",
              ":release:2.2.1",
              ":rest-client-builder:1.0.3") {
            export = false
        }
    }
}

// grails.plugin.location.Routing = "../Diagnostics"

