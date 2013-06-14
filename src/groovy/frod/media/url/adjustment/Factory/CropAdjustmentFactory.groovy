package frod.media.url.adjustment.Factory

import frod.media.image.thumbnail.adjustment.IAdjustment
import frod.media.image.thumbnail.adjustment.crop.ICropAdjustment

/**
 * User: freeman
 * Date: 7.6.13
 */
class CropAdjustmentFactory implements IAdjustmentFactory {
    @Override
    IAdjustment create(String params) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    String createUrlPartFromAdjustment(IAdjustment adjustment) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    boolean canCreateUrlPart(IAdjustment adjustment) {
        return false  //To change body of implemented methods use File | Settings | File Templates.
    }
}
