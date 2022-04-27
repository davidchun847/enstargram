package com.choco.enstargram.script;

public class JsResponseScript {
	
	public static String alertNback(String msg) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert();");
		sb.append("history.back();");
		sb.append("</script>");
		return sb.toString();
	}

}
