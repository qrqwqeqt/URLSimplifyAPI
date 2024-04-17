package com.linkurlshorter.urlshortener.link;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a response containing information about a link.
 * This class provides various properties related to a link, such as its ID, long link,
 * short link, creation time, expiration time, usage statistics, and status.
 * Instances of this class can be created using the provided builder pattern.
 *
 * @author Artem Poliakov
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LinkInfoResponse {
    private String error;
    private UUID id;
    private String longLink;
    private String shortLink;
    private LocalDateTime createdTime;
    private LocalDateTime expirationTime;
    private int usageStatistics;
    private LinkStatus status;
}
