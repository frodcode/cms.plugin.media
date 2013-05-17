package frod.media.model.mapping

import frod.media.domain.Media

/**
 * User: freeman
 * Date: 3.5.13
 */
class MappingRegister {

    private mappings = [:]

    MappingRegister(LinkedHashMap<String, Object> mappingsToRegister) {
        mappingsToRegister.each {
            register(it.key, it.value)
        }
    }

    public void register(String slug, def processor)
    {
        if (hasMapping(slug)) {
            throw new IllegalArgumentException(sprintf('Processor with slug "%s" is already registered.', slug))
        }
        mappings[slug] = new Mapping(processor, slug)
    }

    public boolean hasMapping(String slug)
    {
        if (mappings[slug]) {
            return true;
        }
        return false;
    }

    public Mapping getMappingByFile(File file)
    {
        Mapping mapping = mappings.find {
            it.value.processor.canProcess(file)
        }?.value
        if (!mapping) {
            throw new IllegalArgumentException('Cannot find any suitable mapping for given file')
        }
        return mapping;
    }
}
