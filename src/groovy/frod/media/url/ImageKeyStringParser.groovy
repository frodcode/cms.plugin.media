package frod.media.url

import java.util.regex.Pattern
import frod.media.url.adjustment.AdjustmentParser
import frod.media.image.thumbnail.adjustment.IAdjustment

/**
 * User: freeman
 * Date: 7.6.13
 */
class ImageKeyStringParser {

    AdjustmentParser adjustmentParser

    public UrlThumbnailKey getThumbnailKey(String urlPart) {
        def regexPattern = /(?i)^([a-f0-9]{7})-([0-9]*)-([a-z0-9-]*)_([a-z0-9-_]*)\.([a-z]*)$/;
        def matcher = regexPattern =~ urlPart
        if (matcher.size() != 6) {
            throw new UrlKeyException(sprintf('Url part "%s" does not match to matcher. Check your url', urlPart));
        }
        UrlImageKey urlImageKey = new UrlImageKey((int) matcher[2], (String) matcher[3], (String) matcher[5]);
        String adjustingString = matcher[4]
        List<IAdjustment> adjustments = adjustmentParser.createAdjustments(adjustingString);
        return new UrlThumbnailKey(urlImageKey, adjustments);
    }

}
