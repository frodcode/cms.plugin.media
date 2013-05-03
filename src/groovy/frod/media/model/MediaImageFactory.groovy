package frod.media.model

import frod.media.model.processor.MediaImageCreationResult
import frod.media.domain.MediaImage

/**
 * User: freeman
 * Date: 3.5.13
 */
class MediaImageFactory {


    public MediaImageCreationResult createMediaImage(byte[] content, String title)
    {
        MediaImage mediaImage = new MediaImage()
        String extFromFile = title.split('.')?.last()
        String ext
        if (extFromFile && (extFromFile.length() == 3 || extFromFile.length() == 4)) {
            ext = extFromFile
        } else {

        }
//        mediaImage.
    }

}
