package main;

import javax.swing.*;

import java.awt.Color;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * The class is used to create or close socket connection with client
 * send and receive the data from client
 */
public class Server extends Thread 
{
 
   public static final int SERVERPORT = 4444;
   private boolean running = false;
   PrintWriter mOut;
   Socket client;
   
 
   private OnMessageReceived messageListener;

   public static void main(String[] args) 
   {

     //opens the window where the messages will be received and sent
	 ServerBoard frame = new ServerBoard();
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 frame.pack();
	 frame.setVisible(true);
   }

    /**
     * Constructor of the class
     * @param messageListener listens for the messages
     */
     
   public Server(OnMessageReceived messageListener)
   {
	    this.messageListener = messageListener;
   }

   /**
     * Method to send the messages from server to client
     * @param message the message sent by the server
     */
   
    public void sendMessage(String message)
    {
     
    	if (mOut != null && !mOut.checkError())
    	{
    		System.out.println("message sending.."+message );
    		mOut.println(message);
    		mOut.flush();
    		System.out.println("message sent.!"+message ); 
			
    	}
    }
    
    @Override
    public void run()
    {
    
    	 super.run();
    	 running = true;
 
         try
         {
         
        	System.out.println("S: Connecting...");
            
            //create a server socket. A server socket waits for requests to come in over the network.
            ServerSocket serverSocket = new ServerSocket(SERVERPORT);

            // Testing Client
            Socket client1 = serverSocket.accept();
            System.out.println("S: Finding Client...");
            
            
            //create client socket... the method accept() listens for a connection to be made to this socket and accepts it.
            Socket client = serverSocket.accept();
            System.out.println("S: Receiving...");
            
            try
            {
	    
            	  mOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
                 
            	  //read the message received from client
                  BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                  
                  //in this while we wait to receive messages from client (it's an infinite loop)
                  //this while it's like a listener for messages
             
                  while (running)
                  {
                	 
                	  String message = in.readLine();
                	  if (message != null && messageListener != null)
                	  {
                		 
                		 messageListener.messageReceived(message);
                	  }
                  }
             } 
             catch (Exception e)
             {  
            	System.out.println("S: Error");
            	e.printStackTrace();
             } 
            
            finally 
            {
            	client.close();
            	System.out.println("S: Done.");
            }
         
         } catch (Exception e) {
	       System.out.println("S: Error");
	       e.printStackTrace();
	       
        }
      }
    
    //Declare the interface. The method messageReceived(String message) will must be implemented in the ServerBoard
    //class at on startServer button click
    
    public interface OnMessageReceived
    {
    	public void messageReceived(String message);
    }
}
