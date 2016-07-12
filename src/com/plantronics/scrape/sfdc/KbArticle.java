package com.plantronics.scrape.sfdc;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.*;


public class KbArticle {
	
	private String url;
	private String id;
	private String title;
	private String title2;
	private String content; 
	private String content2;
	private String searchResults;
	private String pdf;
	private String questTitle;
	private String answerTitle;
	private String questionContent;
	private String answerContent;
	private String searchTitle;
	private String articleType;
	private String familyType;
	private String keywordSearch; 
	private Document page;
	private String[] children;
	private static final Logger log = Logger.getLogger(KbArticle.class);
    
    
    public KbArticle(){
    	
    	url = null;
    	id = null;
    	title = null;
    	content = null;
    	searchResults = null;
    	title2 = null;
    	content2 = null;
    	pdf = null;
    	questionContent = null; 
    	answerContent = null;
    	questTitle = null;
    	answerTitle = null;
    	searchTitle = null;
    	articleType = null;
    	familyType = null;
    	keywordSearch = null;
    	page = null;
    	children = null;
    }
    
    public void setUrl(String value){

    	url = value;
    	try {
			page = Jsoup.connect(url).get();
			log.info("URL: " + url);
			
		} 
    	catch (Exception e) {
			log.fatal("URL connection fail, error message: " + e);
			e.printStackTrace();
			page = null;
		}
    }
    
    public String[] getChildren(){
    	
    	if(page == null){
    		return(children);
    	}
    	
    	try{
    		Element div = page.select("div.blockDetail").first();
	  	  	Elements child = div.children();
	  	  	children = new String[child.size()];
	  	  	for(int i = 0; i < child.size(); i++){
	  	  		children[i] = (child.get(i).attr("class"));
	  	  	}
	  	 log.info("Number of child DIV: " + children.length);
    	}
    	catch(Exception e){
    		log.error("No child DIV found , error message: " + e);
    		e.printStackTrace();
    	}
  
  	  	return(children);
    }
    
    public String getUrl(){
    	
    	return(url);
    }
    
    public String getId(){
    	
    	if(page == null){
    		return(id);
    	}
    	
    	try{
	    	for (Element searchResult : page.select("div.blockMetaData")) {
	    		id = searchResult.select("span").text();
	        }
	    	log.info("Article ID: " + id);
    	}
    	catch(Exception e){
    		log.error("No ID found, error message: " + e);
      	  	e.printStackTrace();
    	}
    	
    	return(id);
    }
    
    public String getTitle(){
    	
    	if(page == null){
    		return(title);
    	}
    	
    	try{
	    	for(Element searchResult : page.select("div.blockDetail")) {
	    		Element div = searchResult.child(1);   
	    		title = div.select("h3").text();
		    }
	    	log.info("Title: " + title);
    	}
    	catch(Exception e){
    		log.error("No Title found, error message: " + e);
      	  	e.printStackTrace();
    	}
    	
    	return(title);
    }
    
    public String getTitle2(){
    	
    	if(page == null){
    		return(title2);
    	}
    	
    	try{
	    	for(Element searchResult : page.select("div.blockDetail")) {
	    		Element div = searchResult.child(3);   
				title2 = div.select("h3").text();
		    }
	    	log.info("Content title: " + title2);
    	}
    	catch(Exception e){
    		log.error("No Content Title found, error message: " + e);
      	  	e.printStackTrace();
    	}
    	
    	return(title2);
    }
    
    public String getContent(){
    	
    	if(page == null){
    		return(content);
    	}
    	
    	try{
	    	for(Element searchResult : page.select("div.blockDetail")) {
	    		Element div = searchResult.child(2);
	    		content = div.html();
	      	}
	    	log.info("Question/Content: " + content);
    	}
    	catch(Exception e){
    		log.error("No Question/Content found, error message: " + e);
      	  	e.printStackTrace();
    	}
    	
    	content = cleanHtml(content);
    	return(content);
    }
    
   public String getContent2(){
	   
	   if(page == null){
		   return(content2);
	   }
	   
	   try{
		   for (Element searchResult : page.select("div.blockDetail")) {
	    		Element div = searchResult.child(4);
	    		content2 = div.html();
		   }
		   log.info("Content: " + content2);
	   }
	   catch(Exception e){
		   log.error("No Content found, error message: " + e);
     	   e.printStackTrace();
	   }
	   
    	content2 = cleanHtml(content2);
    	return(content2);
    }
        
    public String getSearchResults(){
    	
    	if(page == null){
    		return(searchResults);
    	}
    	
    	try{
	  	    
	    	for (Element searchResult : page.select("div.blockResults")) {
	  	    	searchResults = searchResult.html();
		    }
	    	log.info("Search Results: " + searchResults);
    	}
    	catch(Exception e){
    		log.error("No Search Results found, error message: " + e);
      	    e.printStackTrace();
    	}
    	
    	searchResults = cleanHtml(searchResults);
    	return(searchResults);
    }
    
    public String getPdf(){
    	
    	if(page == null){
    		return(pdf);
    	}
    	
    	try{
    		for(Element searchResult : page.select("div.blockDetail")) {
    			pdf = searchResult.select("a").outerHtml();
    		}
    		log.info("PDF: " + pdf);
  	  	}catch(Exception e){
  		   log.error("No PDF found, error message: " + e);
  		   e.printStackTrace();
  	  	}
	  
    	pdf = cleanHtml(pdf);
    	return(pdf);
    }
   
