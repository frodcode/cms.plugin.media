package frod.media

import frod.media.domain.Media
import frod.media.domain.MediaImage
import frod.media.image.thumbnail.adjustment.IAdjustment
import frod.media.url.ImageKeyStringParser

class MediaTagLib {

    ImageKeyStringParser imageKeyStringParser

    static namespace = "frodm"

    /**
     * @attr media
     * @attr mediaImage
     * @attr adjustments
     */
    def img = {attrs, body ->
        MediaImage mediaImage = getMediaImageFromAttrs(attrs)
        def uri = getImageUri(mediaImage, attrs)
        def myAttrs = [uri: uri]
        out << g.img(attrs + myAttrs, body)
    }

    /**
     * @attr media
     * @attr mediaImage
     * @attr adjustments
     */
    def imgUri = {attrs, body ->
        MediaImage mediaImage = getMediaImageFromAttrs(attrs)
        out << getImageUri(mediaImage, attrs)
    }

    /**
     * @attr media
     * @attr mediaImage
     * @attr adjustments
     */
    def imgLink = {attrs, body ->
        MediaImage mediaImage = getMediaImageFromAttrs(attrs)
        def adjustments = getAdjustments(attrs)
        def myAttrs = [mapping: 'dynamicImage', absolute: true, params: [id: imageKeyStringParser.getUrlPart(mediaImage, adjustments)]]
        out << g.link(attrs + myAttrs, body)
    }

    private def getMediaImageFromAttrs(def attrs) {
        Media media = attrs.media;
        MediaImage mediaImage;
        if (media) {
            mediaImage = media.mainImage
        } else {
            mediaImage = attrs.mediaImage
        }
        if (!mediaImage) {
            throw new IllegalArgumentException('For element img you must define eather media or media image attribute')
        }
        return mediaImage
    }

    private def getAdjustments(def attrs) {
        List<IAdjustment> adjustments = attrs.adjustments
        if (!adjustments) {
            adjustments = []
        }
        return adjustments
    }

    private def getImageUri(MediaImage mediaImage, def attrs) {
        List<IAdjustment> adjustments = getAdjustments(attrs)
        return createLink([mapping: 'dynamicImage', absolute: true, params: [id: imageKeyStringParser.getUrlPart(mediaImage, adjustments)]])
    }

}
