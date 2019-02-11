package ictgradschool.project.servlets.admin;

import ictgradschool.project.daos.AdminDAO;
import ictgradschool.project.daos.UserDAO;
import ictgradschool.project.utilities.RandomPassword;

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

        String username = request.getParameter("username");

        final String SENDING_ACCOUNT_ADDRESS = getServletContext().getInitParameter(
                "SENDING_ACCOUNT_ADDRESS");
        final String SENDING_ACCOUNT_PASSWORD =getServletContext().getInitParameter("SENDING_ACCOUNT_PASSWORD");

        String tempPw = RandomPassword.generateRandomPassword();
        UserDAO.changePassword(username, tempPw, getServletContext());

        final String EMAIL_ADDRESS_TO = AdminDAO.getUserEmail(username, getServletContext());
        if (EMAIL_ADDRESS_TO.equals("none")){
            String message = "No email address set for this user. Could not reset password.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("admininterface").forward(request, response);
        } else {
            final String EMAIL_SUBJECT = "Resetting your password";
            final String EMAIL_BODY = "Your temporary password is: " + tempPw +" \nPlease update " +
                    "your password as soon as possible";

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

            String message = "Sent password reset email to user " + username;
            request.setAttribute("message", message);
            request.getRequestDispatcher("admininterface").forward(request, response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
