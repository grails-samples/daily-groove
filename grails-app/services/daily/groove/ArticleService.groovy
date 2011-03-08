package daily.groove

class ArticleService {

    static transactional = true

	def redis
    def loadFeed(rss) {
		rss.channel.item.each { item ->
			def a = new Article(title:item.title.text(),
						body:item.description.text(),
						link:new URL(item.link.text())).save(failOnError:true)
		}
    }
}
