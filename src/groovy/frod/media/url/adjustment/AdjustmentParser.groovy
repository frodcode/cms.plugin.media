package frod.media.url.adjustment

import frod.media.image.thumbnail.adjustment.IAdjustment
import frod.media.url.adjustment.Factory.IAdjustmentFactory

/**
 * User: freeman
 * Date: 7.6.13
 */
class AdjustmentParser {

    public static String ADJUSTING_GLUE = '_';

    public static String TYPE_PARAM_GLUE = '-';

    AdjustmentFactoryRegister adjustmentFactoryRegister

    public List<IAdjustment> createAdjustments(String adjustmentString) {
        def adjustmentParts = adjustmentString.tokenize(ADJUSTING_GLUE)
        List<IAdjustment> allAdjustments = []
        for(String part in adjustmentParts) {
            allAdjustments.add(createAdjustmentForPart(part))
        }
        return allAdjustments;
    }

    private def createAdjustmentForPart(part) {
        def splitParts = part.tokenize(TYPE_PARAM_GLUE)
        def type = splitParts[0];
        splitParts.remove(0);
        def params = splitParts.join(TYPE_PARAM_GLUE);
        return createAdjustmentFor(type, params);
    }

    private IAdjustment createAdjustmentFor(String type, String params) {
        IAdjustmentFactory factory = adjustmentFactoryRegister.getFactoryBySlug(type)
        return factory.create(params);
    }

}
