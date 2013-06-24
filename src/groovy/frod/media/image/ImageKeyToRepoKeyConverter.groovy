package frod.media.image

import java.security.MessageDigest
import javax.xml.bind.annotation.adapters.HexBinaryAdapter
import frod.media.utils.StringUtils

/**
 * User: freeman
 * Date: 3.5.13
 */
class ImageKeyToRepoKeyConverter {

    public def convert(ImageKey imageKey) {
        if (!imageKey.getId()) {
            throw new IllegalArgumentException('Image key must content id')
        }
        String title = StringUtils.webalize(imageKey.title)
        MessageDigest md = MessageDigest.getInstance("MD5");
        String md5 = (new HexBinaryAdapter()).marshal(md.digest(imageKey.getId().toString().getBytes()))
        String shortedMd5 = md5.substring(0, 7);
        String key =  shortedMd5 + '-' +
                imageKey.getId() + '-' +
                title;
        if (imageKey.fileExtension) {
            key += '.' + imageKey.fileExtension
        }
        return key.toLowerCase()
    }

}
