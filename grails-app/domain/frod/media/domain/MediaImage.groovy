package frod.media.domain

import frod.media.image.ImageKey

class MediaImage implements ImageKey {

    Integer id

    String title

    String fileExtension

    String mimeType

    static constraints = {
    }

}
