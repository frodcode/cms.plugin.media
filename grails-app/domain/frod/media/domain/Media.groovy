package frod.media.domain

abstract class Media {

    SortedSet mediaImages

    static hasMany = [mediaImages: MediaImage]

    MediaGroup mediaGroup

    String typeSlug

    Integer position

    static mapping = {
        tablePerHierarchy(false)
        sort position: "asc"
    }

    static constraints = {
        mediaImages(nullable:true)
        position(unique: ['mediaGroup'])
    }

    static transients = ['mainImage']

    public MediaImage getMainImage() {
        return mediaImages.first()
    }

}
