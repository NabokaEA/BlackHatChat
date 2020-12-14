import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class UserMessage implements Serializable {
    private Date messageSendTime;
    private String messageAuthor;
    private String messageText;

    public Date getMessageSendTime() {
        return messageSendTime;
    }

    public void setMessageSendTime(Date messageSendTime) {
        this.messageSendTime = messageSendTime;
    }

    public String getMessageAuthor() {
        return messageAuthor;
    }

    public void setMessageAuthor(String messageAuthor) {
        this.messageAuthor = messageAuthor;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }


    public UserMessage() {
    }

    public static UserMessage of(String author, String message) {
        UserMessage userMessage = new UserMessage();
        userMessage.setMessageAuthor(author);
        userMessage.setMessageText(message);
        userMessage.setMessageSendTime(new Date());
        return userMessage;
    }

    @Override
    public String toString() {
        return String.format("%s %s:\n", messageAuthor, messageText);
    }
}
