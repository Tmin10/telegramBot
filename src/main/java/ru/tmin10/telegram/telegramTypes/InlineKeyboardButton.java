package ru.tmin10.telegram.telegramTypes;

public class InlineKeyboardButton 
{
	public String text;
	public String url;
	public String callback_data;
	public String switch_inline_query;
	
	public InlineKeyboardButton(String text) 
	{
		this.text = text;
	}
}
