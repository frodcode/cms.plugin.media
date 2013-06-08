package frod.media.url.adjustment

import frod.media.url.adjustment.Factory.IAdjustmentFactory

/**
 * User: freeman
 * Date: 7.6.13
 */
class AdjustmentFactoryRegister {

    private LinkedHashMap<String, IAdjustmentFactory> factories = [:]

    AdjustmentFactoryRegister(LinkedHashMap<String, IAdjustmentFactory> factories) {
        factories.each {
            register(it.key, it.value)
        }
    }

    public void register(String slug, IAdjustmentFactory factory) {
        if (hasFactory(slug)) {
            throw new IllegalArgumentException(sprintf('Factory with slug "%s" is already registered.', slug))
        }
        factories[slug] = factory
    }

    public boolean hasFactory(String slug) {
        if (factories[slug]) {
            return true;
        }
        return false;
    }

    public IAdjustmentFactory getFactoryBySlug(String slug) {
        if (!hasFactory(slug)) {
            throw new IllegalArgumentException(sprintf('Cannot find any suitable mapping for slug "%s"', slug))
        }
        return factories[slug]
    }
}
