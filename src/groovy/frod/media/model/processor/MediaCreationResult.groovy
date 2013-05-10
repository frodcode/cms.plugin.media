package frod.media.model.processor

import frod.media.domain.Media

/**
 * User: freeman
 * Date: 3.5.13
 */
class MediaCreationResult {

    Media media

    /**
     * First id the main
     */
    MediaImageCreationResult[] mediaImageCreationResults

    public MediaCreationResult(Media media, List<MediaImageCreationResult> mediaImageCreationResults) {
        this.media = media
        this.mediaImageCreationResults = mediaImageCreationResults
    }
}
