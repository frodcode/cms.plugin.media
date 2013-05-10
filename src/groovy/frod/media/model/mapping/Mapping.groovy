package frod.media.model.mapping

/**
 * User: freeman
 * Date: 3.5.13
 */
class Mapping {

    def processor

    String slug;

    public Mapping(processor, String slug) {
        this.processor = processor
        this.slug = slug
    }
}
