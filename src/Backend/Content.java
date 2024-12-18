package Backend;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Content {
    private String contentId;
    private String authorId;
    private String content;
    private String timeStamp;
    private static final AtomicInteger counter = new AtomicInteger(0);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Content(String contentId, String authorId, String content, LocalDateTime timeStamp) {
        this.contentId = contentId;
        this.authorId = authorId;
        this.content = content;
        this.timeStamp = timeStamp.format(formatter);
    }

    public String getContentId() {
        return this.contentId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getContent() {
        return content;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
    public static String generateID() {
        return "CID-" + counter.incrementAndGet();
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp.format(formatter);
    }



}
