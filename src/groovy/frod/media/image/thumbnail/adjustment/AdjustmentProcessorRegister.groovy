package frod.media.image.thumbnail.adjustment

/**
 * User: freeman
 * Date: 7.6.13
 */
class AdjustmentProcessorRegister {

    LinkedHashMap<String, IAdjustmentProcessor> processors = [:]

    AdjustmentProcessorRegister(LinkedHashMap<String, IAdjustmentProcessor> processors) {
        processors.each{
            register(it.key, it.value)
        }
    }

    public void register(String slug, IAdjustmentProcessor processor) {
        if (processors.containsKey(slug)) {
            throw new IllegalArgumentException(sprintf('Processor with slug "%s" is already registered', slug))
        }
        processors[slug] = processor;
    }

    public boolean hasBySlug(String slug) {
        return processors.containsKey(slug)
    }

    public IAdjustmentProcessor getBySlug(String slug) {
        if (!hasBySlug(slug)) {
            throw new IllegalArgumentException(sprintf('Processor with slug "%s" is not registered', slug))
        }
        return processors[slug]
    }

    public IAdjustmentProcessor getByAdjustment(IAdjustment adjusting) {
        for (IAdjustmentProcessor processor in processors.values()) {
            if (processor.canProcess(adjusting)) {
                return processor
            }
        }
        throw new IllegalArgumentException(sprintf('Cannot find processor for adjustment "%s"', adjusting.class.name))
    }

}
