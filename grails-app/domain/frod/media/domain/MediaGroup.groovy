package frod.media.domain

class MediaGroup {

    String name

    String description

    static constraints = {
        description(nullable:true)
    }
}
