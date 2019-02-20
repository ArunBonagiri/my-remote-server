package main;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;


  /**             IMPORTANT NOTE PLEASE READ IT
   *    
   * 
   * This class is used to perform given actions on local machine    
   * some of actions performed by using "nircmd.exe" so you need this file on your computer
   * 
   */

public class TaskExecuter 
{
   Robot robot;
   Robot robot1,okRobot;
   Keyboard keyboard;
   
   public void ExecuteThis(String string)
   {
    
	   
	   try 
	   {
		 robot = new Robot();
	   } catch (AWTException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	keyboard = new Keyboard();      
	
	  
	/****************************     Power Options    ****************************/
	   
	   if(string.equalsIgnoreCase("lock"))
	   {
		 String command = "Rundll32.exe user32.dll,LockWorkStation"; 
		 try {
				Process process = Runtime.getRuntime().exec(command);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	   }
	   else if(string.equalsIgnoreCase("sleep"))
	   {
		 String command = "rundll32.exe powrprof.dll,SetSuspendState 0,1,0"; 
		 try {
				Process process = Runtime.getRuntime().exec(command);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	   }
	   else if(string.equalsIgnoreCase("shutdown"))
	   {
		 String command = "Shutdown.exe -s"; 
		 try {
				Process process = Runtime.getRuntime().exec(command);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	   }
	   else if(string.equalsIgnoreCase("restart"))
	   {
		 String command = "Shutdown.exe -r -t 00"; 
		 try {
				Process process = Runtime.getRuntime().exec(command);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	   }
	   
	   /****************************     Audio Options    ****************************/

	   else if(string.equalsIgnoreCase("iVolume"))
	   {
		 String command = "nircmd.exe changesysvolume 2000"; 
		 try {
				Process process = Runtime.getRuntime().exec(command);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	   }
	   else if(string.equalsIgnoreCase("dVolume"))
	   {
		   
		String command = "nircmd.exe changesysvolume -2000"; 
		 try {
				Process process = Runtime.getRuntime().exec(command);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
	   }
	   else if(string.equalsIgnoreCase("mVolume"))  
	   {
		 String command = "nircmd.exe mutesysvolume 1"; 
		 try {
				Process process = Runtime.getRuntime().exec(command);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	   }
	   else if(string.equalsIgnoreCase("uVolume"))
	   {
		 String command = "nircmd.exe mutesysvolume 0"; 
		 try {
				Process process = Runtime.getRuntime().exec(command);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	   }
	   
	   /****************************     Mouse Options    ****************************/

	 //input will come in x,y format if user moves mouse on mouse pad

		else if(string.contains(",")){

			float movex=Float.parseFloat(string.split(",")[0]);//extract movement in x direction

			float movey=Float.parseFloat(string.split(",")[1]);//extract movement in y direction

			Point point = MouseInfo.getPointerInfo().getLocation(); //Get current mouse position

			float nowx=point.x;

			float nowy=point.y;

			robot.mouseMove((int)(nowx+movex),(int)(nowy+movey));//Move mouse pointer to new location

		}
	    //if user taps on mouse pad to simulate a left click

		else if(string.contains("left_click")){

			//Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)

			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

		}
	    //if user taps on mouse pad to simulate a right click
	    else if(string.equalsIgnoreCase("right_click")){

	   //Simulate press and release of mouse button 1(makes sure correct button is pressed based on user's dexterity)
	     {
	        robot.keyPress(KeyEvent.VK_SHIFT); 	
	        robot.keyPress(KeyEvent.VK_F10);
	     }
	 
	     robot.keyRelease(KeyEvent.VK_SHIFT); 	
	     robot.keyRelease(KeyEvent.VK_F10); 
	     } 
	   /**************************       special keys     *************************/
	   
	    else if(string.equalsIgnoreCase("enter"))
	    {
           keyboard.specialKey(66);
	 	}  
	    else if(string.equalsIgnoreCase("backspace"))
	    {
	    	 keyboard.specialKey(67);
	 	}
	    else if(string.equalsIgnoreCase("keyup"))
	    {
           keyboard.specialKey(19);
	 	}  
	    else if(string.equalsIgnoreCase("keydown"))
	    {
	    	 keyboard.specialKey(20);
	 	}
	    else if(string.equalsIgnoreCase("keyleft"))
	    {
           keyboard.specialKey(21);
	 	}  
	    else if(string.equalsIgnoreCase("keyright"))
	    {
	    	 keyboard.specialKey(22);
	 	}
	   
	   /**************************       extra keys     *************************/
	   
	    else if(string.equalsIgnoreCase("Client_Exit"))
	    {
	    	System.exit(0);
	    }
	    
   }
}
