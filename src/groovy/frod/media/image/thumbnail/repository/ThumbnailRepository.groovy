package frod.media.image.thumbnail.repository

import java.awt.image.BufferedImage
import frod.media.image.ImageKey

import frod.media.image.OriginalImageRepository
import frod.media.image.thumbnail.adjustment.IAdjustment

import javax.imageio.ImageIO

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

    private byte[] bufferedImageToByteArray(BufferedImage image, ImageKey key) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, key.getFileExtension(), baos);
        baos.flush();
        byte[] convertedContent = baos.toByteArray();
        baos.close();
        return convertedContent;
    }

    public byte[] generateThumbnail(ImageKey key, List<IAdjustment> adjustments) {
        checkOriginalExists(key)
        def image = thumbnailGenerator.generateThumbnail(originalImageRepository.load(key), adjustments)
        return bufferedImageToByteArray(image, key)
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
