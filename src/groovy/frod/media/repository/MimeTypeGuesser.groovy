package frod.media.repository

import javax.activation.MimetypesFileTypeMap;

/**
 * User: freeman
 * Date: 3.5.13
 */
class MimeTypeGuesser {


    public String getMimeType(byte[] content)
    {
        File file = new File()
        file.setBytes(content);
        return getMimeType(file);
    }

    public String getMimeType(File file)
    {
        MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap()
        return mimetypesFileTypeMap.getContentType(file)
    }

}
