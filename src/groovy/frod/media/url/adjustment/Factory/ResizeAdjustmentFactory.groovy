package frod.media.url.adjustment.Factory

import frod.media.image.thumbnail.adjustment.IAdjustment
import frod.media.url.UrlKeyException
import org.imgscalr.Scalr
import frod.media.image.thumbnail.adjustment.resize.ResizeAdjustment

/**
 * User: freeman
 * Date: 13.6.13
 */
class ResizeAdjustmentFactory implements IAdjustmentFactory {

    @Override
    IAdjustment create(String params) {
        def pattern = /^([0-9]*)x([0-9]*)-([a-z]*)$/
        def matcher = params =~ pattern;
        if (matcher.size() == 0) {
            throw new UrlKeyException(sprintf('Cannot parse resize adjustment params "%s"', params))
        }
        def paramList = matcher[0]
        Integer width = Integer.parseInt(paramList[1]);
        Integer height = Integer.parseInt(paramList[2]);
        Scalr.Mode mode = extractModeFromString(paramList[3])
        return new ResizeAdjustment(width, height, mode);
    }

    Scalr.Mode extractModeFromString(String stringMode) {
        switch (stringMode) {
            case 'auto' :
                return Scalr.Mode.AUTOMATIC
            case 'fit' :
                return Scalr.Mode.FIT_EXACT
            case 'fith':
                return Scalr.Mode.FIT_TO_HEIGHT
            case 'fitw':
                return Scalr.Mode.FIT_TO_WIDTH
            default:
                throw new UrlKeyException(sprintf('Unknown mode "%s"', stringMode))
        }
    }
}
