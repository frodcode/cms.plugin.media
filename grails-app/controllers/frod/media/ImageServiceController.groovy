package frod.media

import frod.media.domain.Image

class ImageServiceController {

    def index() {
        Image image = new Image()
        image.save()
        render(text: "<xml>some xml</xml>", contentType: "text/xml", encoding: "UTF-8")
    }
}
