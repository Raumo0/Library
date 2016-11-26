package com.netcracker.library.tools;

import com.netcracker.library.constants.MessageConstants;

import java.util.ResourceBundle;

/**
 * Created by raumo0 on 26.11.16.
 */
public class MessageManager {
	private MessageManager() {}

	public static MessageManager getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private final ResourceBundle bundle = ResourceBundle.getBundle(MessageConstants.MESSAGE_SOURCE);

	public String getProperty(String key) {
		return bundle.getString(key);
	}
	
	private static class SingletonHolder{
		private static final MessageManager INSTANCE = new MessageManager();
	}
}
