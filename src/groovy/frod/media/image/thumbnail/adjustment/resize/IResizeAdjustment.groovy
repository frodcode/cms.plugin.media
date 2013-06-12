package frod.media.image.thumbnail.adjustment.resize

import org.imgscalr.Scalr
import frod.media.image.thumbnail.adjustment.IAdjustment

/**
 * User: freeman
 * Date: 12.6.13
 */
interface IResizeAdjustment extends IAdjustment {

    public Integer getWidth();

    public Integer getHeight();

    public Scalr.Mode getMode();

    public Scalr.Method getMethod();

}
