package daily.groove

import static daily.groove.Constants.*

class ArticleController {
    private static final SAMPLE_FEEDS = [
            "http://feeds.bbci.co.uk/news/rss.xml": "BBC News",
            "http://www.theregister.co.uk/headlines.rss": "The Register",
            "http://www.theonion.com/feeds/daily/": "The Onion",
            "http://grails.org/plugin/latest?format=rss": "Grails Plugins",
            "http://groovyblogs.org/feed/rss": "Groovy Blogs",
            "http://rss.slashdot.org/Slashdot/slashdot": "Slashdot",
            "http://blog.springsource.com/feed/": "SpringSource Team Blog"]

    def articleService
    def redis

    def index = { 
        redirect action:'all'
    }
    
    def addFeed = {
        try {
            articleService.loadFeed(params.url)
        }
        catch(e) {
            flash.message = "Error processing feed. Please try again later."
            log.error("Error processing feed: ${e.message}", e)
        }
        redirect action: "all"        
    }
    
    def all = {
        def sort = [sort:"dateCreated", order:"desc", max:10]
        def unreadItems = Article.findAllByUnread(true, sort)
        
        [ latestArticles:Article.list(sort),
          unreadItems:unreadItems,
          sampleFeed:articleService.randomSampleFeed(),
          subscribedFeeds:articleService.subscribedFeeds()*.name]
    }
    
    def show = {
        def article = Article.get(params.id)
        
        render template:"articlePreview", model:[article:article]
    }
    
    def read = {
        def article = Article.get(params.id)
        article.unread = false
        article.save(flush:true)
        
        render template:"articlePreview", model:[article:article]
    }
    
    def sampleFeed = {
        render template:"sampleFeed", model:[sampleFeed:articleService.randomSampleFeed()]
    }
    
    def loadData = {
        if (!redis.scard(SAMPLE_FEEDS_KEY)) {
            SAMPLE_FEEDS.each { url, name ->
                redis.set url, name
                redis.sadd SAMPLE_FEEDS_KEY, url
            }
        }
        
        redirect action: "all"
    }
}
