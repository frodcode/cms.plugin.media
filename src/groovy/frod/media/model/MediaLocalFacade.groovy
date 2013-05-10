package frod.media.model

import frod.media.domain.MediaGroup
import frod.media.model.mapping.MappingRegister
import org.apache.tika.mime.MimeType
import frod.media.model.processor.MediaCreationResult

/**
 * User: freeman
 * Date: 3.5.13
 */
class MediaLocalFacade {

    private MappingRegister mappingRegister

    private MediaFactory mediaFactory

    public List<MediaCreationResult> createAssetFromFile(File file, String title, MediaGroup mediaGroup)
    {
        def mapping = mappingRegister.getMappingByFile(file)
        List<MediaCreationResult> results = mediaFactory.createMediaFromFileBy(file, title, mapping)
        results.each {
            it.getMedia().setMediaGroup(mediaGroup)
            it.getMedia().setMediaImages(it.mediaImageCreationResults*.getMediaImage())
            it.getMedia().setTypeSlug(mapping.getSlug())
        }

        return results
    }

}
