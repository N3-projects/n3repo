/*    */ package n3.home.security;
/*    */ 
/*    */ import java.util.Iterator;
import java.util.LinkedHashMap;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.beans.factory.FactoryBean;
/*    */ import org.springframework.security.ConfigAttributeDefinition;
/*    */ import org.springframework.security.ConfigAttributeEditor;
/*    */ import org.springframework.security.intercept.web.DefaultFilterInvocationDefinitionSource;
/*    */ import org.springframework.security.intercept.web.FilterInvocationDefinitionSource;
/*    */ import org.springframework.security.intercept.web.RequestKey;
/*    */ import org.springframework.security.util.AntUrlPathMatcher;
/*    */ import org.springframework.security.util.UrlMatcher;

/*    */ public class DefinitionSourceFactoryBean
/*    */   implements FactoryBean
/*    */ {
/*    */ 
/*    */   public Object getObject()
/*    */     throws Exception
/*    */   {
/* 40 */     LinkedHashMap requestMap = buildRequestMap();
/* 41 */     UrlMatcher matcher = getUrlMatcher();
/* 42 */     DefaultFilterInvocationDefinitionSource definitionSource = new DefaultFilterInvocationDefinitionSource(matcher, 
/* 43 */       requestMap);
/* 44 */     return definitionSource;
/*    */   }
/*    */ 
/*    */   public Class getObjectType()
/*    */   {
/* 49 */     return FilterInvocationDefinitionSource.class;
/*    */   }
/*    */ 
/*    */   public boolean isSingleton() {
/* 53 */     return true;
/*    */   }
/*    */ 
/*    */   protected UrlMatcher getUrlMatcher()
/*    */   {
/* 60 */     return new AntUrlPathMatcher();
/*    */   }
/*    */ 
/*    */   protected LinkedHashMap<RequestKey, ConfigAttributeDefinition> buildRequestMap()
/*    */     throws Exception
/*    */   {
/* 68 */     LinkedHashMap srcMap = this.getRequestMap();
/* 69 */     LinkedHashMap distMap = new LinkedHashMap();
/* 70 */     ConfigAttributeEditor editor = new ConfigAttributeEditor();
/*    */
/* 72 */     for(Iterator iterator = srcMap.entrySet().iterator(); iterator.hasNext();)
			{
			    java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
			    RequestKey key = new RequestKey((String)entry.getKey(), null);
			    if(StringUtils.isNotBlank((String)entry.getValue()))
			    {
			        editor.setAsText((String)entry.getValue());
			        distMap.put(key, (ConfigAttributeDefinition)editor.getValue());
			    } else
			    {
			        distMap.put(key, ConfigAttributeDefinition.NO_ATTRIBUTES);
			    }
			}
/*    */ 
/* 82 */     return distMap;
/*    */   }

			public LinkedHashMap<String, String> getRequestMap() throws Exception {
				LinkedHashMap<String, String> requestMap = new LinkedHashMap<String, String>();
				requestMap.put("/ajax/**","IS_AUTHENTICATED_ANONYMOUSLY");
				requestMap.put("/login.jsp","IS_AUTHENTICATED_ANONYMOUSLY");
				requestMap.put("/bottom.jsp","IS_AUTHENTICATED_ANONYMOUSLY");
				requestMap.put("/main.jsp","ROLE_INDEX");
				requestMap.put("/rest/**","ROLE_REST");
				requestMap.put("/**","ROLE_ALL");
				return requestMap;
			}
}
