package newtest;

import sun.audio.*;

import java.io.*;

public class Sound 
{
     public Sound()
     {
		try
		{
		  FileInputStream fileau=new  FileInputStream("E:\\study\\filehome\\javafilehome\\Java_workplace\\msg.mid");
		  sun.audio.AudioStream as=new AudioStream(fileau);
		  AudioPlayer.player.start(as);
		} 
		  catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}

class Sound1 
{
     public Sound1()
     {
		try
		{
		  FileInputStream fileau=new  FileInputStream("E:\\study\\filehome\\javafilehome\\Java_workplace\\msg.mid");
		  sun.audio.AudioStream as=new AudioStream(fileau);
		  AudioPlayer.player.start(as);
		} 
		  catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}