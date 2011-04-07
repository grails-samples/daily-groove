<html>
	<head>
		<meta name="layout" content="news">
		<title>The Daily Groove</title>
		
	</head>
	
	<body id="all">
		<div class="container">
			<div class="span-24 last addFeed">
				<div id="errors">
					${flash.message}
				</div>
				<g:form action="addFeed">
					Add Feed: <g:textField name="url"  /> <g:submitButton name="Add"></g:submitButton>
				</g:form>			

			</div>			
			<div class="span-12 last leftColumnHeader">
				<h2>In the News</h2>

			</div>
			<div class="span-12 last rightColumnHeader">
				<h2>Unread Items</h2>
			</div>			

			<div class="span-4">
				<article:list value="${latestArticles}"></article:list>
			</div>
			<div class="span-15" id="contentPane">
				<article:repeat times="3">
					<article:random />
				</article:repeat>

			</div>
			<div class="span-5 last">
				<article:list action="read" value="${unreadItems}"></article:list>				
			</div>			
		</div>		
	</body>
	
</html>