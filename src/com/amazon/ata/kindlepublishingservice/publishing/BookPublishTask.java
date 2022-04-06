package com.amazon.ata.kindlepublishingservice.publishing;

import com.amazon.ata.kindlepublishingservice.dao.CatalogDao;
import com.amazon.ata.kindlepublishingservice.dao.PublishingStatusDao;
import com.amazon.ata.kindlepublishingservice.dynamodb.models.CatalogItemVersion;
import com.amazon.ata.kindlepublishingservice.dynamodb.models.PublishingStatusItem;
import com.amazon.ata.kindlepublishingservice.enums.PublishingRecordStatus;
import com.amazon.ata.kindlepublishingservice.exceptions.BookNotFoundException;

import javax.inject.Inject;

public class BookPublishTask implements Runnable {
    private PublishingStatusDao publishingStatusDao;
    private BookPublishRequestManager  bookPublishRequestManager;
    private CatalogDao catalogDao;

    @Inject
    public BookPublishTask(PublishingStatusDao publishingStatusDao, BookPublishRequestManager bookPublishRequestManager, CatalogDao catalogDao) {
        this.publishingStatusDao = publishingStatusDao;
        this.bookPublishRequestManager = bookPublishRequestManager;
        this.catalogDao = catalogDao;
    }

    @Override
    public void run() {


        //added below

        BookPublishRequest newRequest = bookPublishRequestManager.getBookPublishRequest();
        while(newRequest == null) {
            try {
                Thread.sleep(1000);
                newRequest = bookPublishRequestManager.getBookPublishRequest();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        PublishingStatusItem item = publishingStatusDao.setPublishingStatus(newRequest.getPublishingRecordId(), PublishingRecordStatus.IN_PROGRESS, newRequest.getBookId());

        KindleFormattedBook kindleFormattedBook = KindleFormatConverter.format(newRequest);

        try {
            CatalogItemVersion book = catalogDao.createOrUpdateBook(kindleFormattedBook);
            item = publishingStatusDao.setPublishingStatus(newRequest.getPublishingRecordId(), PublishingRecordStatus.SUCCESSFUL, book.getBookId());
        } catch (BookNotFoundException e) {
            item = publishingStatusDao.setPublishingStatus(newRequest.getPublishingRecordId(), PublishingRecordStatus.FAILED, newRequest.getBookId());
        }


// added above

    }
}
