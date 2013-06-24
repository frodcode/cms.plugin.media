package frod.media.utils

/**
 * User: freeman
 * Date: 23.6.13
 */
class UrlUtils {

    public static String guessTitleFromUrl(URL url) {
        String title = url.getPath().tokenize('/').last()
        if (!title) {
            return url.getPath();
        }
        String betterTitle = title.tokenize('.').first()
        if (!betterTitle)  {
            return title
        }
        return URLDecoder.decode(betterTitle.replaceAll('-', ' ').replaceAll('_', ' '))
    }

}
