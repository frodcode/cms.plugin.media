package frod.media.model.mapping.youtube

import frod.media.model.processor.MediaCreationResult
import frod.media.download.DownloadedContent
import frod.media.utils.UrlUtils
import frod.media.domain.Image
import frod.media.domain.MediaImage
import frod.media.model.processor.MediaImageCreationResult
import frod.media.domain.Media
import frod.media.repository.MimeTypeGuesser
import frod.media.repository.FileExtensionGuesser
import frod.media.image.OriginalImageRepository
import frod.media.download.IContentDownloader
import frod.media.domain.Youtube

/**
 * User: freeman
 * Date: 26.6.13
 */
class YoutubeProcessor {

    FileExtensionGuesser fileExtensionGuesser

    OriginalImageRepository originalImageRepository

    IContentDownloader contentDownloader

    List<String> youtubeAddresses = ['www.youtube.com']

    List<String> youtubeImagePatterns = ['http://img.youtube.com/vi/%s/0.jpg', 'http://img.youtube.com/vi/%s/1.jpg', 'http://img.youtube.com/vi/%s/2.jpg']

    public List<MediaCreationResult> createAssetFromUrl(URL url) {
        DownloadedContent downloadedHtmlContent = contentDownloader.getContentFromUrl(url)
        String html = downloadedHtmlContent.contentOutputStream.toString('UTF-8')
        def tagsoupParser = new org.ccil.cowan.tagsoup.Parser()
        def slurper = new XmlSlurper(tagsoupParser)
        def parsedSlurper = slurper.parseText(html)
        String title = parsedSlurper."**".find {it.name() == 'title'}.text()

        List<MediaImageCreationResult> resultList = []
        Youtube youtube = new Youtube()
        String youtubeId = UrlUtils.parseUrlParams(url).v;
        youtube.youtubeId = youtubeId
        youtubeImagePatterns.each {
            URL imageUrl = sprintf(it, youtubeId).toURL()
            DownloadedContent downloadedContentImage = contentDownloader.getContentFromUrl(imageUrl)
            MediaImage mediaImage = new MediaImage()
            mediaImage.mimeType = downloadedContentImage.entity.contentType.value
            mediaImage.fileExtension = fileExtensionGuesser.getExtension(imageUrl)
            mediaImage.title = title
            MediaImageCreationResult mediaImageCreationResult = new MediaImageCreationResult(mediaImage, downloadedContentImage.contentOutputStream.toByteArray())
            resultList.add(mediaImageCreationResult)
        }

        return [new MediaCreationResult(youtube, resultList)]
    }

    public boolean canProcess(URL url) {
        if (!youtubeAddresses.contains(url.getHost())) {
            return false
        }
        if (!UrlUtils.parseUrlParams(url).containsKey('v')) {
            return false;
        }
        DownloadedContent downloadedContent = contentDownloader.getContentFromUrl(url)
        if (downloadedContent.statusCode != 200) {
            return false;
        }

        return true;
    }

    public List<Media> process(URL url, List<MediaCreationResult> mediaCreationResults) {
        mediaCreationResults[0].mediaImageCreationResults.each { imageResult ->
            originalImageRepository.save(imageResult.content, imageResult.mediaImage)
        }
        return [mediaCreationResults[0].media]
    }
}
