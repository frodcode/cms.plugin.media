package frod.media.repository

import org.springframework.mail.javamail.ConfigurableMimeFileTypeMap;

/**
 * User: freeman
 * Date: 3.5.13
 */
class MimeTypeGuesser {

    ConfigurableMimeFileTypeMap configurableMimeFileTypeMap;

    public String getMimeType(byte[] content)
    {
        File file = new File()
        file.setBytes(content);
        return getMimeType(file);
    }

    public String getMimeType(File file)
    {
        return configurableMimeFileTypeMap.getContentType(file)
    }

}
