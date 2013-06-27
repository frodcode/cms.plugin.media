package frod.media.domain

import frod.media.image.ImageKey

class MediaImage implements ImageKey, Comparable {

    Integer id

    String title

    String fileExtension

    String mimeType

    Integer position

    static constraints = {
    }

    int compareTo(obj) {
        position.compareTo(obj.position)
    }

}
