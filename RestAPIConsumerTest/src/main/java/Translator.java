import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Translator {
    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("Введите предложение на русском языке");
        Scanner scanner = new Scanner(System.in);
        String sentenceToTranslate = scanner.nextLine();

        RestTemplate restTemplate = new RestTemplate();

        String url = "https://translation.googleapis.com/language/translate/v2";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "");

        Map<String, String> jsonData = new HashMap<>();
        jsonData.put("folderId", "");
        jsonData.put("targetLanguageCode", "en");
        jsonData.put("texts", "[" + sentenceToTranslate + "]");
        HttpEntity<Map<String, String>> request = new HttpEntity<>(jsonData, headers);

       YandexResponse response = restTemplate.postForObject(url, request, YandexResponse.class);
        System.out.println(response);

        //parsing

        ObjectMapper mapper = new ObjectMapper();
        System.out.println("Перевод: " + response.getTranslations().get(0).getText());
    }
}
