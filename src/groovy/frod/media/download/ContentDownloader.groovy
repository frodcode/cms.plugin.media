package frod.media.download

import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient

/**
 * User: freeman
 * Date: 23.6.13
 */
class ContentDownloader implements IContentDownloader {

    public DownloadedContent getContentFromUrl(URL url) {
        HttpGet req = new HttpGet(new URI(url.toString()));
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(req);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        outputStream  << response.entity.getContent();
        return new DownloadedContent(response.getStatusLine().statusCode, response.entity, outputStream)
    }
}
