package com.amazon.ata.kindlepublishingservice.activity;

import com.amazon.ata.kindlepublishingservice.dao.PublishingStatusDao;
import com.amazon.ata.kindlepublishingservice.dynamodb.models.PublishingStatusItem;
import com.amazon.ata.kindlepublishingservice.models.PublishingStatusRecord;
import com.amazon.ata.kindlepublishingservice.models.requests.GetPublishingStatusRequest;
import com.amazon.ata.kindlepublishingservice.models.response.GetPublishingStatusResponse;
import com.amazonaws.services.lambda.runtime.Context;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetPublishingStatusActivity {
    private PublishingStatusDao publishingStatusDao;

    @Inject
    public GetPublishingStatusActivity(PublishingStatusDao publishingStatusDao) {
        this.publishingStatusDao = publishingStatusDao;
    }

    public GetPublishingStatusResponse execute(GetPublishingStatusRequest publishingStatusRequest) {
        List<PublishingStatusItem> publishingStatusItemList = publishingStatusDao.getPublishingStatus(publishingStatusRequest.getPublishingRecordId());
        List<PublishingStatusRecord> publishingStatusRecords = new ArrayList<>();
                for (PublishingStatusItem item: publishingStatusItemList){
                    publishingStatusRecords.add(PublishingStatusRecord.builder()
                                    .withStatusMessage(item.getStatusMessage())
                                    .withStatus(item.getStatus().toString())
                                    .withBookId(item.getBookId())
                                    .build());
                }
        return GetPublishingStatusResponse.builder().withPublishingStatusHistory(publishingStatusRecords).build();
    }

}
