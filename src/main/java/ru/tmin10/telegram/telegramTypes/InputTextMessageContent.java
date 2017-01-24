package ru.tmin10.telegram.telegramTypes;

public class InputTextMessageContent extends InputMessageContent 
{
	public String message_text;
	public String parse_mode;
	public boolean disable_web_page_preview;	
	
	public InputTextMessageContent(String message_text) 
	{
		this.message_text = message_text;
	}
}
