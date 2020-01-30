package facades;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.*;

public class ScraperFacade {

    private static EntityManagerFactory emf;
    private static ScraperFacade instance;
    private static List<String> urls;

    private ScraperFacade() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static ScraperFacade getScraperFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            getUrls();
            instance = new ScraperFacade();
        }
        return instance;
    }

    private static void getUrls() {
        urls = new ArrayList<>();
        urls.add("https://emilgth.dk/margamondo/api/flights/all");
//        urls.add("https://www.jjugroup.ga/SysEksBackend/api/flight/all");
//        urls.add("http://lamseben.dk/rushflight/api/flights/all");
    }

    public List<String> getAllApiData() throws ExecutionException, InterruptedException {
        List<String> data = new ArrayList<>();
        Queue<Future<String>> queue = new ArrayBlockingQueue<>(urls.size());

        ExecutorService executor = Executors.newCachedThreadPool();
        for (String url : urls) {
            //The lambda notation makes the method an implicit callable
            Future<String> future = executor.submit(() -> getApiData(url));
            queue.add(future);
        }
        data.add("[");
        while (!queue.isEmpty()) {
            Future<String> json = queue.poll();
            if (json.isDone()) {
                data.add(json.get());
                data.add(",");
            } else {
                queue.add(json);
            }
        }
        data.remove(data.size() - 1);
        data.add("]");
        executor.shutdown();
        return data;
    }

    public String getApiData(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        try (Scanner scanner = new Scanner(connection.getInputStream())) {
            StringBuilder jsonStr = new StringBuilder();
            while (scanner.hasNext()) {
                jsonStr.append(scanner.nextLine());
            }
            return jsonStr.toString();
        }
    }
}
