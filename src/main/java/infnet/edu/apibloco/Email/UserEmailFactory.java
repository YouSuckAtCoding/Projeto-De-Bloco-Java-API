package infnet.edu.apibloco.Email;

import infnet.edu.apibloco.Domain.Aggreagates.UserAggregate;
import infnet.edu.apibloco.Domain.Contracts.Email.SendEmailRequest;
import infnet.edu.apibloco.Domain.Models.User;


public class UserEmailFactory {

    public static SendEmailRequest CreateUserEmailRequest(UserAggregate user, OperationType tOperationType) {

        SendEmailRequest emailRequest = new SendEmailRequest();
        emailRequest.setEmailAddress(user.getEmail());
        emailRequest.setSubject(GetSubject(tOperationType));
        emailRequest.setContent(GetContent(tOperationType));

        return emailRequest;
    }
    public static SendEmailRequest CreateUserEmailRequest(User user, OperationType tOperationType) {

        SendEmailRequest emailRequest = new SendEmailRequest();
        emailRequest.setEmailAddress(user.Email);
        emailRequest.setSubject(GetSubject(tOperationType));
        emailRequest.setContent(GetContent(tOperationType));

        return emailRequest;
    }

    private static String GetSubject(OperationType tOperationType) {

        if (tOperationType == OperationType.Create)
            return "User Created";
        if (tOperationType == OperationType.Update)
            return "User Updated";
            
        return "User Deleted";
    }

    private static String GetContent(OperationType tOperationType) {

        if (tOperationType == OperationType.Create)
            return "User Created Sucessfully ";
        if (tOperationType == OperationType.Update)
            return "User Updated Successfully";
        return "User Deleted Successfully";
    }
}
