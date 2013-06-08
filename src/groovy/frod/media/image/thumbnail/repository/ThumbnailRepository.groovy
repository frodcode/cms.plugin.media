package frod.media.image.thumbnail.repository

import java.awt.image.BufferedImage
import frod.media.image.ImageKey

import frod.media.image.OriginalImageRepository
import frod.media.image.thumbnail.adjustment.IAdjustment
import java.awt.image.DataBufferByte
import javax.imageio.ImageIO
import java.awt.image.WritableRaster
import com.sun.image.codec.jpeg.JPEGCodec
import com.sun.image.codec.jpeg.JPEGImageDecoder
import java.awt.image.DataBufferInt

/**
 * User: freeman
 * Date: 7.6.13
 */
class ThumbnailRepository {

    OriginalImageRepository originalImageRepository

    ThumbnailGenerator thumbnailGenerator

    private void checkOriginalExists(ImageKey key) {
        if (!originalImageRepository.exists(key)) {
            throw new IllegalArgumentException(sprintf('Cannot find any image for key with id "%s"', key.id))
        }
    }

    private byte[] bufferedImageToBytArray(BufferedImage image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        baos.flush();
        byte[] convertedContent = baos.toByteArray();
        baos.close();
        return convertedContent;
    }

    public byte[] generateThumbnail(ImageKey key, List<IAdjustment> adjustments) {
        checkOriginalExists(key)
        def image = thumbnailGenerator.generateThumbnail(originalImageRepository.load(key), adjustments)
        return bufferedImageToBytArray(image)
    }

    public byte[] loadThumbnail(ImageKey key, List<IAdjustment> adjustments) {
        return generateThumbnail(key, adjustments)
    }

    public boolean thumbnailExists(ImageKey key, List<IAdjustment> adjustments) {
        return originalImageRepository.exists(key)
    }

    public void remove(ImageKey key, List<IAdjustment> adjustments) {

    }

}
