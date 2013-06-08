package frod.media.image.thumbnail.adjustment

import java.awt.image.BufferedImage

/**
 * User: freeman
 * Date: 7.6.13
 */
public interface IAdjustmentProcessor {

    public boolean canProcess(IAdjustment adjusting);

    public boolean applyAdjustment(BufferedImage image, IAdjustment adjustment);

}