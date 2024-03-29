import javax.activation.MimetypesFileTypeMap
import org.springframework.mail.javamail.ConfigurableMimeFileTypeMap
import frod.media.image.thumbnail.adjustment.AdjustmentProcessorRegister
import frod.media.image.thumbnail.adjustment.crop.CropProcessor
import frod.media.image.thumbnail.repository.ThumbnailRepository
import frod.media.image.thumbnail.repository.ThumbnailGenerator
import frod.media.url.adjustment.Factory.CropAdjustmentFactory
import frod.media.url.adjustment.AdjustmentFactoryRegister
import frod.media.url.adjustment.AdjustmentParser
import frod.media.url.ImageKeyStringParser
import frod.media.image.thumbnail.adjustment.resize.ResizeProcessor
import frod.media.url.adjustment.Factory.ResizeAdjustmentFactory
import frod.media.MediaTagLib

class MediaGrailsPlugin {
    // the plugin version
    def version = "1.6"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.2 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Media Plugin" // Headline display name of the plugin
    def author = "Your name"
    def authorEmail = "franta.odehnal@gmail.com"
    def description = '''\
Media of any type
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/media"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
//    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.codehaus.org/grails-plugins/" ]

    def doWithWebDescriptor = { webXml ->
    }

    def doWithSpring = {

        configurableMimeFileTypeMap(ConfigurableMimeFileTypeMap)

        def userDir = System.getProperty("user.dir");
        if (application.config.grails.media.preferences?.imageDir) {
            userDir = application.config.grails.media.preferences.imageDir
        }
        mediaFacade(frod.media.model.MediaFacade) {
            mappingRegister = ref('mappingRegister')
            mediaLocalFacade = ref('mediaLocalFacade')
        }
        mediaLocalFacade(frod.media.model.MediaLocalFacade) {
            mappingRegister = ref('mappingRegister')
            mediaFactory = ref('mediaFactory')
        }
        mappingRegister(frod.media.model.mapping.MappingRegister,
                [image : ref('imageProcessor')],
                [image : ref('imageProcessor')]) {

        }
        mediaFactory(frod.media.model.MediaFactory) {

        }

        // processors
        imageProcessor(frod.media.model.mapping.image.ImageProcessor) {
            mimeTypeGuesser = ref('mimeTypeGuesser')
            fileExtensionGuesser = ref('fileExtensionGuesser')
            originalImageRepository = ref('originalImageRepository')
        }

        mimeTypeGuesser(frod.media.repository.MimeTypeGuesser) {
            configurableMimeFileTypeMap = ref('configurableMimeFileTypeMap');
        }

        fileExtensionGuesser(frod.media.repository.FileExtensionGuesser) {

        }

        // images and files
        repository_original_image(frod.media.repository.FileRepository, userDir+'/data/images/', '0775', '0775', 'freeman') {
            filePathAssembler = ref('filePathAssembler')
        }

        filePathAssembler(frod.media.repository.FilePathAssembler) {

        }

        originalImageRepository(frod.media.image.OriginalImageRepository) {
            fileRepository = ref('repository_original_image')
            imageKeyToRepoKeyConverter = ref('imageKeyToRepoKeyConverter')
        }

        imageKeyToRepoKeyConverter(frod.media.image.ImageKeyToRepoKeyConverter)

        cropProcessor(CropProcessor)

        resizeProcessor(ResizeProcessor)

        adjustmentProcessorRegister(AdjustmentProcessorRegister, ['crop': ref('cropProcessor'), 'resize': ref('resizeProcessor')]) {
        }

        thumbnailRepository(ThumbnailRepository) {
            originalImageRepository = ref('originalImageRepository')
            thumbnailGenerator = ref('thumbnailGenerator')
        }

        thumbnailGenerator(ThumbnailGenerator) {
            adjustmentProcessorRegister = ref('adjustmentProcessorRegister')
        }

        cropAdjustmentFactory(CropAdjustmentFactory)

        resizeAdjustmentFactory(ResizeAdjustmentFactory)

        adjustmentFactoryRegister(AdjustmentFactoryRegister, ['crop': ref('cropAdjustmentFactory'), 'resize': ref('resizeAdjustmentFactory')]) {
        }

        adjustmentParser(AdjustmentParser) {
            adjustmentFactoryRegister = ref('adjustmentFactoryRegister')
        }

        imageKeyStringParser(ImageKeyStringParser) {
            adjustmentParser = ref('adjustmentParser');
        }

        'frod.media.MediaTagLib'(MediaTagLib) {
            imageKeyStringParser = ref('imageKeyStringParser')
        }

    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
