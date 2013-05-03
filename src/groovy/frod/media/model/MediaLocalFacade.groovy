package frod.media.model

import frod.media.domain.MediaGroup
import frod.media.model.mapping.MappingRegister
//import org.apache.tika.mime

/**
 * User: freeman
 * Date: 3.5.13
 */
class MediaLocalFacade {

    private MappingRegister mappingRegister

    private MediaFactory mediaFactory

    public def createAssetFromFile(byte[] content, String title, String mimeType, String fileExtension, MediaGroup mediaGroup)
    {
        def mapping = mappingRegister.getMappingByFile(content)

        def result = mediaFactory.createMediaFromFileBy(content, title, mapping)

    }

}
