package frod.media.model.processor

import frod.media.domain.MediaImage

/**
 * User: freeman
 * Date: 3.5.13
 */
class MediaImageCreationResult {

    MediaImage mediaImage

    byte[] content

    public MediaImageCreationResult(MediaImage mediaImage, byte[] content) {
        this.mediaImage = mediaImage
        this.content = content
    }
}
