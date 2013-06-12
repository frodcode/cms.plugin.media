package frod.media.image.thumbnail.adjustment.resize

import frod.media.image.thumbnail.adjustment.IAdjustmentProcessor
import frod.media.image.thumbnail.adjustment.IAdjustment
import java.awt.image.BufferedImage
import org.imgscalr.Scalr

/**
 * User: freeman
 * Date: 12.6.13
 */
class ResizeProcessor implements IAdjustmentProcessor {

    public boolean canProcess(IAdjustment adjusting) {
        if (adjusting instanceof IResizeAdjustment) {
            return true;
        }
        return false
    }

    @Override
    BufferedImage applyAdjustment(BufferedImage image, IAdjustment adjustment) {
        IResizeAdjustment resizeAdjustment = (IResizeAdjustment) adjustment;
        return Scalr.resize(image, resizeAdjustment.getMethod(), resizeAdjustment.getMode(), resizeAdjustment.getWidth(), resizeAdjustment.getHeight());
    }
}
