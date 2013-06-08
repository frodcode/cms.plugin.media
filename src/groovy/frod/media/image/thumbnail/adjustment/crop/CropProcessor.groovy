package frod.media.image.thumbnail.adjustment.crop

import frod.media.image.thumbnail.adjustment.IAdjustment
import java.awt.image.BufferedImage
import org.imgscalr.Scalr
import frod.media.image.thumbnail.adjustment.IAdjustmentProcessor;

/**
 * User: freeman
 * Date: 7.6.13
 */
class CropProcessor implements IAdjustmentProcessor {

    public boolean canProcess(IAdjustment adjusting) {
        if (adjusting instanceof ICropAdjustment) {
            return true;
        }
        return false
    }

    public boolean applyAdjustment(BufferedImage image, IAdjustment adjusting) {
        ICropAdjustment cropAdjusting = (ICropAdjustment) adjusting
        return Scalr.crop(image, cropAdjusting.left, cropAdjusting.top, cropAdjusting.width, cropAdjusting.height)
    }

}
