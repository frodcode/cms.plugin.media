package frod.media.repository

/**
 * User: freeman
 * Date: 3.5.13
 */
class FileRepository {

    File root

    String fileMod

    String dirMod

    String group

    FilePathAssembler filePathAssembler

    public FileRepository(String root, String fileMod, String dirMod, String group) {
        this.root = new File(root)
        if (!this.root.exists()) {
            throw new IllegalArgumentException(sprintf('File repository root directory is set to be at path "%s" but the dir does not exist', root))
        }
        if (!this.root.canWrite()) {
            throw new IllegalArgumentException(sprintf('File repository root directory must be writable. Given path "%s" is not', root))
        }

        this.fileMod = fileMod
        this.dirMod = dirMod
        this.group = group
    }

    public void save(byte[] content, def key)
    {
        String path = getPath(key)
        File dir = new File(getDirPath(key))
        if (!dir.exists()) {
            dir.mkdirs()
        }
        new FileOutputStream(path).write(content);
    }

    private String getDirPath(key) {
        return filePathAssembler.getDirPath(this.root.getPath(), key);
    }

    public Boolean exists(def key)
    {
        File file = new File(getPath(key))
        return file.exists()
    }

    private String getPath(def key)
    {
        return filePathAssembler.getPath(this.root.getPath(), key);
    }

    private void checkExists(key) {
        if (!exists(key)) {
            throw new FileNotFoundException(sprintf('Cannot find any file registered by key "%s"', key))
        }
    }

    public byte[] load(def key)
    {
        checkExists(key)
        FileInputStream fileInputStream = new FileInputStream(getPath(key))
        return fileInputStream.getBytes()
    }

    public void delete(def key)
    {
        checkExists(key)
        File file = new File(getPath(key))
        file.delete()
    }

}
