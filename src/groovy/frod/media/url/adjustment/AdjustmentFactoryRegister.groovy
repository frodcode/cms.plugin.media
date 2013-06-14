package frod.media.url.adjustment

import frod.media.url.adjustment.Factory.IAdjustmentFactory
import frod.media.image.thumbnail.adjustment.IAdjustment

/**
 * User: freeman
 * Date: 7.6.13
 */
class AdjustmentFactoryRegister {

    private List<IAdjustmentFactory> factoryEntries = []

    AdjustmentFactoryRegister(LinkedHashMap<String, IAdjustmentFactory> factories) {
        factories.each {
            register(it.key, it.value)
        }
    }

    public void register(String slug, IAdjustmentFactory factory) {
        if (hasFactoryEntry(slug)) {
            throw new IllegalArgumentException(sprintf('Factory with slug "%s" is already registered.', slug))
        }
        factoryEntries.add(new FactoryRegisterEntry(slug, factory))
    }

    public boolean hasFactoryEntry(String slug) {
        for (FactoryRegisterEntry entry in factoryEntries) {
            if (entry.slug == slug) {
                return true;
            }
        }
        return false;
    }

    public IAdjustmentFactory getFactoryBySlug(String slug) {
        if (!hasFactoryEntry(slug)) {
            throw new IllegalArgumentException(sprintf('Cannot find any suitable mapping for slug "%s"', slug))
        }
        for (FactoryRegisterEntry entry in factoryEntries) {
            if (slug == entry.slug) {
                return entry.factory
            }
        }
    }

    public FactoryRegisterEntry getFactoryEntryByAdjustment(IAdjustment adjustment) {
        for (FactoryRegisterEntry factoryEntry in factoryEntries) {
            if (factoryEntry.factory.canCreateUrlPart(adjustment)) {
                return factoryEntry
            }
        }
        throw new IllegalArgumentException(sprintf('Cannot find any suitable factory for adjustment with type "%s"', adjustment.class.name))
    }


}
