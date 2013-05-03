package frod.media.image

import java.security.MessageDigest

/**
 * User: freeman
 * Date: 3.5.13
 */
class ImageKeyToRepoKeyConverter {


    public def convert(ImageKey imageKey) {
        if (!imageKey.id) {
            throw new IllegalArgumentException('Image key must contnt')
        }
        String title = decompose(imageKey.title)
        MessageDigest md = MessageDigest.getInstance("MD5");
        String key = md.digest(imageKey.id.toString()).toString().substring(0, 7) + '-' +
                imageKey.id + '-' +
                title;
        if (imageKey.fileExtension) {
            key += '-' + '.' + imageKey.fileExtension
        }
        return key
    }

    private String decompose(String s) {
        return java.text.Normalizer.normalize(s, java.text.Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}
