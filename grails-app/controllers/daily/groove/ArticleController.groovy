package daily.groove

class ArticleController {
    def articleService

    def index = { 
        redirect action:'all'
    }
    
    def addFeed = {
        try {
            articleService.loadFeed(new XmlSlurper().parseText(new URL(params.url).text))
        }
        catch(e) {
            flash.message = "Error processing feed. Please try again later."
            log.error("Error processing feed: ${e.message}", e)
        }
        redirect(action:'all')        
    }
    
    def all = {
        def sort = [sort:"dateCreated", order:"desc", max:10]
        def unreadItems = Article.findAllByUnread(true, sort)
        [latestArticles:Article.list(sort), unreadItems:unreadItems]
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
    def test = {
        render "all = ${Article.countByUnread(true)}"
    }
}
