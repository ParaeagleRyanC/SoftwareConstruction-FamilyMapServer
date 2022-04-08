package Handlers;

import Results.EventSingleResult;
import Results.PersonSingleMemberResult;
import Services.PersonSingleMemberService;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class PersonSingleMemberHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {
            if (exchange.getRequestMethod().equalsIgnoreCase("get")) {

                String urlPath = exchange.getRequestURI().toString();
                String[] urlParam = urlPath.split("/");

                Headers requestHeaders = exchange.getRequestHeaders();
                if (requestHeaders.containsKey("Authorization")) {
                    String authToken = requestHeaders.getFirst("Authorization");

                    PersonSingleMemberService service = new PersonSingleMemberService();
                    PersonSingleMemberResult result = service.getSinglePerson(urlParam[2], authToken);

                    if (result.isSuccess()) {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    }

                    else {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    }

                    OutputStream respBody = exchange.getResponseBody();
                    Gson gson = new Gson();
                    writeString(gson.toJson(result), respBody);
                    respBody.close();
                }
            }
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }

    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}
