<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8">
		<title>The Daily Groove</title>
		<g:layoutHead></g:layoutHead>
		<g:javascript library="prototype"></g:javascript>
		<style type="text/css" media="screen">
			body h1 {
				margin-top:30px;
				font-family:Times;
				font-size: 4.0em;
				text-align:center;
				width:100%;
			}
			.leftColumnHeader h2{
				margin-top:20px;
				font-family:Times;
				font-size:2.0em;
			}
			.rightColumnHeader h2{
				margin-top:20px;
				font-family:Times;
				font-size:2.0em;
				text-align:right;
				margin-right:50px;
			}			
			.addFeed {
				font-family:times;
				font-weight:bold;
				
			}
			div.articleList {
				margin:0px;
			}
			div.articleList  div{
				margin:0px;
				margin-bottom:4px;
			}		
			.article {
				margin-bottom:30px;
				padding:4px;
			}	
			#errors {
				color:red;
				padding:5px;
			}
		</style>
		<blueprint:resources/>
	</head>
	<body id="news">
		<h1><g:layoutTitle></g:layoutTitle></h1>
		<g:layoutBody></g:layoutBody>
	</body>
	
</html>