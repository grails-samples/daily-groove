package daily.groove

import static daily.groove.Constants.*

class ArticleService {
    def redis

    def loadFeed(url, rss) {
        for (item in rss.channel.item) {
            def a = new Article(title: item.title.text(),
                    body: item.description.text(),
                    link: new URL(item.link.text())).save()
        }
        
        if (!redis.exists(url)) {
            redis.set url, rss.channel.title.text()
        }
        redis.sadd SUBSCRIBED_FEEDS_KEY, url
    }
    
    def randomSampleFeed() {
        // Get a random sample feed that hasn't already been subscribed to.
        def allSampleFeedsSubscribed = redis.sinter(SAMPLE_FEEDS_KEY, SUBSCRIBED_FEEDS_KEY)?.size() == redis.scard(SAMPLE_FEEDS_KEY)
        def sampleFeed = [:]
        while (!allSampleFeedsSubscribed && !sampleFeed) {
            def feed = redis.srandmember(SAMPLE_FEEDS_KEY)
            if (!redis.sismember(SUBSCRIBED_FEEDS_KEY, feed)) {
                sampleFeed["url"] = feed
                sampleFeed["name"] = redis.get(feed)
            }
        }
        
        return sampleFeed
    }
    
    def subscribedFeeds() {
        return redis.smembers(SUBSCRIBED_FEEDS_KEY).collect { feedUrl -> new Expando(name: redis.get(feedUrl), url: feedUrl) }
    }
}
