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

class ImageServiceController {

    MediaFacade mediaFacade

    ThumbnailRepository thumbnailRepository

    OriginalImageRepository originalImageRepository

    ImageKeyStringParser imageKeyStringParser;

    def index() {
        MediaImage image = Media.findAll()[0].mainImage
        String key = 'C81E728-2-grails-jpg_resize-100x100-auto.jpg'
        def matcher = "C81E728" =~ /([A-F0-9]{7})/
        dump(matcher)
        dump(matcher.size())
        // byte[] imageContent = originalImageRepository.load(image)
        //render(text: "<xml>${image.title}</xml>", contentType: "text/xml", encoding: "UTF-8")
        def keyObject = imageKeyStringParser.getThumbnailKey(key)
        dump(keyObject)

        dump(image)
        response.contentType = 'image/jpeg'
        ResizeAdjustment resizeAdjustment = new ResizeAdjustment(100, 100, Scalr.Mode.AUTOMATIC)
        response.outputStream.write(thumbnailRepository.loadThumbnail(image, [resizeAdjustment]))
        //response.outputStream.write(originalImageRepository.load(image))
        response.outputStream.flush()

        //render(contentType: 'image/png') {
            //response.outputStream << originalImageRepository.load(image)
        //}
    }
}
