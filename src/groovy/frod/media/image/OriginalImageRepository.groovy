package frod.media.image

import frod.media.repository.FileRepository

/**
 * User: freeman
 * Date: 3.5.13
 */
class OriginalImageRepository {

    FileRepository fileRepository

    ImageKeyToRepoKeyConverter imageKeyToRepoKeyConverter

    public void save(byte[] content, ImageKey imageKey)
    {
        String key = getRepoKeyFromImageKey(imageKey)
        fileRepository.save(content, key)
    }

    public void load(ImageKey imageKey)
    {
        String key = getRepoKeyFromImageKey(imageKey)
        fileRepository.load(key)
    }

    public boolean exists(ImageKey imageKey)
    {
        String key = getRepoKeyFromImageKey(imageKey)
        return fileRepository.exists(key)
    }

    public void remove(ImageKey imageKey)
    {
        String key = getRepoKeyFromImageKey(imageKey)
        fileRepository.remove(key)
    }

    private String getRepoKeyFromImageKey(ImageKey imageKey) {
        return imageKeyToRepoKeyConverter.convert(imageKey)
    }

}
