package frod.media.domain

abstract class Media {

    static hasMany = [mediaImages: MediaImage]

    MediaImage mainImage;

    MediaGroup mediaGroup

    String typeSlug

    static mapping = {
        tablePerHierarchy(false)
    }

    static constraints = {
        mainImage(nullable:true)
        mediaImages(nullable:true)
    }
}
