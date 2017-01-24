package ru.tmin10.telegram.telegramTypes;

public class InlineQueryResultPhoto extends InlineQueryResult
{
	public String type = "photo";
	public String id;
	public String photo_url;
	public String thumb_url;
	public int photo_width;
	public int photo_height;
	public String title;
	public String description;
	public String caption;
	public InlineKeyboardMarkup reply_markup;
	public InputMessageContent input_message_content;
	
	public InlineQueryResultPhoto(String photo_url, String thumb_url) 
	{
		this.id = Double.toString(Math.random()*10000000);
		this.photo_url = photo_url;
		this.thumb_url = thumb_url;
	}
}
