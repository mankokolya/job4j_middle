package jsonparsing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import jsonparsing.pojo.DayPojo;
import jsonparsing.pojo.SimpleTestCasePojo;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class JsonTest {

    private final String jsonSource = "{\n"
            + " \"title\":\"Coder From Scratch\", \n"
            + " \"author\":\"Nick\"\n"
            + "}";


    @Test
    public void testParse() throws IOException {
        JsonNode node = Json.parse(jsonSource);
        assertEquals("Coder From Scratch", node.get("title").asText());
    }

    @Test
    public void fromJson() throws IOException {
        JsonNode node = Json.parse(jsonSource);
        SimpleTestCasePojo pojo = Json.fromJson(node, SimpleTestCasePojo.class);

        assertEquals("Coder From Scratch", pojo.getTitle());
    }

    @Test
    public void toJson() {
        SimpleTestCasePojo pojo = new SimpleTestCasePojo();
        pojo.setTitle("Testing123");
        JsonNode jsonNode = Json.toJson(pojo);
        assertEquals("Testing123", jsonNode.get("title").asText());

    }

    @Test
    public void stringify() throws JsonProcessingException {
        SimpleTestCasePojo pojo = new SimpleTestCasePojo();
        pojo.setTitle("Testing123");
        JsonNode jsonNode = Json.toJson(pojo);
//        String toString = Json.stringify(jsonNode);
        System.out.println(Json.stringify(jsonNode));
        System.out.println(Json.prettyPrint(jsonNode));
    }

    @Test
    public void fromJsonDayTestScenario1() throws IOException {
        String dayScenario1 = "{\n"
                + " \"date\" : \"2019-12-25\", \n"
                + " \"name\" : \"Christmas Day\"\n"
                + "}";
        JsonNode node = Json.parse(dayScenario1);
        DayPojo pojo = Json.fromJson(node, DayPojo.class);
        assertEquals("2019-12-25", pojo.getDate().toString());
    }

}