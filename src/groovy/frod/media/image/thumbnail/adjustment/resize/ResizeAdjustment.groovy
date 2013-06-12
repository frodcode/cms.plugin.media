package frod.media.image.thumbnail.adjustment.resize

import org.imgscalr.Scalr

/**
 * User: freeman
 * Date: 12.6.13
 */
class ResizeAdjustment implements IResizeAdjustment {

    private Integer width;

    private Integer height;

    private Scalr.Mode mode;

    private Scalr.Method method;

    ResizeAdjustment(Integer width, Integer height, Scalr.Mode mode, Scalr.Method method) {
        this.width = width
        this.height = height
        this.mode = mode
        this.method = method
    }

    ResizeAdjustment(Integer width, Integer height, Scalr.Mode mode) {
        this.width = width
        this.height = height
        this.mode = mode
        this.method = Scalr.Method.AUTOMATIC;
    }

    @Override
    Integer getWidth() {
        return width  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Integer getHeight() {
        return height  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Scalr.Mode getMode() {
        return mode  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Scalr.Method getMethod() {
        return method  //To change body of implemented methods use File | Settings | File Templates.
    }
}
