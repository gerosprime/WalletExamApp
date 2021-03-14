# WalletExamApp

## Building and Running App
These are the recommended versions required to make sure the project can be built successfully.
- Java JDK: Latest version (To support latest gradle)
- Android Studio Version: 4.1.2
- Android Build Tools Version: 30.0.2
- Kotlin Version: 1.4.30

You may use the command line gradle to build or run unit tests.

## API

The server uses https://amock.io/ to mock such APIs

The general API Response format looks like this

For success sample
```JSON
{
   "status": "SUCCESS",
   "data": {
       
   }
}
```

The `data` object can be in any form as long as you pass its type argument into `ApiResponse<T>`


For error from API
```JSON
{
   "status": "ERROR",
   "httpErrorCode": 400,
   "errors": {
      "non_field_errors": {
        "message": "Request has been throttled"
      }
   }
}
```
`errors` is in a form of Dictionary/HashTable/HashMap data structure. It will require the name of error as key to get the value version.
`httpErrorCode` is optional
 

### Wallet API

Request Type: GET
Directory: `/wallets`

Sample
```JSON
{
    "status": "SUCCESS",
    "data": {
        "wallets": [
            {
                "id": "1001",
                "wallet_name": "PHP",
                "balance": "1000.23"
            },
            {
                "id": "1002",
                "wallet_name": "USD",
                "balance": "100.10"
            },
            {
                "id": "1003",
                "wallet_name": "ETH",
                "balance": "0.000000000000010026"
            }
        ]
    }
}

```


### Transaction History API

Request Type: GET
Directory: `/histories`

Sample
```JSON
{
    "status": "SUCCESS",
    "data": {
        "histories": [
                {
                    "id": "2001",
                    "entry": "incoming",
                    "amount": "100.23",
                    "currency": "PHP",
                    "sender": "John",
                    "recipient": "You"
                },
                {
                    "id": "2000",
                    "entry": "outgoing",
                    "amount": "10.10",
                    "currency": "USD",
                    "sender": "You",
                    "recipient": "7-11"
                },
                {
                    "id": "1999",
                    "entry": "incoming",
                    "amount": "0.000000000000000105",
                    "currency": "ETH",
                    "sender": "Keith",
                    "recipient": "You"
                }
            ]
    }
    
}
```
