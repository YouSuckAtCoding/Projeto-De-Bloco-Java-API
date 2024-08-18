package infnet.edu.apibloco.Domain.Contracts.Email;

import java.io.File;
import java.time.LocalDateTime;

public class SendEmailRequest {
    
    private String email;
    private String content;
    private File attatchement;
    private LocalDateTime dateSended;
    private long userId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public File getAttatchement() {
        return attatchement;
    }

    public void setAttatchement(File attatchement) {
        this.attatchement = attatchement;
    }

    public LocalDateTime getDateSended() {
        return dateSended;
    }

    public void setDateSended(LocalDateTime dateSended) {
        this.dateSended = dateSended;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
