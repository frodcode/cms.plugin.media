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

    String testGroupName = 'test media group'

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
        MediaGroup mediaGroup = MediaGroup.findByName(testGroupName)
        if (mediaGroup) {
            return mediaGroup
        }
        mediaGroup = new MediaGroup()
        mediaGroup.setName(testGroupName)
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

    @Test
    void testAddMediaImageFromUrl() {
        MediaGroup mediaGroup = getTestingMediaGroup()
        List<Media> medias = mediaFacade.addMediaFromUrl('http://images5.fanpop.com/image/photos/25600000/DOG-ssssss-dogs-25606625-1024-768.jpg', mediaGroup.id)
        Media media = medias[0]
        println media.getMainImage()
        assertTrue originalImageRepository.exists(media.getMainImage())
        originalImageRepository.remove(media.getMainImage())
        assertFalse originalImageRepository.exists(media.getMainImage())
    }
}
