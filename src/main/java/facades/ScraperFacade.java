package facades;

import webScrapers.GetApi;
import webScrapers.GetApiCallable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ScraperFacade {
    public List<String> runParralel() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<String> urls = new ArrayList<>();
        urls.add("https://swapi.co/api/people/1/");
        List<Future<GetApi>> futures = new ArrayList<>();
        for (String url : urls) {
            Callable<GetApi> getApiCallable = new GetApiCallable(url);
            Future<GetApi> future = executorService.submit(getApiCallable);
            futures.add(future);
        }
        List<String> apiData = new ArrayList<>();
        for (Future<GetApi> future : futures) {
            GetApi getApi = future.get();
            apiData.add(getApi.getResult());
        }
        executorService.shutdown();
        return apiData;
    }
}
