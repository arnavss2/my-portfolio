package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet responsible for listing tasks. */
@WebServlet("/list-contacts")
public class ListContactsServlet extends HttpServlet {
    private static String CONTACT_CONST = "Contact";
    private static String SMALL_NAME_CONST = "name";
    private static String SMALL_EMAIL_CONST = "email";
    private static String SMALL_MESSAGE_CONST = "message";
    private static String NAME_CONST = "Name: ";
    private static String EMAIL_CONST = "Email: ";
    private static String MESSAGE_CONST = "Message: ";

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    Query<Entity> query =
        Query.newEntityQueryBuilder().setKind(CONTACT_CONST).setOrderBy(OrderBy.desc(SMALL_NAME_CONST)).build();
    QueryResults<Entity> results = datastore.run(query);

    List<Contact> contacts = new ArrayList<>();
    while (results.hasNext()) {
      Entity entity = results.next();

      long id = entity.getKey().getId();
      String name = entity.getString(SMALL_NAME_CONST);
      String email = entity.getString(SMALL_EMAIL_CONST);
      String message = entity.getString(SMALL_MESSAGE_CONST);

      Contact contact = new Contact(id, name, email, message);
      contacts.add(contact);
    }

    Gson gson = new Gson();

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(contacts));
  }
}
