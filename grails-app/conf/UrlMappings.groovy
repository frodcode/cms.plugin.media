class UrlMappings {

	static mappings = {
        name dynamicImage: "/dynamic-media/image/$id?"{
            controller = 'ImageService'
            action = 'index'
            constraints {
                // apply constraints here
            }
        }
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
