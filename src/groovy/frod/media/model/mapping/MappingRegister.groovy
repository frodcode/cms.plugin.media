package frod.media.model.mapping

import frod.media.domain.Media

/**
 * User: freeman
 * Date: 3.5.13
 */
class MappingRegister {

    private mappings = []

    public Mapping<Media> getMappingByFile(InputStream inputStream)
    {
        def mapping = mappings.find {
            it.processor.canProcess(inputStream)
        }
        if (!mapping) {
            throw new IllegalArgumentException('Cannot find any suitable mapping for given file')
        }
        return mapping;
    }
}
