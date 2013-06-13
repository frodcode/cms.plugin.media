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
        def regexPattern = /^([A-F0-9]{7})-([0-9]*)-([a-z0-9-]*)_([a-z0-9-_]*)\.([a-z]*)$/;
        def mainMatcher = urlPart =~ regexPattern
        if (mainMatcher.size() == 0) {
            throw new UrlKeyException(sprintf('Url part "%s" does not match to matcher. Check your url', urlPart));
        }
        def matcher = mainMatcher[0];
        UrlImageKey urlImageKey = new UrlImageKey(Integer.parseInt(matcher[2]), (String) matcher[3], (String) matcher[5]);
        String adjustingString = matcher[4]
        List<IAdjustment> adjustments = adjustmentParser.createAdjustments(adjustingString);
        return new UrlThumbnailKey(urlImageKey, adjustments);
    }

}
