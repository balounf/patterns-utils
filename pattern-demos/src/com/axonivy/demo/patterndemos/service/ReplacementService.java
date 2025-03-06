package com.axonivy.demo.patterndemos.service;

import java.util.Map;
import java.util.regex.Pattern;

public class ReplacementService {
	private static final ReplacementService INSTANCE = new ReplacementService();
	public static final Pattern pattern = Pattern.compile("\\{\\{(.*?)\\}\\}");

	/**
	 * Gets the service instance.
	 *
	 * @return DocumentService
	 */
	public static ReplacementService get() {
		return INSTANCE;
	}

	/**
	 * Replace placeholders in a text and return the replaced text.
	 *
	 * @param content
	 * @param map
	 * @return replaced String
	 */
	public String replacePlaceholders(String content, Map<String, String> replacement) {
		var buffer = new StringBuffer();
		var replaceMatcher = pattern.matcher(content);
		while (replaceMatcher.find()) {
			var value = replacement.get(replaceMatcher.group(1));
			replaceMatcher.appendReplacement(buffer, value != null ? value : replaceMatcher.group(0));
		}
		replaceMatcher.appendTail(buffer);
		return buffer.toString();
	}
}
