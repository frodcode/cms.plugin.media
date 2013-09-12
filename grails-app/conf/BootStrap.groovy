import org.codehaus.groovy.grails.commons.ApplicationAttributes
import frod.media.domain.Image
import frod.media.domain.Youtube
import javax.servlet.ServletContext
import frod.media.domain.Media
import frod.media.domain.MediaGroup
import frod.media.model.MediaFacade
import frod.media.domain.MediaGroupType

class BootStrap {
	def init = { ServletContext servletContext ->
		def ctx = servletContext.getAttribute(ApplicationAttributes.APPLICATION_CONTEXT)
        MediaFacade mediaFacade = ctx.mediaFacade

        MediaGroupType defaultType = new MediaGroupType(name:'výchozí')
        defaultType.save(flush:true)

        String userDir = System.getProperty("user.dir");

        MediaGroup mediaGroup = new MediaGroup()
        mediaGroup.setName('Example group')
        mediaGroup.description = 'example group'
        mediaGroup.setType(defaultType)
        mediaGroup.save(flush: true);

        List<Media> medias = mediaFacade.addMediaFromFile(userDir+'/data/exampleData/grails.jpg', mediaGroup.id)
        mediaFacade.addMediaFromFile(userDir+'/data/exampleData/land.jpg', mediaGroup.id)
        mediaFacade.addMediaFromFile(userDir+'/data/exampleData/big-landscape.jpg', mediaGroup.id)
	}
}
