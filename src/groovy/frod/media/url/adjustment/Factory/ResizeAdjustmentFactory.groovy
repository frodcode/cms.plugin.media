package frod.media.url.adjustment.Factory

import frod.media.image.thumbnail.adjustment.IAdjustment
import frod.media.url.UrlKeyException
import org.imgscalr.Scalr
import frod.media.image.thumbnail.adjustment.resize.ResizeAdjustment
import frod.media.image.thumbnail.adjustment.resize.IResizeAdjustment

/**
 * User: freeman
 * Date: 13.6.13
 */
class ResizeAdjustmentFactory implements IAdjustmentFactory {

    public static LinkedHashMap<String, Scalr.Mode> modeMapping = ['auto': Scalr.Mode.AUTOMATIC, 'fit': Scalr.Mode.FIT_EXACT, 'fith': Scalr.Mode.FIT_TO_HEIGHT, 'fitw': Scalr.Mode.FIT_TO_WIDTH]

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
        if (!modeMapping.containsKey(stringMode)) {
            throw new UrlKeyException(sprintf('Unknown mode "%s"', stringMode))
        }
        return modeMapping.get(stringMode)
    }

    String extractStringFromMode(Scalr.Mode mode) {
        if (!modeMapping.containsValue(mode)) {
            throw new UrlKeyException(sprintf('Unknown mode for resize. Check modeMapping proeperty and add string value for it'))
        }
        for (String key : modeMapping.keySet()) {
            if (modeMapping.get(key) == mode) {
                return key;
            }
        }
        throw new IllegalStateException('Something is terrible wrong. This code is supposed to be unreachable')
    }

    String createUrlPartFromAdjustment(IAdjustment adjustment) {
        ResizeAdjustment resizeAdjustment = (ResizeAdjustment) adjustment
        return resizeAdjustment.width + 'x' + resizeAdjustment.height+'-'+extractStringFromMode(resizeAdjustment.mode)
    }

    public boolean canCreateUrlPart(IAdjustment adjustment) {
        if (adjustment instanceof  IResizeAdjustment) {
            return true
        }
        return false;
    }

}
