package frod.media.example

import frod.media.model.MediaFacade
import frod.media.domain.Media
import frod.media.url.ImageKeyStringParser
import org.springframework.web.multipart.commons.CommonsMultipartFile
import frod.media.domain.MediaGroup

class MediaController {

    ImageKeyStringParser imageKeyStringParser;

    MediaFacade mediaFacade

    def index() {
        def allMedia = Media.findAll()
        [allMedia: allMedia, keyParser: imageKeyStringParser]
    }

    def upload() {
        MediaGroup group = MediaGroup.findAll()[0]
        CommonsMultipartFile uploadedFile = request.getFile('file')
        File savedFile = new File(uploadedFile.originalFilename)
        uploadedFile.transferTo(savedFile)
        mediaFacade.addMediaFromFile(savedFile, group.id)
        redirect(action: 'index')
    }
}
