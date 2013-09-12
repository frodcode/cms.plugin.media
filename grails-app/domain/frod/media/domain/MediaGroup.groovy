package frod.media.domain

class MediaGroup {

    String name

    String description

    MediaGroupType type

    static constraints = {
        description(nullable:true)
    }
}
