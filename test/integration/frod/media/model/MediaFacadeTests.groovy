package frod.media.model

import static org.junit.Assert.*
import org.junit.*
import frod.media.domain.MediaGroup
import frod.media.domain.Media
import frod.media.image.OriginalImageRepository

class MediaFacadeTests {

    def grailsApplication

    MediaFacade mediaFacade

    OriginalImageRepository originalImageRepository

    String userDir = System.getProperty("user.dir");

    @Before
    void setUp() {
        mediaFacade = grailsApplication.mainContext['mediaFacade']
        originalImageRepository = grailsApplication.mainContext['originalImageRepository']
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    MediaGroup getTestingMediaGroup()
    {
        MediaGroup mediaGroup = new MediaGroup()
        mediaGroup.setName('test media group')
        mediaGroup.description = 'testing media group'
        mediaGroup.save();
        return mediaGroup;
    }

    @Test
    void testAddMediaImageFromFile() {
        MediaGroup mediaGroup = getTestingMediaGroup()
        List<Media> medias = mediaFacade.addMediaFromFile(userDir+'/data/test/profil.jpg', mediaGroup.id)
        Media media = medias[0]
        println media.getMainImage()
        assertTrue originalImageRepository.exists(media.getMainImage())
        originalImageRepository.remove(media.getMainImage())
        assertFalse originalImageRepository.exists(media.getMainImage())
    }
}
