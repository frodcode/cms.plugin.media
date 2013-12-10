package frod.media

import frod.media.domain.Image
import frod.media.model.MediaFacade
import frod.media.domain.Media
import frod.media.image.thumbnail.repository.ThumbnailRepository
import frod.media.domain.MediaImage
import frod.media.image.OriginalImageRepository
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.awt.image.DataBufferByte
import frod.media.image.thumbnail.adjustment.crop.CropAdjustment
import frod.media.image.thumbnail.adjustment.resize.ResizeAdjustment
import org.imgscalr.Scalr
import frod.media.url.ImageKeyStringParser
import frod.media.url.UrlThumbnailKey

class ImageServiceController {

    MediaFacade mediaFacade

    ThumbnailRepository thumbnailRepository

    OriginalImageRepository originalImageRepository

    ImageKeyStringParser imageKeyStringParser;

    def index() {
        def urlPart = params.id
        UrlThumbnailKey keyObject = imageKeyStringParser.getThumbnailKey(urlPart)
        MediaImage image = MediaImage.findById(keyObject.imageKey.id)
        if (!image) {
            response.sendError(404, sprintf('Image with id "%s" was not found'));
            return;
        }
        String correctUrlPart = imageKeyStringParser.getUrlPart(image, keyObject.adjustments)
        if (correctUrlPart != urlPart) {
            redirect(id: correctUrlPart)
            return
        }
        Date today = new Date()
        Date expdate = new Date()
        Date modified = new Date()
        modified.setTime (modified.getTime() - (10000 * 1000));
        expdate.year = expdate.year + 1

        response.contentType = image.mimeType
        ResizeAdjustment resizeAdjustment = new ResizeAdjustment(100, 100, Scalr.Mode.AUTOMATIC)
        response.outputStream.write(thumbnailRepository.loadThumbnail(keyObject.imageKey, keyObject.adjustments))
        //entity.contentOutputStream.write(originalImageRepository.load(image))
        response.addHeader('Cache-Control', 'max-age=1209600')
        response.addHeader('Date', today.toGMTString())
        response.addHeader('Expires', expdate.toGMTString())
        response.addHeader('Connection', 'keep-alive')
        response.addHeader('Last-Modified', modified.toGMTString())

        def ext = keyObject.imageKey.fileExtension.toLowerCase()
        response.outputStream.flush()

        //render(contentType: 'image/png') {
            //entity.contentOutputStream << originalImageRepository.load(image)
        //}
    }
}
