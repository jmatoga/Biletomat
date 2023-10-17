# Biletomat
Sprzedaż biletów w biletomacie MPK

## Table of contents
* [General info](#general-info)
* [English](#english)
* [Technologies](#technologies)


## General info
Program realizuje sprzedaż biletów w biletomacie MPK. Główne klasy to: Biletomat, Bilet, Pieniadz – obsługa zakupu biletów w biletomacie, biletomat wydaje resztę po transakcji gotówkowej.

Klasa Biletomat przechowuje informację o rodzaju biletów i ich cenach oraz swojej lokalizacji (w formie tekstowej). 
Każda transakcja sprzedaży biletu jest rejestrowana i biletomat posiada metodę toString() umożliwiającą wydruk informacji o wszystkich sprzedanych biletach. 
Dodatkowo biletomat posiada metodę sprzedaży biletów (w jednej transakcji można kupić kilka biletów).
Klasa Bilet posiada parametry określające rodzaj biletu i jego cenę. Posiada konstruktor i metodę toString do zwracania jego tekstowej reprezentacji.
Klasa Pieniadz określa środek płatności za bilety.

## English:
Ticket sales program at the MPK ticket machine. The main classes are: Biletomat, Bilet, Pieniadz - handling the purchase of tickets in a ticket machine, ticket machine issued after a cash transaction.

The Biletomat class stores notifications about ticket types and distribution, as well as your location (in text form).
Information about ticket sales is recorded and the Biletomat has toString() displaying information about all tickets sold.
Additionally, the ticket machine sells tickets (you can buy several tickets in one transaction).
Bilet class contains detailed information about the type of ticket and its price. It has a constructor and a toString to register its text.
Pieniadz class defining the means of payment for tickets.
	
## Technologies
Project is created with:
* Java
