package kg.megacom;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kg.megacom.models.Person;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        String jackson = main.sendGet().toString();

        ObjectMapper mapper = new ObjectMapper();

        List<Person>  user;
        user = mapper.readValue(jackson, new TypeReference<List<Person>>() {
        });

        user.forEach(System.out::println);

        


    }
    private StringBuilder sendGet() throws Exception {

        String url = "https://cat-fact.herokuapp.com/facts";

        HttpURLConnection httpClient =
                (HttpURLConnection) new URL(url).openConnection();

        // optional default is GET
        httpClient.setRequestMethod("GET");

        //add request header
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = httpClient.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            StringBuilder response = new StringBuilder();
            String line;


            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            return response;
        }
    }
}
