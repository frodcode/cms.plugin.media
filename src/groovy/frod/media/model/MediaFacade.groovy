package frod.media.model

import frod.media.model.mapping.MappingRegister
import frod.media.domain.MediaGroup
import frod.media.model.processor.MediaCreationResult
import frod.media.domain.Media

/**
 * User: freeman
 * Date: 3.5.13
 */
class MediaFacade {

    MappingRegister mappingRegister

    MediaLocalFacade mediaLocalFacade

    public def addMediaFromUrl(String url, Long groupId)
    {

    }

    public Media findMediaById(Long id) {
        return Media.findById(id);
    }

    public List<Media> addMediaFromFile(String path, Long groupId) {
        File file = new File(path)
        return addMediaFromFile(file, groupId)
    }

    public List<Media> addMediaFromFile(File file, Long groupId) {
        if (!file.exists()) {
            throw new IllegalArgumentException(sprintf('File on path "%s" does not exist', file.getAbsolutePath()))
        }
        def mapping = mappingRegister.getMappingByFile(file)
        List<MediaCreationResult> results = mediaLocalFacade.createAssetFromFile(file, file.getName(), MediaGroup.findById(groupId))
        Media.withTransaction {
            results.each {result ->
                result.media.mediaImages*.save()
                result.media.save()
            }
            mapping.processor.process(file, results)
        }
    }

}
