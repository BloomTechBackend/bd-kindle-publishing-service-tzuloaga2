package com.amazon.ata.kindlepublishingservice.publishing;

import com.amazon.ata.kindlepublishingservice.activity.RemoveBookFromCatalogActivity;
import com.amazon.ata.kindlepublishingservice.models.Book;
import com.amazon.ata.kindlepublishingservice.publishing.BookPublishRequest;

import java.util.concurrent.ConcurrentLinkedQueue;

public class BookPublishRequestManager   {
    ConcurrentLinkedQueue<BookPublishRequest> requests;
    public void BookPublishRequest(BookPublishRequest bookPublishRequest){
        requests.add(bookPublishRequest);
    }


    public BookPublishRequest getBookPublishRequest(){
      return requests.poll();
    }
}
