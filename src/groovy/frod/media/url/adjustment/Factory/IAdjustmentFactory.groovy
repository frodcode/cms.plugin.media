package frod.media.url.adjustment.Factory

import frod.media.image.thumbnail.adjustment.IAdjustment

/**
 * User: freeman
 * Date: 7.6.13
 */
public interface IAdjustmentFactory {

    public IAdjustment create(String params);
}