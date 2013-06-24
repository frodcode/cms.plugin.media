package frod.media.repository

import javax.activation.FileDataSource
/**
 * User: freeman
 * Date: 3.5.13
 */
class FileExtensionGuesser {

    public String guessExtension(byte[] content)
    {
        FileDataSource fileDataSource = new FileDataSource()
        fileDataSource

    }

    public String getExtension(File file)
    {
        return file.getName().tokenize('.').last()
    }

    public String getExtension(URL url)
    {
        String ext = url.toString().tokenize('.').last()
        if (ext) {
            return ext;
        }
        throw new IllegalArgumentException(sprintf('Not implemented yet but should recognize extension from content type for url "%s"', url))
    }
}
