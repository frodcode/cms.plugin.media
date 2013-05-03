package frod.media.model

/**
 * User: freeman
 * Date: 3.5.13
 */
class MediaFactory {


    public def createMediaFromFileBy(byte[] content, String title, def mapping)
    {
        def processor = mapping.processor
        def result = processor.createAssetFromFileSource(content, title)
        return result
    }
}
