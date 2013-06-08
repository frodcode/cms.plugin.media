package frod.media.url

import frod.media.image.ImageKey

/**
 * User: freeman
 * Date: 7.6.13
 */
class UrlImageKey implements ImageKey {

    private Integer id

    private String title

    private String fileExtension

    UrlImageKey(int id, String title, String fileExtension) {
        this.id = id
        this.title = title
        this.fileExtension = fileExtension
    }

    Integer getId() {
        return id
    }

    String getTitle() {
        return title
    }

    String getFileExtension() {
        return fileExtension
    }
}
