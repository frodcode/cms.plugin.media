package frod.media.domain

import frod.media.image.ImageKey

class MediaImage implements ImageKey {

    String title

    String fileExtension

    String mimeType

    static constraints = {
    }

}
