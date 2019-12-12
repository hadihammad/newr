# HadiWalletProject

- Basic Creating of Customer and Wallet by a rest API calls.



# Build the app

- Go to root project folder then

```sh
mvn clean install
```

- the output will be jar file.



# To Run the app

- Go to target folder then

```sh
java -jar hadiwalletproject-0.0.1-SNAPSHOT.jar
```



# Testing App

- Use this app, [Postman](https://www.getpostman.com/) , its necessary but very easy and simple.



# How to Use

| Start Here             | Link                                                         | HTTP Methods |
| ---------------------- | ------------------------------------------------------------ | ------------ |
| Root                   | http://localhost:8080/                                       | GET          |
| Create Customer        | http://localhost:8080/customer/addCustomer                   | POST         |
| Create Wallet          | http://localhost:8080/custumerId={custumerId}/addwallet      | POST         |
| get Customer info      | http://localhost:8080/customerId={custumerId}                | GET          |
| get all Customers info | http://localhost:8080/customer/allCustomers                  | GET          |
| add Credit to Customer | http://localhost:8080/custumersId={custumerId}/addMoneyByCreditToSarWallet={amount} | PATCH        |
| Delete Customer        | /customer/deleteCustomerId={customerId}                      | DELETE       |

- Links that have {custumerId} or {amount} copy them insted of click.
  remove {} and put number instead.

Example: http://localhost:8080/custumerId=1/addwallet



# Payload needed to Create the Customer or any resource

# Create Customer

URL: http://localhost:8080/customer/addCustomer

```json
{
        "phoneNumber": "Any String",
        "password": "Any String",
        "nationalId": "Any String",
        "firstName": "Any String",
        "lastName": "Any String"
}
```

- id will be generated automatically



# Add Wallet

URI: http://localhost:8080/custumerId=2/addwallet

```json
{
        "sar": "00.98",
        "try": "0.01"
}
```

- money will store in *number* but the double quotation is important.
- URI mean the link is not fixed like the URL.


# Add money or transfer to IBAN

URI: http://localhost:8080/custumerId=1/addMoneyByCreditToSarWallet=5000

URI: http://localhost:8080/custumerId=1/transferMoneyFromSarWalletToIban=2353

```json
{
	
}
```

- curly braces is necessary.



# list all Transaction for specific Customer

URI: http://localhost:8080/customerId=1/walletId=1

```json

```

- PayLoad is not needed at all, even if there any, there will be no consideration, only the URI is important.
- password will not be accessible by this method.



# list all Transaction for all Customer

URL: http://localhost:8080/customer/allCustomers

```json

```

- PayLoad is not needed at all, even if there any, there will be no consideration, only the URI is important.
- All Wallets Transactions per different Users, only for testing purposes, not ideal for business.



# Delete Customer

URI: http://localhost:8080/customer/deleteCustomerId=1

```json

```

- PayLoad is not needed.



# Database

To login to data base, go to http://localhost:8080/h2 , then enter the below

```json
jdbc:h2:~/DB/customer-db
```

in the field called "JDBC URL" then press Connect button.

- password will not be possible to see it from Get Method, only if you connect to database, although it just a basic approach.

# Troubleshooting

- This application will create database to store all the data, the data will be loaded automatically the next time it got started, if you want to delete it, go to you home user directory, inside a folder called "DB", the database it self called "customer-db.mv.db", after deleting it the next app start will create fresh one.



- if you want to build the app as war file not jar, add this tag below to pom.xml

```json
<packaging>war</packaging>
```



- always remember to update maven manually if you add or change the pom.xml, the process is :

  right click in project > Maven > Update project > checkBox Force update then click ok.


