package org.jugistanbul.stream.internal;

import jdk.jfr.consumer.RecordingStream;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class VirtualThreadPinnedEvent
{
    private static final String URL = "https://www.google.com/";

    public static void main(String[] args) throws InterruptedException {

        var client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build();
        var request = HttpRequest.newBuilder(URI.create(URL)).GET().build();

        try (var rs = new RecordingStream()){

            rs.onEvent("jdk.VirtualThreadPinned", event -> {
                System.out.println("Cause: " + event.getString("cause"));
                System.out.println("Total pause: " + event.getDuration("sumOfPauses"));
                System.out.println("Longest pause: " + event.getDuration("longestPause"));
                System.out.println();
            });

            rs.startAsync();

            var virtualThread = Thread.startVirtualThread(() -> getBodyAsString(client, request));
            virtualThread.join();
        }
    }

    public static synchronized void getBodyAsString(final HttpClient client, final HttpRequest request) {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Body: " + response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
