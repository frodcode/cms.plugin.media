package frod.media.url

import frod.media.image.ImageKey
import frod.media.image.thumbnail.adjustment.IAdjustment

/**
 * User: freeman
 * Date: 7.6.13
 */
class UrlThumbnailKey {

    private ImageKey imageKey

    private List<IAdjustment> adjustments

    UrlThumbnailKey(ImageKey imageKey, List<IAdjustment> adjustments) {
        this.imageKey = imageKey
        this.adjustments = adjustments
    }

    ImageKey getImageKey() {
        return imageKey
    }

    List<IAdjustment> getAdjustments() {
        return adjustments
    }
}
