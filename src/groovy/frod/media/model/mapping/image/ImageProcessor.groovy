package frod.media.model.mapping.image

import frod.media.model.processor.MediaCreationResult
import frod.media.domain.Image
import frod.media.model.processor.MediaImageCreationResult
import frod.media.domain.MediaImage
import frod.media.repository.MimeTypeGuesser
import frod.media.repository.FileExtensionGuesser
import frod.media.domain.Media
import frod.media.image.OriginalImageRepository

/**
 * User: freeman
 * Date: 3.5.13
 */
class ImageProcessor {

    MimeTypeGuesser mimeTypeGuesser

    FileExtensionGuesser fileExtensionGuesser

    OriginalImageRepository originalImageRepository

    public boolean canProcess(File file) {
        def mimeType = mimeTypeGuesser.getMimeType(file);
        return ['image/gif', 'image/jpg', 'image/jpeg', 'image/png', 'image/x-png'].contains(mimeType.toString())
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

    public boolean canProcess(URL url) {
        String html = url.getText()
        XmlSlurper slurper = new XmlSlurper()
        def titleEl = slurper.find {it.name() == 'title'}
        dump(titleEl)
    }

}
