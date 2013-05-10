package frod.media.model

import frod.media.model.processor.MediaCreationResult

/**
 * User: freeman
 * Date: 3.5.13
 */
class MediaFactory {


    public MediaCreationResult createMediaFromFileBy(File file, String title, def mapping)
    {
        def processor = mapping.processor
        def result = processor.createAssetFromFile(file, title)
        return result
    }
}
