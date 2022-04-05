package com.amazon.ata.kindlepublishingservice.publishing;

import com.amazon.ata.kindlepublishingservice.dao.CatalogDao;
import com.amazon.ata.kindlepublishingservice.dao.PublishingStatusDao;
import com.amazon.ata.kindlepublishingservice.dynamodb.models.CatalogItemVersion;
import com.amazon.ata.kindlepublishingservice.dynamodb.models.PublishingStatusItem;

import javax.inject.Inject;

//public class BookPublishTask implements Runnable {
//    private PublishingStatusDao publishingStatusDao;
//    private BookPublishRequestManager  bookPublishRequestManager;
//    private CatalogDao catalogDao;
//
//    @Inject
//    public BookPublishTask(PublishingStatusDao publishingStatusDao, BookPublishRequestManager bookPublishRequestManager, CatalogDao catalogDao) {
//        this.publishingStatusDao = publishingStatusDao;
//        this.bookPublishRequestManager = bookPublishRequestManager;
//        this.catalogDao = catalogDao;
//    }
//
//
//
//
//}
