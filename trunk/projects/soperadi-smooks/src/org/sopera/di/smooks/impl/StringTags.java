package org.sopera.di.smooks.impl;

import java.util.HashMap;

public class StringTags 
{
	HashMap<String,String> tags = new HashMap<String,String>();;
	boolean state = false;
	boolean end = false;
	
	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	String findTagValue(String tagName)
	{
		System.out.println("read: "+tags.get(tagName));
		return tags.get(tagName);
	}
	
	void write(String key, String res)
	{
		this.tags.put(key, res);
		System.out.println("write: "+key+"    "+res);
	}
	
	synchronized void startWrite()
	{
		if(state)
		{
			try
			{
				wait();
			}
			catch(InterruptedException e){}
		}
	}
	
	synchronized void startRead()
	{
		if(!state)
		{
			try
			{
				wait();
			}
			catch(InterruptedException e){}
		}
	}
	synchronized void endRead()
	{
		if(state) {
			state = false;
			notify();
		}
	}
	
	synchronized void endWrite()
	{
		if(!state) {
			state = true;
			notify();
		}
	}
}
