import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class App {
    public static void main(String[] args) {

        new GUI();

    }

    public static String getWeatherData(String city) throws Exception{

        String location = "https://community-open-weather-map.p.rapidapi.com/find?q=" + city + "&cnt=1&mode=null&lon=0&type=link%2C%20accurate&lat=0&units=metric";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(location))
                .header("x-rapidapi-key", readConfig())
                .header("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public static String getTemperature(String city) throws Exception {


        String data = getWeatherData(city);

        if(data.equals("{\"message\":\"accurate\",\"cod\":\"200\",\"count\":0,\"list\":[]}") || city.equals("")){

            return "You typed wrong city name. Try again!";
        }

        String temperatureParameter = data.substring(data.indexOf("temp"));

        String[] arrayOfParametres = temperatureParameter.split(",");

        String y = arrayOfParametres[0];

        double temperature = Double.parseDouble(y.substring(y.indexOf(":")+1));

        String x = String.valueOf(temperature);

        return x;
    }

    public static boolean checkIfCityCorrect(String city) throws Exception {

        String data = getWeatherData(city);

        if(data.equals("{\"message\":\"accurate\",\"cod\":\"200\",\"count\":0,\"list\":[]}") || city.equals("")){
            return false;
        }

        return true;
    }

    public static String readConfig() throws IOException {
        FileInputStream input = new FileInputStream("config.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        return reader.readLine();
    }
}
