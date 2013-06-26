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

    public static LinkedHashMap<String, Object> parseUrlParams(URL url) {
        return url.query.split('&').inject([:]) {map, kv -> def (key, value) = kv.split('=').toList(); map[key] = value != null ? URLDecoder.decode(value) : null; map }
    }

}
