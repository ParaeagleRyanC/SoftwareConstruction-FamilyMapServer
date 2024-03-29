package Handlers;

import Results.PersonAllFamilyMemberResult;
import Results.PersonSingleMemberResult;
import Services.PersonAllFamilyMemberService;
import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class PersonAllFamilyMemberHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (exchange.getRequestMethod().equalsIgnoreCase("get")) {

                Headers requestHeaders = exchange.getRequestHeaders();
                if (requestHeaders.containsKey("Authorization")) {
                    String authToken = requestHeaders.getFirst("Authorization");

                    PersonAllFamilyMemberService service = new PersonAllFamilyMemberService();
                    PersonAllFamilyMemberResult result = service.getAllPersons(authToken);

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
