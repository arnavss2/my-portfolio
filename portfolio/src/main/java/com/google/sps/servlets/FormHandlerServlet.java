package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {
    private static String CONTACT_CONST = "Contact";
    private static String SMALL_NAME_CONST = "name";
    private static String SMALL_EMAIL_CONST = "email";
    private static String SMALL_MESSAGE_CONST = "message";
    private static String NAME_CONST = "Name: ";
    private static String EMAIL_CONST = "Email: ";
    private static String MESSAGE_CONST = "Message: ";

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the values entered in the form.
    String name = request.getParameter(SMALL_NAME_CONST);
    String email = request.getParameter(SMALL_EMAIL_CONST);
    String message = request.getParameter(SMALL_MESSAGE_CONST);

    // Print the value so you can see it in the server logs.
    System.out.println(NAME_CONST + name);
    System.out.println(EMAIL_CONST + email);
    System.out.println(MESSAGE_CONST + message);

    // Write the value to the response so the user can see it.
    response.getWriter().println(NAME_CONST + name);
    response.getWriter().println(EMAIL_CONST + email);
    response.getWriter().println(MESSAGE_CONST + message);
    
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

    KeyFactory keyFactory = datastore.newKeyFactory().setKind(CONTACT_CONST);

    FullEntity contactEntity =
    Entity.newBuilder(keyFactory.newKey())
        .set(NAME_CONST, name)
        .set(EMAIL_CONST, email)
        .set(MESSAGE_CONST, message)
        .build();

    datastore.put(contactEntity);

    response.sendRedirect("/index.html");
  }
}