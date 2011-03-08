package daily.groove

class Article {

	String title
	String body
	URL link
	Date dateCreated
	boolean unread = true
	
	static mapping = {
		unread index:true
	}
	
    static constraints = {
		title blank:false
		body blank:false
    }
}
