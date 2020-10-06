Feature: Validating Place API's

Scenario Outline: Verify if place is being Succesfully added using AddPlace API
Given Add Place Payload with "<Name>" "<Language>" "<Address>"
When user calls "AddPlaceAPI" with "Post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_Idcreated maps to "<Name>" using "getPlaceAPI"


Examples:
|Name      |Language|Address|
|Niroshika |English |india  |
#|Durga     |Tamil   |china  |

