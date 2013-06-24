package frod.media.download

import org.apache.http.HttpResponse

/**
 * User: freeman
 * Date: 23.6.13
 */
class CachedContentDownloader implements IContentDownloader {

    IContentDownloader contentDownloader

    def knownUrls = [:]

    @Override
    DownloadedContent getContentFromUrl(URL url) {
        dump(this)
        if (knownUrls.containsKey(url)) {
            return knownUrls[url]
        }
        DownloadedContent content = contentDownloader.getContentFromUrl(url)
        knownUrls[url] = content
        return content
    }

    void cleanCache() {
        knownUrls = [:]
    }

    void cleanCache(URL url) {
        if (knownUrls.containsKey(url)) {
            knownUrls.remove(url)
        }
    }

}
