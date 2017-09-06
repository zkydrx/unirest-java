package com.mashape.unirest.http.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Created with IntelliJ IDEA.
 * User: Abbot
 * Date: 2017-09-06
 * Time: 01:03
 * Description:
 * 获取Google 短链接的工具类
 */
public class GoogleShortUtil
{
	/**
	 * 基于Google short url 实现短链接
	 * 优点，速度快，功能多。
	 * 根据长链接，获取一个短链接。
	 *
	 * @param longUrl
	 * @return
	 */
	public static String createShortUrl(String longUrl) throws UnirestException
	{
		HttpResponse<JsonNode> body = Unirest.post("https://www.googleapis" + "" + ".com/urlshortener/v1/url")
											 .header("Content-Type", "application/json")
											 .queryString("key", "AIzaSyAdkwBkPoXODWAH3NPf8ZI8lMzR9TlyDRo")
											 .body("{\"longUrl\":\"" + longUrl + "\"}")
											 .asJson();

		String shortUrl = body.getBody().getObject().getString("id");
		return shortUrl;
	}


	/**
	 * 扩展一个通过Google short url 压缩过的短链接
	 * 得到原始的长链接
	 *
	 * @param shortUrl
	 * @return
	 */
	public static String expandShortUrl(String shortUrl) throws UnirestException
	{

		HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.get("https://www.googleapis" + "" +
			".com/urlshortener/v1/url?shortUrl=" + shortUrl)
															 .header("Content-Type", "application/json")
															 .queryString("key",
																 "AIzaSyAdkwBkPoXODWAH3NPf8ZI8lMzR9TlyDRo")
															 .getHttpRequest()
															 .asJson();
		String longUrl = jsonNodeHttpResponse.getBody().getObject().getString("longUrl");
		return longUrl;
	}
}
