import dataclasses
import json
from datetime import date, timedelta
from typing import List


@dataclasses.dataclass
class Metadata:
    day: str
    event_type: str
    count: int


@dataclasses.dataclass
class RequestInput:
    from_day: str # inclusive
    to_day:str # inclusive
    event_types:List[str]


def read_csv(path: str) -> List[Metadata]:
    import csv
    with open(path, 'r') as f:
        reader = list(csv.reader(f))
        return [
            Metadata(day=line[1], event_type=line[2], count=int(line[3]))
            for line in reader[1::]
        ]

# Output Example 1:
# [
#     {
#         "from_day": "2024-08-01",
#         "to_day": "2024-08-01",
#         event_type": ["LINK_FOLDER_CREATED", "INVITE_TO_FORWRD", "CLICK_RESOURCE_CARD"]
#     },
#     {
#         "from_day": "2024-08-01",
#         "to_day": "2024-08-01",
#         "event_type": ["Unsupported Url (Auto brand recommendation)"]
#     },
#     ....
# ]
#
# Output Example 2:
# [
#     {
#         "from_day": "2024-08-01",
#         "to_day": "2024-08-03",
#         event_type": ["LINK_FOLDER_CREATED"]
#     },
#     {
#         "from_day": "2024-08-04",
#         "to_day": "2024-08-10",
#         "event_type": ["LINK_FOLDER_CREATED"]
#     },
#     ....
# ]
def get_request_input(matrix: List[Metadata]) -> List[RequestInput]:
    # TODO write your code here
    return []


def validate(matrix: List[Metadata], request_input_list: List[RequestInput]):
    def __get_key(day, event_type):
        return f'{day}=$={event_type}'

    data = {__get_key(m.day, m.event_type): m.count for m in matrix}
    actual_count = sum(data.values())
    total_entries = len(data)

    total_count = 0
    for r in request_input_list:
        request_count = 0
        curr_day = r.from_day
        end_day_d = date.fromisoformat(r.to_day)
        while date.fromisoformat(curr_day) <= end_day_d:
            for t in r.event_types:
                key = __get_key(curr_day, t)
                count = data.get(key)
                del data[key]
                request_count += count
                total_count += count
            next_day_d = date.fromisoformat(curr_day) + timedelta(days=1)
            curr_day = next_day_d.isoformat()
        if len(r.event_types) > 1:
            assert request_count <= 100_000, f"total request count exceeded 100K: {request_count}"

    assert actual_count == total_count, f"actual_count: {actual_count}. total_count: {total_count}"
    assert len(data) == 0, f"not all data used - ({len(data)}/{total_entries}). Missing records: {data}"


if __name__ == '__main__':
    matrix = read_csv('matrix.csv')
    request_input_list = get_request_input(matrix)
    validate(matrix, request_input_list)
    # print(json.dumps([dataclasses.asdict(r) for r in request_input_list], indent=2))
