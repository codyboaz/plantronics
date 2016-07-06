package com.plantronics.com.scrape.sfdc;

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
	private String submit; 
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
    	submit = null;
    	page = null;
    	children = null;
    }
    
    public void setUrl(String value){

    	url = value;
    	try {
			page = Jsoup.connect(url).get();
			
		} catch (Exception e) {
			log.fatal("could not make connection with URL, error message: " + e);
			e.printStackTrace();
		}
    }
    public String[] getChildren(){

      Elements child;
      try{
	  	  Element div = page.select("div.blockDetail").first();
	  	  child = div.children();
	  	  children = new String[child.size()];
	  	  for(int i = 0; i < child.size(); i++){
	 		  children[i] = (child.get(i).attr("class"));
	 	  }
      }catch(Exception e){
    	  log.fatal("could not make connection with URL, error message: " + e);
    	  e.printStackTrace();
      }
  
  	  return(children);
    }
    
    public String getUrl()
    {
    	return(url);
    }
    
    public String getId(){

    	for (Element searchResult : page.select("div.blockMetaData")) {
    		id = searchResult.select("span").text();
        }
    	return(id);
    }
    
    public String getTitle(){

    	for(Element searchResult : page.select("div.blockDetail")) {
    		Element div = searchResult.child(1);   
    		title = div.select("h3").text();
	    }
    	log.info("Title: " + title);
    	return(title);
    }
    
    public String getTitle2(){

    	for(Element searchResult : page.select("div.blockDetail")) {
    		Element div = searchResult.child(3);   
			title2 = div.select("h3").text();
	    }
    	return(title2);
   }
    
    public String getContent(){

    	for(Element searchResult : page.select("div.blockDetail")) {
    		Element div = searchResult.child(2);
    		content = div.html();
      	}
    	content = cleanHtml(content);
    	return(content);
    }
    
   public String getContent2(){

	   for (Element searchResult : page.select("div.blockDetail")) {
    		Element div = searchResult.child(4);
    		content2 = div.html();
	   }
    	content2 = cleanHtml(content2);
    	return(content2);
    }
        
    public String getSearchResults(){
  
    	page.select("div.blockPagination").remove();
    	page.select("div.blockResults h3").remove();
  	    
    	for (Element searchResult : page.select("div.blockResults")) {
  	    	searchResults = searchResult.html();
	    }
    	searchResults = cleanHtml(searchResults);
    	return(searchResults);
    }
    
  public String getPdf(){

	   for(Element searchResult : page.select("div.blockDetail")) {
		   pdf = searchResult.select("a").outerHtml();
       }
	   pdf = cleanHtml(pdf);
       return(pdf);
    }
   
   public String getQuestionTitle(){

	   for(Element searchResult : page.select("div.blockDetail")) {
  		   Element div = searchResult.child(2);   
  		   questTitle = div.select("h4").text();
	   }
	   return(questTitle);
    }
   
   	public String getAnswerTitle(){
  
   		for(Element searchResult : page.select("div.blockDetail")) {
	  		Element div = searchResult.child(4);   
	  		answerTitle = div.select("h4").text();
		}
	  	return(answerTitle);
	  }
   	
   	public String getQuestionContent(){

   		for(Element searchResult : page.select("div.blockDetail")) {
   			Element div = searchResult.child(3);
   			questionContent = div.html();
     	}
   		questionContent = cleanHtml(questionContent);
   		return(questionContent);
   	}
   
   	public String getAnswerContent(){

   		for(Element searchResult : page.select("div.blockDetail")) {
   			Element div = searchResult.child(5);
   			answerContent = div.html();
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

 		for(Element searchResult : page.select("div.blockSearch fieldset")) {
 			Element div = searchResult.child(0);
 			searchTitle = div.text();
 		}
 		searchTitle = cleanHtml(searchTitle);
 		return(searchTitle);
 	}
   
   public String getArticleType(){

  		for(Element searchResult : page.select("div.blockSearch fieldset")) {
 			Element div = searchResult.child(1);
   			articleType = div.html();
     	}
   		articleType = cleanHtml(articleType);
  		return(articleType);
  	}
   
   public String getFamilyType(){

 		for(Element searchResult : page.select("div.blockSearch fieldset")) {
			Element div = searchResult.child(2);
  			familyType = div.html();
    	}
  		familyType = cleanHtml(familyType);
 		return(familyType);
 	}
   
   public String getkeywordSearch(){

 		for(Element searchResult : page.select("div.blockSearch fieldset")) {
			Element div = searchResult.child(3);
  			keywordSearch = div.html();
    	}
 		keywordSearch = cleanHtml(keywordSearch);
 		return(keywordSearch);
 	}
   
   public String getSubmit(){

		for(Element searchResult : page.select("div.blockSearch fieldset")) {
			Element div = searchResult.child(4);
 			submit = div.html();
   	}
		submit = cleanHtml(submit);
		return(submit);
	}
   

}