    public String getQuestionTitle(){
    	
    	if(page == null){
    		return(questTitle);
    	}
    	
    	try{
    		for(Element searchResult : page.select("div.blockDetail")) {
    			Element div = searchResult.child(2);   
	  		    questTitle = div.select("h4").text();
		    }
		    log.info("Question info: " + questTitle);
	    }
	    catch(Exception e){
		    log.error("No Question Title found, error message: " + e);
     	    e.printStackTrace();
	    }
	   
	    return(questTitle);
     }
   
   	public String getAnswerTitle(){
   		
   		if(page == null){
   			return(answerTitle);
   		}
   		
   		try{
	   		for(Element searchResult : page.select("div.blockDetail")) {
		  		Element div = searchResult.child(4);   
		  		answerTitle = div.select("h4").text();
			}
	   		log.info("Answer Title: " + answerTitle);
   		}
   		catch(Exception e){
   			log.error("No Answer Title found, error message: " + e);
      	    e.printStackTrace();
   		}
   		
	  	return(answerTitle);
	  }
   	
   	public String getQuestionContent(){
   		
   		if(page == null){
   			return(questionContent);
   		}
   		
   		try{
	   		for(Element searchResult : page.select("div.blockDetail")) {
	   			Element div = searchResult.child(3);
	   			questionContent = div.html();
	     	}
	   		log.info("Question Info: " + questionContent);
   		}
   		catch(Exception e){
   			log.error("No Question Content found, error message: " + e);
      	    e.printStackTrace();
   		}
   		
   		questionContent = cleanHtml(questionContent);
   		return(questionContent);
   	}
   
   	public String getAnswerContent(){
   		
   		if(page == null){
   			return(answerContent);
   		}
   		
   		try{
	   		for(Element searchResult : page.select("div.blockDetail")) {
	   			Element div = searchResult.child(5);
	   			answerContent = div.html();
	     	}
	   		log.info("Answer Content: " + answerContent);
   		}
   		catch(Exception e){
   			log.error("No Answer Content found, error message: " + e);
      	    e.printStackTrace();
   		}
   		
   		answerContent = cleanHtml(answerContent);
   		return(answerContent);
   	}
   
    public String cleanHtml(String html){
   		
    	Document doc = Jsoup.parse(html);
	    doc.select("font").unwrap();

	    NodeTraversor traversor  = new NodeTraversor(new NodeVisitor() {
		   
	    	public void tail(Node node, int depth) {
	    		if (node instanceof Element) {
				   Element e = (Element) node;
				   e.removeAttr("class");
				   e.removeAttr("style");
			    }
		    }

		    public void head(Node node, int depth) {        
		    }
	    });
	    
	    traversor.traverse(doc.body());
	    String modifiedHtml = doc.toString();
	    modifiedHtml = modifiedHtml.replaceAll("<html>", "");
	    modifiedHtml = modifiedHtml.replaceAll("</html>", "");
	    modifiedHtml = modifiedHtml.replaceAll("<body>", "");
	    modifiedHtml = modifiedHtml.replaceAll("</body>", "");
	    modifiedHtml = modifiedHtml.replaceAll("<head>", "");
	    modifiedHtml = modifiedHtml.replaceAll("</head>", "");
	
	    return (modifiedHtml);
    }
   
    public String getSearchTitle(){
    	
    	if(page == null){
    		return(searchTitle);
    	}
    	
    	try{
	    	for(Element searchResult : page.select("div.blockSearch fieldset")) {
	 			Element div = searchResult.child(0);
	 			searchTitle = div.text();
	 		}
	    	log.info("Search Title: " + searchTitle);
    	}
    	catch(Exception e){
    		log.error("No Search Title found, error message: " + e);
      	    e.printStackTrace();
    	}
    	
 		searchTitle = cleanHtml(searchTitle);
 		return(searchTitle);
 	}
   
    public String getArticleType(){
    	
    	if(page == null){
    		return(articleType);
    	}
    	
    	try{
	    	for(Element searchResult : page.select("div.blockSearch fieldset")) {
	 			Element div = searchResult.child(1);
	   			articleType = div.html();
	     	}
	    	log.info("Article Type: " + articleType);
    	}
    	catch(Exception e){
    		log.error("No Article Type found, error message: " + e);
      	    e.printStackTrace();
    	}
    	
   		articleType = cleanHtml(articleType);
  		return(articleType);
  	}
   
    public String getFamilyType(){
    	
    	if(page == null){
    		return(familyType);
    	}
    	
    	try{
	    	for(Element searchResult : page.select("div.blockSearch fieldset")) {
				Element div = searchResult.child(2);
	  			familyType = div.html();
	    	}
	    	log.info("Family Type: " + familyType);
    	}
    	catch(Exception e){
    		log.error("No Family Type found, error message: " + e);
      	    e.printStackTrace();
    	}
    	
  		familyType = cleanHtml(familyType);
 		return(familyType);
 	}
   
    public String getkeywordSearch(){
    	
    	if(page == null){
    		return(keywordSearch);
    	}
    	
    	try{
	    	for(Element searchResult : page.select("div.blockSearch fieldset")) {
				Element div = searchResult.child(3);
	  			keywordSearch = div.html();
	    	}
	    	log.info("Keyword Search: " + keywordSearch);
    	}
    	catch(Exception e){
    		log.error("No Keyword Search found, error message: " + e);
      	    e.printStackTrace();
    	}
    	
 		keywordSearch = cleanHtml(keywordSearch);
 		return(keywordSearch);
 	}
}