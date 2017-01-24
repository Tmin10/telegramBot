package ru.tmin10.telegram.telegramTypes;

public class InlineQueryResultArticle extends InlineQueryResult 
{
	public String type = "article";
	public String id;
	public String title;
	public InlineKeyboardMarkup reply_markup;
	public InputMessageContent input_message_content;
	public String url;
	public boolean hide_url;
	public String description;
	public String thumb_url;
	public int thumb_width;
	public int thumb_height;
}
