package frod.media.model

import frod.media.model.processor.MediaCreationResult
import frod.media.model.mapping.Mapping

/**
 * User: freeman
 * Date: 3.5.13
 */
class MediaFactory {


    public List<MediaCreationResult> createMediaFromFileBy(File file, String title, Mapping mapping)
    {
        def processor = mapping.processor
        return processor.createAssetFromFile(file, title)
    }

    public List<MediaCreationResult> createMediaFromUrlBy(URL url, String title, Mapping mapping)
    {
        def processor = mapping.processor
        return processor.createAssetFromUrl(url, title)
    }
}
