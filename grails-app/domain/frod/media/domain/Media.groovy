package frod.media.domain

abstract class Media {

    SortedSet mediaImages

    static hasMany = [mediaImages: MediaImage]

    MediaGroup mediaGroup

    String typeSlug

    static mapping = {
        tablePerHierarchy(false)
    }

    static constraints = {
        mediaImages(nullable:true)
    }

    static transients = ['mainImage']

    public MediaImage getMainImage() {
        return mediaImages.first()
    }

}
