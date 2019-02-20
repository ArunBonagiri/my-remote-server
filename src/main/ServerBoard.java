package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 /**
  *  This class is used to provide user-interface using Swing library
  *  it was also routes the received client commands to corresponding classes
  */


public class ServerBoard extends JFrame
{
	private JTextArea messagesArea;
	private JTextArea messagesArea2;
	private JTextField message;
	private JButton startServer;
	private Server mServer;
	private TaskExecuter taskExecuter;
	Keyboard keyboard;
	String hostname = "Unknown";
	InetAddress addr;
	

	public ServerBoard() 
	{
		
		super("My Server");
		
		
		JPanel panelFields = new JPanel();
		panelFields.setBackground(Color.white);
		panelFields.setLayout(new BoxLayout(panelFields,BoxLayout.Y_AXIS));	
		
		//message area for IP port display
		messagesArea = new JTextArea();
		messagesArea.setColumns(25);
		messagesArea.setRows(5);
		messagesArea.setEditable(false);
		
        // message area for gap
		messagesArea2 = new JTextArea();
		messagesArea2.setColumns(25);
		messagesArea2.setRows(1);
		messagesArea2.setEditable(false);
		
		// new class's reference creation
		taskExecuter = new TaskExecuter();
		keyboard = new Keyboard();
		
		startServer = new JButton("Start");
		startServer.setAlignmentX(CENTER_ALIGNMENT);
		startServer.addActionListener(new ActionListener() 
		{
			
	    @Override
	    public void actionPerformed(ActionEvent e)
	    {		
		  // disable the start button
		  startServer.setEnabled(false);
				
		  //creates the object OnMessageReceived asked by the TCPServer constructor
		  mServer = new Server(new Server.OnMessageReceived() 
		  {
					
		    @Override
		    //this method declared in the interface from TCPServer class is implemented here
			//this method is actually a callback method, because it will run every time when it will be called from
			//Server class (at while)
		    public void messageReceived(String message)
			{			 
		      if(message.equalsIgnoreCase("hostname"))
			  {
			    try {   
			    	  
			    	  try
			    	  {
						  addr = InetAddress.getLocalHost();
					  } catch (UnknownHostException e1) { e1.printStackTrace(); }
			    	  
			    	   hostname = addr.getHostName(); 
				       //System.out.println("PC name :"+hostname);
			    	   mServer.sendMessage("hostname"+":"+hostname);
						
					} catch (Exception e) { e.printStackTrace(); System.out.println("ServerBoard: host name message can't sent"); }
			  }
		      else if(message.length()>2)
			  {
			     taskExecuter.ExecuteThis(message);
			  }
			  else
			  {	
				  try
				  {
				    keyboard.type(message.charAt(0)); 
				    
				  }catch(Exception e)
				  { 
					  // taskExecuter.ExecuteThis("enter");
					  System.out.println("\n unknown symbol"); 
				  } 
			  }
		       
	        System.out.println("\n "+message+" length:"+message.length());
	       }
					  
	     });
				
	     mServer.start();
         }
			
	    });
		
		// the box where the user enters the text (EditText is called in Android)
		message = new JTextField();
		message.setSize(200, 20);

		// try block for getting IP address
		try 
		{
	            InetAddress ipAddr = InetAddress.getLocalHost();
	            String i = ipAddr.getHostAddress();
	            
	            messagesArea.setText("\n\n"+"\t IP        : "+i +" \n\t PORT : "+4444);
	     
	    } catch (UnknownHostException ex) { ex.printStackTrace(); }
		
		//add the buttons and the text fields to the panel
		panelFields.add(messagesArea);
		panelFields.add(startServer);
		panelFields.add(messagesArea2);
		getContentPane().add(panelFields);
		getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		setSize(300, 170);
		
		// setting the server frame into middle of the screen of our computer
		setLocationRelativeTo(null);
		Dimension dimention = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int)((dimention.getWidth()- this.getWidth())/2);
		int y = (int)((dimention.getHeight()- this.getHeight())/2);
		setLocation(x,y);
		
		setResizable(false);
		setVisible(true);
		
		}
}
