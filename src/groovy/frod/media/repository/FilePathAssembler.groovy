package frod.media.repository

/**
 * User: freeman
 * Date: 3.5.13
 */
class FilePathAssembler {

    public String getPath(String root, String key) {
        String fullPathDir = getDirPath(root, key)
        String fullPath = fullPathDir + key

        return fullPath
    }

    public String getDirPath(String root, String key) {
        if (key.length() < 4) {
            throw new IllegalArgumentException(sprintf('Cannot create path to file with given key "%s". Key is too short. It must have at least 4 chars', key))
        }
        String dir = key.substring(0, 2) + "/" + key.substring(2, 4) + '/';
        if (root.endsWith('/')) {
            root = root[0..-1]
        }
        String fullPathDir = root + '/' + dir
        return fullPathDir;
    }

}
