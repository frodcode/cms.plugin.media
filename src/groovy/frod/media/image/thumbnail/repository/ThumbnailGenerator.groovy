package frod.media.image.thumbnail.repository

import java.awt.image.BufferedImage
import javax.imageio.ImageIO

import frod.media.image.thumbnail.adjustment.AdjustmentProcessorRegister
import frod.media.image.thumbnail.adjustment.IAdjustment
import frod.media.image.thumbnail.adjustment.IAdjustmentProcessor

/**
 * User: freeman
 * Date: 7.6.13
 */
class ThumbnailGenerator {

    AdjustmentProcessorRegister adjustingProcessorRegister

    public BufferedImage generateThumbnail(byte[] content, List<IAdjustment> adjustments) {
        InputStream inputStream = new ByteArrayInputStream(content);
        BufferedImage image = ImageIO.read(inputStream);
        for (IAdjustment adjustment in adjustments) {
            IAdjustmentProcessor processor = adjustingProcessorRegister.getByAdjustment(adjustment)
            image = processor.applyAdjustment(image, adjustment);
        }
        return image;
    }

}
