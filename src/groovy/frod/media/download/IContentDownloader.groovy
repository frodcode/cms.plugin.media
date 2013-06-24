package frod.media.download

import org.apache.http.HttpResponse

/**
 * User: freeman
 * Date: 23.6.13
 */
public interface IContentDownloader {
    public DownloadedContent getContentFromUrl(URL url)
}