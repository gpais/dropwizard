package com.newsWeaver.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {

	 public static byte[] serialize(Object obj)  {
	        ByteArrayOutputStream b = new ByteArrayOutputStream();
	        ObjectOutputStream o;
			try {
				o = new ObjectOutputStream(b);
				o.writeObject(obj);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return b.toByteArray();
	    }

	    public static Object deserialize(byte[] bytes)  {
	        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
	        try {
	        	ObjectInputStream o = new ObjectInputStream(b);
				return o.readObject();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        return null;
	    }
}
