package Handlers;

import Results.FillResult;
import Services.FillService;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class FillHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {
            if (exchange.getRequestMethod().equalsIgnoreCase("post")) {

                String urlPath = exchange.getRequestURI().toString();
                String[] urlParam = urlPath.split("/");

                FillService service = new FillService();
                FillResult result;
                if (urlParam.length == 3) {
                    result = service.fill(urlParam[2], "4");
                }
                else {
                    result = service.fill(urlParam[2], urlParam[3]);
                }

                if (result.isSuccess()) {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                }
                else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }

                Gson gson = new Gson();
                OutputStream respBody = exchange.getResponseBody();
                writeString(gson.toJson(result), respBody);
                respBody.close();

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
