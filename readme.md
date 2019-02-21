
## Quick overview:

By using java sockets we builded a my_remote application. It devide into two parts

* server (my-remote-server)
* client (my-remote-client)

this current repo contains all the source of server,you can find client repo [here](https://github.com/ArunBonagiri/my-remote-client)
	
	
## Server (my-remote-server):
 
It was totally built on java core library's. So you don't need extra dependencies 
except this [nircmd.exe](http://www.nirsoft.net/utils/nircmd.html) file, because it allows to perform some simple opertions on a machine easily.
	
you can find more information of this application on WikiTab
	
	
	
### How to run?
	
* first download this repo in to your local machine.
* open command promt in downloaded directory and run following commands
```
     javac -d bin/ -cp src src/main/Server.java
     java -cp bin main.Server			 
```	
