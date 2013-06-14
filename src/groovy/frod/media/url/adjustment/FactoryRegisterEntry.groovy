package frod.media.url.adjustment

import frod.media.url.adjustment.Factory.IAdjustmentFactory

/**
 * User: freeman
 * Date: 14.6.13
 */
class FactoryRegisterEntry {

    def slug

    def IAdjustmentFactory factory

    FactoryRegisterEntry(slug, IAdjustmentFactory factory) {
        this.slug = slug
        this.factory = factory
    }
}
