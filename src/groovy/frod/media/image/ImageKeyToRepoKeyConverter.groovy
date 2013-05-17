package frod.media.image

import java.security.MessageDigest
import javax.xml.bind.annotation.adapters.HexBinaryAdapter
import grails.util.GrailsUtil

/**
 * User: freeman
 * Date: 3.5.13
 */
class ImageKeyToRepoKeyConverter {


    public def convert(ImageKey imageKey) {
        if (!imageKey.id) {
            throw new IllegalArgumentException('Image key must content id')
        }
        String title = decompose(imageKey.title)
        MessageDigest md = MessageDigest.getInstance("MD5");
        //md.digest(imageKey.id.toString().bytes)
        String md5 = (new HexBinaryAdapter()).marshal(md.digest(imageKey.id.toString().getBytes()))
        String shortedMd5 = md5.substring(0, 7);
        String key =  shortedMd5 + '-' +
                imageKey.id + '-' +
                title;
        if (imageKey.fileExtension) {
            key += '.' + imageKey.fileExtension
        }
        return key
    }

    private String decompose(String s) {
        return java.text.Normalizer.normalize(s, java.text.Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "").replace('.', '-')
    }
}
