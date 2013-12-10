package frod.media.domain

abstract class Media {

    SortedSet mediaImages

    static hasMany = [mediaImages: MediaImage]

    MediaGroup mediaGroup

    String typeSlug

    static mapping = {
        tablePerHierarchy(false)
        sort id: "desc"
    }

    static constraints = {
        mediaImages(nullable:true)
    }

    static transients = ['mainImage']

    public MediaImage getMainImage() {
        return mediaImages.first()
    }

}
