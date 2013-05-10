import org.codehaus.groovy.grails.commons.ApplicationAttributes
import frod.media.domain.Image
import frod.media.domain.Youtube
import javax.servlet.ServletContext
import frod.media.domain.Media

class BootStrap {
	def init = { ServletContext servletContext ->
		def ctx = servletContext.getAttribute(ApplicationAttributes.APPLICATION_CONTEXT)
//        Image image = new Image();
//        println 'saving image'
//        Image.findAll()
//        image.save(failOnError: true);
//        Youtube youtube = new Youtube()
//        youtube.youtubeId = 'aa';
//        youtube.save(flush: true)
//
//        println Media.findAll()

	}
}
