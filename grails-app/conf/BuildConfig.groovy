grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.dependency.resolution = {
    inherits "global"
    log      "warn"
    
    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()
    }
    plugins {
        compile ":redis:1.0.0.M2"
        runtime ":blueprint:0.9.1"
        build ":tomcat:1.3.5"
    }
    dependencies {
    }
}
