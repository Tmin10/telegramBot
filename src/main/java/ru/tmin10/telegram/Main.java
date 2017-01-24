package ru.tmin10.telegram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import ru.tmin10.telegram.telegramTypes.*;

public class Main 
{
	public static void main(String[] args)
	{
		Query query = new Query();
		Result<User> info = query.run("getMe", new TypeToken<Result<User>> (){}.getType());
		System.out.println("Bot @" + info.result.username + " was started");
		
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("offset", "0");
		params.put("limit", "50");
		params.put("timeout", "10");
		int offset = 0;
		
		while (true)
		{
			params.put("offset", Integer.toString(offset));
			Result<Update[]> updates = query.run("getUpdates", new TypeToken<Result<Update[]>> (){}.getType(), params);
			if (updates.result.length > 0)
			{
				for (int i = 0; i < updates.result.length; i++)
				{
					offset = updates.result[i].update_id + 1;
					if (updates.result[i].inline_query != null)
					{
						Map<String, String> answerParams = new HashMap<String, String>();
						answerParams.put("inline_query_id", updates.result[i].inline_query.id);
						Gson gson = new Gson();
						ArrayList<InlineQueryResult> results = new ArrayList<InlineQueryResult>();
						InlineQueryResultPhoto photo = new InlineQueryResultPhoto("http://stuffo.hswstatic.com/stuffmomnevertoldyou/wp-content/uploads/sites/86/2013/12/mlp.jpg", "http://stuffo.hswstatic.com/stuffmomnevertoldyou/wp-content/uploads/sites/86/2013/12/mlp.jpg?w=300");
						photo.caption = "caption";
						ArrayList<InlineKeyboardButton> row = new ArrayList<InlineKeyboardButton> ();
						InlineKeyboardButton button = new InlineKeyboardButton("test");
						button.callback_data = "test";
						row.add(button);
						photo.reply_markup = new InlineKeyboardMarkup();
						photo.reply_markup.inline_keyboard = new ArrayList<ArrayList<InlineKeyboardButton>>();
						photo.reply_markup.inline_keyboard.add(row);
						results.add(photo);
						InlineQueryResultArticle article = new InlineQueryResultArticle();
						article.id = "1";
						article.title = "title";	
						article.input_message_content = new InputTextMessageContent("test");
						results.add(article);
						answerParams.put("results", gson.toJson(results));
						System.out.println(gson.toJson(results));
						Result<Boolean> result = query.run("answerInlineQuery", new TypeToken<Result<Boolean>> (){}.getType(), answerParams);
					}
				}
			}
		}
	}
}
