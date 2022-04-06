package com.amazon.ata.kindlepublishingservice.publishing;

import java.util.concurrent.ConcurrentLinkedQueue;

public class BookPublishRequestManager {
    ConcurrentLinkedQueue<BookPublishRequest> requests;

    public BookPublishRequestManager() {
        this.requests = new ConcurrentLinkedQueue<>();
    }

    public void addRequest(BookPublishRequest request){
        requests.add(request);
    }

    public BookPublishRequest getBookPublishRequest(){
        return requests.poll();
    }
}