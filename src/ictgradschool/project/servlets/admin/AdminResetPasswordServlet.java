package ictgradschool.project.servlets.admin;

import ictgradschool.project.DAOs.AdminDAO;
import ictgradschool.project.DAOs.UserDAO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet(name = "AdminResetPasswordServlet")
public class AdminResetPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("In Admin Reset Password servlet");

        String username = request.getParameter("username");

        // Details for the sending account
        final String SENDING_ACCOUNT_ADDRESS = "socialsunflowers@gmail.com";
        final String SENDING_ACCOUNT_PASSWORD = "teamsunflowers";

//        todo this will actually generate a random string
        String tempPw = "temporary";
        UserDAO.changePassword(username, tempPw, getServletContext());

        // Details for the message to be sent
        final String EMAIL_ADDRESS_TO = AdminDAO.getUserEmail(username, getServletContext());
        final String EMAIL_SUBJECT = "Resetting your password";
        final String EMAIL_BODY = "Your temporary password is: " + tempPw;
        //Add in the password
        //Will be randomised


        // Mail server details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(SENDING_ACCOUNT_ADDRESS, SENDING_ACCOUNT_PASSWORD);
                    }
                });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(SENDING_ACCOUNT_ADDRESS));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EMAIL_ADDRESS_TO));
            message.setSubject(EMAIL_SUBJECT);
            message.setText(EMAIL_BODY);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("admininterface");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
