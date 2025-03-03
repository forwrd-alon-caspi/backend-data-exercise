### Given:

* An API that accepts the following filters

```
from: str (inclusive)
to: str (inclusive)
event_type: list[str]
```

* The fact that each API call is limited to up to 100K records in its response (not sorted). I.e. if the there are more than 100K that match the filters - only 100K records are returned (not sorted)


### Implement:
A function that given the data volumes matrix returns a <u>**sub-optimal**</u> list of API parameters to be used for fetching the data, that:
* Not loose data, when possible
* Minimize the amount of calls

**_Use the code skeleton ([code_test.py](./code_test.py) or [code_test.java](./code_test.java)) 
and the data volume test file ([matrix.csv](./matrix.csv)) 
to provide and test your answer._**

<br/>

**Follow-up question:**
* Assuming there is a rate limit of 60 calls per hour and there are more than 60 API calls required - how would you handle them? 
* Bonus - Any idea for a potential optimal algorithm