# Changes in regard of the SDD #

During the implementation phase, we found out that we had not a detailed enough vision of our
program. We had not anticipated every single details during the conception time.

Of course, we kept the global model of our application, using the general concepts of : MVP, boundaries/entities, the “façade” pattern using interfaces, and the separation in GUI,NI, and Controller.

But on multiple details we had to make some modifications.

## Handling the messages ##

For example, we had not anticipated very well the way packets are handled. We had to implement an abstract factory in order to manage the Messages and JSONS.

## Arguments in the functions ##

We had sometimes also not very well anticipated the arguments of our functions, for example with ip addresses, or we had sometimes overestimated the content of the messages in regard of the choices that we made with the rest of the group. ( for example, in the beginning we thought that we would directly have the nickname when we get a message, but we had to retrieve it in the list, using the ip address. )

## Managing the JList ##

We also had not thought about the fact that jList were not automatically updated, we handled this by creating a new class inherited from the defaultModelList class that handle everything. (we could easily modify the way our User class work, it will be transparent for the controller who only uses the class userList)

## Signals ##

The names of the signal have slightly changed from the original conception. But globally, we managed to respect the original design, using the correct terms. And in our sequence diagram, we had not put the use of the “façade pattern”. No components can communicate directly with the controller, only the GUI and the NI can call methods from the controller.

## File transfer ##

The file transfer management of our application has been modified quite a lot. In fact, we were not really clear during the design phase on how the file transfert is made in Java, we did not know at this time every class involved in this functionality ( the concept of stream, the way the socket works,etc).