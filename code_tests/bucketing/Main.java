package ai.datomize.report_generator_msvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // Equivalent of the Python Metadata dataclass
    public static class Metadata {
        private String day;
        private String eventType;
        private int count;

        public Metadata(String day, String eventType, int count) {
            this.day = day;
            this.eventType = eventType;
            this.count = count;
        }

        // Getters and setters (optional)
        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public String getEventType() {
            return eventType;
        }

        public void setEventType(String eventType) {
            this.eventType = eventType;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }

    // Equivalent of the Python RequestInput dataclass
    public static class RequestInput {
        private String fromDay; // inclusive
        private String toDay;   // inclusive
        private List<String> eventType;

        public RequestInput(String fromDay, String toDay, List<String> eventType) {
            this.fromDay = fromDay;
            this.toDay = toDay;
            this.eventType = eventType;
        }

        // Getters and setters (optional)
        public String getFromDay() {
            return fromDay;
        }

        public void setFromDay(String fromDay) {
            this.fromDay = fromDay;
        }

        public String getToDay() {
            return toDay;
        }

        public void setToDay(String toDay) {
            this.toDay = toDay;
        }

        public List<String> getEventType() {
            return eventType;
        }

        public void setEventType(List<String> eventType) {
            this.eventType = eventType;
        }
    }

    /**
     * Reads a CSV file and converts it into a list of Metadata objects.
     * The CSV file is expected to have a header and the following columns:
     * (ignored, day, eventType, count, ...)
     *
     * @param path the path to the CSV file
     * @return a list of Metadata objects
     */
    public static List<Metadata> readCsv(String path) {
        List<Metadata> metadataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            boolean firstLine = true;  // skip header
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                // Assuming CSV is comma-separated without quoted commas
                String[] tokens = line.split(",");
                if (tokens.length >= 4) {
                    String day = tokens[1];
                    String eventType = tokens[2];
                    int count = Integer.parseInt(tokens[3].trim());
                    metadataList.add(new Metadata(day, eventType, count));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return metadataList;
    }

    /**
     * Generates a list of RequestInput objects based on the list of Metadata.
     * TODO: Implement the logic to transform the metadata list into the desired RequestInput list.
     *
     * Output Example 1:
     * [
     *     {
     *         "fromDay": "2024-08-01",
     *         "toDay": "2024-08-01",
     *         "eventType": ["LINK_FOLDER_CREATED", "INVITE_TO_FORWRD", "CLICK_RESOURCE_CARD"]
     *     },
     *     {
     *         "fromDay": "2024-08-01",
     *         "toDay": "2024-08-01",
     *         "eventType": ["Unsupported Url (Auto brand recommendation)"]
     *     },
     *     ....
     * ]
     *
     * Output Example 2:
     * [
     *     {
     *         "fromDay": "2024-08-01",
     *         "toDay": "2024-08-03",
     *         "eventType": ["LINK_FOLDER_CREATED"]
     *     },
     *     {
     *         "fromDay": "2024-08-04",
     *         "toDay": "2024-08-10",
     *         "eventType": ["LINK_FOLDER_CREATED"]
     *     },
     *     ....
     * ]
     *
     * @param matrix the list of Metadata objects
     * @return a list of RequestInput objects
     */
    public static List<RequestInput> getRequestInput(List<Metadata> matrix) {
        // TODO: Write your code here to generate the RequestInput list.
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        List<Metadata> matrix = readCsv(MATRIX_PATH);
        List<RequestInput> requestInputList = getRequestInput(matrix);

        // Convert the list to JSON using Gson and print it.
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(requestInputList);
        System.out.println(jsonOutput);
    }
}