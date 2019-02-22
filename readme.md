
## Quick overview:

This is a Client-Server application built on java. 
It is used to control local computer remotly by using android device 

Features:
* Mouse
* Keyboard
* power-options
* volume-control

This application developement process devided into two parts

* server (my-remote-server)
* client (my-remote-client)

this current repo contains all the source of server,you can find client repo [here](https://github.com/ArunBonagiri/my-remote-client)
	
## Server (my-remote-server):

The main application of this Server is to receive commands from client and execute on computer.	
you can find more information/quick_documentation of this application on [WikiTab](https://github.com/arunbonagiri190/my-remote-server/wiki)

### dependencies

It was totally built on java core library's. So you don't need extra dependencies 
except this [nircmd.exe](http://www.nirsoft.net/utils/nircmd.html) file, because it allows to perform some simple opertions on a machine easily.
	
### How to run?

* first download this repo in to your local machine.
* open command promt in downloaded directory and run following commands
```
     javac -d bin/ -cp src src/main/Server.java
     java -cp bin main.Server			 
```	
	
## Built With

* Java 1.8
* [Eclipse Mars](https://www.eclipse.org/mars/)

### Contributions
[Arun Bonagiri](https://github.com/arunbonagiri190)	
