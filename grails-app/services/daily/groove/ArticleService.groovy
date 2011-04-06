package daily.groove

class ArticleService {

    def loadFeed(rss) {
        for (item in rss.channel.item) {
            def a = new Article(title: item.title.text(),
                    body: item.description.text(),
                    link: new URL(item.link.text())).save()
        }
    }
}
