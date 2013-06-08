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

class ImageServiceController {

    MediaFacade mediaFacade

    ThumbnailRepository thumbnailRepository

    OriginalImageRepository originalImageRepository

    def index() {
        MediaImage image = Media.findAll()[0].mainImage
        // byte[] imageContent = originalImageRepository.load(image)
        //render(text: "<xml>${image.title}</xml>", contentType: "text/xml", encoding: "UTF-8")
        response.contentType = 'image/jpeg'
        //response.outputStream.write(thumbnailRepository.loadThumbnail(image, []))
        response.outputStream.write(originalImageRepository.load(image))
        response.outputStream.flush()

        //render(contentType: 'image/png') {
            //response.outputStream << originalImageRepository.load(image)
        //}
    }
}
