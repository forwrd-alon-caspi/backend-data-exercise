SQL Exercise
------------

Given a final table name _**use\_case\_predictions**_ in a relational DB (SQL based), with the following columns:
*   **record\_id**: string
*   **score**: string (Excellent, Good, Fair, Poor)
*   **first\_prediction\_date**: date
*   **last\_prediction\_date**: date
*   **close\_date**: date _nullable_
*   **status**: string (Success, Fail) _nullable_

You can assume:
*   The use-case pipeline runs once a day (start and end within the same day)
*   This table is a snapshot. Meaning - every record appears in it only once.
*   The data is updated (atomic) at the end of a successful run
*   [Data setup](./data.sql)
*   You use the following SQL web tool - Fiddle [https://www.db-fiddle.com/](https://www.db-fiddle.com/) 


**Provide an SQL queries that calculate:**

1.  Number of predicted records in the last run (that had predictions)

2.  AVG time to close, for records that were closed in the last 7 days from last run

3.  MIN, MAX and AVG daily **new** records, in last 7 days from last run
    *  new record = a record that was first predicted in a given date or time interval.
    *  How will you handle days with no new records?
    *  For simplicity, you can assume you have a table with all calendar days (_**calendar\_days(day:date)**_)

4. Calculate the last 7 days accumulated SUCCESS performance, per day, for each day in the last 3 days, based on the close\_date .

    *Performance = count(score in (Excellent, Good) and status=Success) / count(status=Success)

**Bonus**: consider Qs #3 and #4 different calculator for Success (Excellent + Good) and Fail (Fair + Poor)
