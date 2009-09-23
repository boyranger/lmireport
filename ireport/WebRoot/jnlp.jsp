<%@ page contentType="application/x-java-jnlp-file; charset=UTF-8"%>
<%

	//过滤器里面添加了该4项，但是对于该contentType是不需要该四项
	//所以要覆盖掉，注意：过滤器定义几项就覆盖几项，不能少也不能多
	//全部设置为空值或0  modify by limao

	//response.setHeader("Cache-Control", null);
	//response.setHeader("Pragma", null);
 	//response.setDateHeader("Expires", 0);
	//response.setDateHeader("max-age", 0);

	String serverUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	String remoteDesignUrl = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/report/remoteDesigner";
	String jnlpString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
	+"<jnlp codebase=\"http://192.168.11.110:8080/ireport\">"
	+"<information>"
	+"<title>湖南科创信息技术股份有限公司</title>"
	+"<vendor>湖南科创信息技术股份有限公司</vendor>"
	+"<homepage href=\"http://www.chinacreator.com\"/>"
	+"<icon href=\"app_logo.jpg\"/>"
	+" <icon kind=\"splash\" href=\"creatorLogo.jpg\"/>"
	+"<offline-allowed/>"
	+"</information>"
	+"<resources><j2se version=\"1.5+\" href=\"http://java.sun.com/products/autodl/j2se\"/>"
	+"<jar href=\"ireport_fat10.jar\" download=\"lazy\"/>"
	+"</resources>"
	+"<application-desc main-class=\"com.chinacreator.ireport.Start\">"
	+"</application-desc>"
	+"<security>"
	+"<all-permissions/>"
	+"</security>"
	+"</jnlp>";

	out.print(jnlpString);
%>