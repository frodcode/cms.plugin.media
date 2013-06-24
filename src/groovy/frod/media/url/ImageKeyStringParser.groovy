package frod.media.url

import java.util.regex.Pattern
import frod.media.url.adjustment.AdjustmentParser
import frod.media.image.thumbnail.adjustment.IAdjustment
import frod.media.image.ImageKey
import java.security.MessageDigest
import javax.xml.bind.annotation.adapters.HexBinaryAdapter
import frod.media.utils.StringUtils

/**
 * User: freeman
 * Date: 7.6.13
 */
class ImageKeyStringParser {

    AdjustmentParser adjustmentParser

    public UrlThumbnailKey getThumbnailKey(String urlPart) {
        def regexPattern = /^([a-f0-9]{7})-([0-9]*)-([a-z0-9-]*)_([a-z0-9-_]*)\.([a-z]*)$/;
        def mainMatcher = urlPart =~ regexPattern
        if (mainMatcher.size() == 0) {
            throw new UrlKeyException(sprintf('Url part "%s" does not match to matcher. Check your url', urlPart));
        }
        def matcher = mainMatcher[0];
        UrlImageKey urlImageKey = new UrlImageKey(Integer.parseInt(matcher[2]), (String) matcher[3], (String) matcher[5]);
        String adjustmentString = matcher[4]
        List<IAdjustment> adjustments = adjustmentParser.createAdjustments(adjustmentString);
        return new UrlThumbnailKey(urlImageKey, adjustments);
    }

    public String getUrlPart(ImageKey imageKey, List<IAdjustment> adjustments) {
        String urlPart = '';
        if (!imageKey.getId()) {
            throw new IllegalArgumentException('Image key must content id')
        }
        String title = StringUtils.webalize(imageKey.title)
        MessageDigest md = MessageDigest.getInstance("MD5");
        String md5 = (new HexBinaryAdapter()).marshal(md.digest(imageKey.getId().toString().getBytes()))
        String shortedMd5 = md5.substring(0, 7);
        urlPart += shortedMd5 + '-' +
                imageKey.getId() + '-' +
                title;

        urlPart += '_' + adjustmentParser.getUrlPartFromAdjustments(adjustments)
        if (imageKey.fileExtension) {
            urlPart += '.' + imageKey.fileExtension
        }
        return urlPart.toLowerCase();
    }

}
