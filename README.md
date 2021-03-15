# WalletExamApp

## Building and Running App
These are the recommended versions required to make sure the project can be built successfully.
- Java JDK: Latest version (To support latest gradle)
- Android Studio Version: 4.1.2
- Android Build Tools Version: 30.0.2
- Kotlin Version: 1.4.30

You may use the command line gradle to build or run unit tests. Make sure the Android SDK directory is correct. Check local.properties to modify it.

## API

The app uses https://amock.io/ to mock such APIs. 
There's a user associated API saved in code for testing. However, you will not be able to change the state of the API (E.g. mocking API Error, HTTP Error).
To do that, you will have to create your own amock.io account. Please proceed to "Using your own amock.io to mock your own API" Section

The general API Response format looks like this

For success sample
```JSON
{
   "status": "SUCCESS",
   "data": {
       
   }
}
```

The `data` object can be in any form as long as you pass its type argument into `ApiResponse<T>`. 
For example, we have this class called `WalletLoadResponse` and its definition.

```kotlin
class WalletLoadResponse(val wallets: List<Wallet> = listOf())

class Wallet(val id: String, @SerializedName("wallet_name") val walletName: String,
             val balance: String)
```

Passing this to `ApiResponse<WalletLoadResponse>`, this must be the structure of JSON Version.
```JSON
{
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
`httpErrorCode` is also needed.
 

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

## Using your own amock.io to mock your own API

Since you do not have an access to the built-in amock.io/glenn.dev account you can still associate your own account to replace the existing account.

1. Register your account in http://amock.io
2. Remember your username. Because you will have to place the constant `USER_NAME` in `HttpModule` class in this project.
3. Go to the dashboard to create those APIs (Wallets and Transaction History). You may refer those above to mock success response, API Error response, and HTTP Error.
4. Open `HttpModule.kt`. Find the field `USER_NAME` and replace the existing value.
5. You will have to recompile the app to apply changes.

