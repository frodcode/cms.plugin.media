package frod.media.utils

import java.text.Normalizer

/**
 * User: freeman
 * Date: 14.6.13
 */
class StringUtils {

    public static String webalize(String webPath) {
//        String webPath = Normalizer
//                .normalize(s, Normalizer.Form.NFD)
//                .replaceAll("[^\\p{ASCII}]", "")
        webPath = webPath.toLowerCase()
        webPath = Normalizer.normalize(webPath, java.text.Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
        webPath = (webPath =~ /[^a-z0-9]/).replaceAll("-")
        while(webPath.contains('--')) {
            webPath = (webPath =~ /--/).replaceAll("-")
        }
        if (webPath.endsWith('-')) {
            webPath = webPath[0..-2]
        }
        return webPath
    }
}
