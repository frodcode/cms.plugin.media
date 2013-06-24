package frod.media.download

import org.apache.http.HttpEntity

/**
 * User: freeman
 * Date: 24.6.13
 */
class DownloadedContent {

    int statusCode

    HttpEntity entity

    ByteArrayOutputStream contentOutputStream

    DownloadedContent(int statusCode, HttpEntity entity, ByteArrayOutputStream contentOutputStream) {
        this.statusCode = statusCode
        this.entity = entity
        this.contentOutputStream = contentOutputStream
    }
}
