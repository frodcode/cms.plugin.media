package frod.media.model.mapping

/**
 * User: freeman
 * Date: 3.5.13
 */
class MappingRegister {

    private fileMappings = [:]

    private urlMappings = [:]

    MappingRegister(LinkedHashMap<String, Object> fileMappingsToRegister, LinkedHashMap<String, Object> urlMappingsToRegister) {
        fileMappingsToRegister.each {
            registerFileMapping(it.key, it.value)
        }
        urlMappingsToRegister.each {
            registerUrlMapping(it.key, it.value)
        }
    }

    public void registerFileMapping(String slug, def processor)
    {
        if (hasFileMapping(slug)) {
            throw new IllegalArgumentException(sprintf('File processor with slug "%s" is already registered.', slug))
        }
        fileMappings[slug] = new Mapping(processor, slug)
    }

    public boolean hasFileMapping(String slug)
    {
        if (fileMappings[slug]) {
            return true;
        }
        return false;
    }

    public void registerUrlMapping(String slug, def processor)
    {
        if (hasUrlMapping(slug)) {
            throw new IllegalArgumentException(sprintf('Url processor with slug "%s" is already registered.', slug))
        }
        urlMappings[slug] = new Mapping(processor, slug)
    }

    public boolean hasUrlMapping(String slug)
    {
        if (urlMappings[slug]) {
            return true;
        }
        return false;
    }

    public Mapping getMappingByFile(File file)
    {
        Mapping mapping = fileMappings.find {
            it.value.processor.canProcess(file)
        }?.value
        if (!mapping) {
            throw new IllegalArgumentException('Cannot find any suitable mapping for given file')
        }
        return mapping;
    }

    public Mapping getMappingByUrl(URL url)
    {
        Mapping mapping = urlMappings.find {
            it.value.processor.canProcess(url)
        }?.value
        if (!mapping) {
            throw new IllegalArgumentException(sprintf('Cannot find any suitable mapping for given url "%s"', url.toString()))
        }
        return mapping;
    }
}
