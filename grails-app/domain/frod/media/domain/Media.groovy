package frod.media.domain

abstract class Media {

    static hasMany = [mediaImages: MediaImage]

    MediaImage mainImage;

    MediaGroup mediaGroup

    static mapping = {
        tablePerHierarchy(false)
    }

    static constraints = {
        mainImage(nullable:true)
        mediaImages(nullable:true)
    }
}
