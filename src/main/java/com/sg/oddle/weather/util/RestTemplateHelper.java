package com.sg.oddle.weather.util;

import java.net.URI;
import java.util.Map;

import org.springframework.web.util.UriComponentsBuilder;

public class RestTemplateHelper {

	public static URI buildUriGetParam(String _url, Map<String,String> params) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(_url);
		return builder.buildAndExpand(params).toUri();
	}
}
