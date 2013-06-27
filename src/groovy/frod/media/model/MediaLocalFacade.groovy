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

    MappingRegister mappingRegister

    MediaFactory mediaFactory

    public List<MediaCreationResult> createAssetFromFile(File file, String title, MediaGroup mediaGroup)
    {
        def mapping = mappingRegister.getMappingByFile(file)
        List<MediaCreationResult> results = mediaFactory.createMediaFromFileBy(file, title, mapping)
        return saveResults(results, mediaGroup, mapping)
    }

    public List<MediaCreationResult> createAssetFromUrl(URL url, MediaGroup mediaGroup)
    {
        def mapping = mappingRegister.getMappingByUrl(url)
        List<MediaCreationResult> results = mapping.processor.createAssetFromUrl(url)
        return saveResults(results, mediaGroup, mapping)
    }

    private List<MediaCreationResult> saveResults(List<MediaCreationResult> results, MediaGroup mediaGroup, def mapping) {
        results.each {
            it.getMedia().setMediaGroup(mediaGroup)
            def position = 1;
            it.mediaImageCreationResults*.mediaImage.each{ mediaImage->
                mediaImage.position = position;
                it.getMedia().addToMediaImages(mediaImage)
                position++;
            }
            it.getMedia().setTypeSlug(mapping.getSlug())
        }

        return results
    }

}
