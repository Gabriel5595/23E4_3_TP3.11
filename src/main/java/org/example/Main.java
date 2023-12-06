package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        try {
            // URL da API
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");

            // Abrindo a conexão
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurando a conexão para o método POST
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Criando um objeto User com os dados
            User user = new User(1, 1, "delectus aut autem", false);

            // Usando ObjectMapper para converter o objeto User em JSON
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonInputString = objectMapper.writeValueAsString(user);

            // Obtendo o stream de saída da conexão para enviar o JSON
            try (OutputStream out = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                out.write(input, 0, input.length);
            }

            // Obtendo a resposta da requisição
            int responseCode = connection.getResponseCode();
            System.out.println("Código de resposta: " + responseCode);

            // Fechando a conexão
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}