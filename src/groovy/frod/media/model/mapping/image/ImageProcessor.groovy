package frod.media.model.mapping.image

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream
import frod.media.domain.Image
import frod.media.domain.Media
import frod.media.domain.MediaImage
import frod.media.image.OriginalImageRepository
import frod.media.model.processor.MediaCreationResult
import frod.media.model.processor.MediaImageCreationResult
import frod.media.repository.FileExtensionGuesser
import frod.media.repository.MimeTypeGuesser
import frod.media.utils.UrlUtils

/**
 * User: freeman
 * Date: 3.5.13
 */
class ImageProcessor {

    MimeTypeGuesser mimeTypeGuesser

    FileExtensionGuesser fileExtensionGuesser

    OriginalImageRepository originalImageRepository

    def knownMimeTypes = ['image/gif', 'image/jpg', 'image/jpeg', 'image/png', 'image/x-png']

    public boolean canProcess(File file) {
        def mimeType = mimeTypeGuesser.getMimeType(file);
        return knownMimeTypes.contains(mimeType.toString())
    }

    public List<MediaCreationResult> createAssetFromFile(File file, String title) {
        Image image = new Image()
        MediaImage mediaImage = new MediaImage()
        mediaImage.mimeType = mimeTypeGuesser.getMimeType(file)
        mediaImage.fileExtension = fileExtensionGuesser.getExtension(file)
        mediaImage.title = title

        MediaImageCreationResult mediaImageCreationResult = new MediaImageCreationResult(mediaImage, file.getBytes())
        return [new MediaCreationResult(image, [mediaImageCreationResult])]
    }

    public List<Media> process(File file, List<MediaCreationResult> mediaCreationResults) {
        byte[] content = mediaCreationResults[0].mediaImageCreationResults[0].content
        MediaImage mediaImage = mediaCreationResults[0].mediaImageCreationResults[0].mediaImage;
        originalImageRepository.save(content, mediaImage)
        return [mediaCreationResults[0].media]
    }

}
