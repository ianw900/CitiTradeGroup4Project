package demo.morerest.crud;

import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

@Path("/productform")
public class ProductFormResource {

    // Inject an HttpServletRequest object (allows us to inspect incoming HTTP request parameters - see the final "GET" method).
    @Context 
    private HttpServletRequest request;

    // Inject a ServletContext object (allows us to access HTTP application state, where we store a collection of Products).
    @Context
    private ServletContext context;
    
    // Name of server computer (set in the constructor, and returned in custom HTTP headers from our RESTful methods).
    private String hostName;
    
    // Constructor, sets the name of the server computer.
    public ProductFormResource() {
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            hostName = "Unknown";
        }
    }
   

    // ===================================================================================================================
    // Gets a product from application state (based on product id query-string-parameter), and returns as plain text.
    // 
    // If there is no "products" collection in application state, or the product id isn't recognized:
    //   - Builds an HTTP response with a status code of "not found" (i.e. 404).
    //
    // Otherwise:
    //   - Builds an HTTP response with a status code of "OK" (i.e. 200).
    //   - Adds a couple of custom headers to the HTTP response (for illustrative purposes).
    //   - Populates the response body with the requested product (as text).
    // ===================================================================================================================
    @GET
    @Path("/text")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getProductAsPlainText(@QueryParam("id") int id) {

        // Try to get the "products" collection from application state.
        HashMap<Integer, Product> products = (HashMap<Integer, Product>)context.getAttribute("products");
        if (products == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        
        // Try to get the product with the specified id.
        Product product = products.get(id);
        if (product == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        // Build and return an HTTP response. Note the following points:
        //   - The ok() method implicitly sets the HTTP status code to 200.
        //   - The ok() method takes a parameter that indicates what to return in the HTTP response body.
        //     In this case, we return the Product in string format (because this method's MIME type is plain text).
        //   - The HTTP response will also include a couple of custom HTTP headers, for illustrative purposes.
        //
        // Note, Response.ok(product.toString()) is equivalent to Response.status(Status.OK).entity(product.toString()).        
        return Response.ok(product.toString())
                       .header("Custom_HostName", hostName)
                       .header("Custom_Timestamp", new Date().toString())
                       .build();        
    }

    
    // ===================================================================================================================
    // Gets a product from application state (based on product id query-string-parameter), and returns as XML.
    // 
    // If there is no "products" collection in application state, or the product id isn't recognized:
    //   - Builds an HTTP response with a status code of "not found" (i.e. 404).
    //
    // Otherwise:
    //   - Builds an HTTP response with a status code of "OK" (i.e. 200).
    //   - Adds a couple of custom headers to the HTTP response (for illustrative purposes).
    //   - Populates the response body with the requested product (JAX-RS will use a JAXB binder implicitly).
    // ===================================================================================================================
    @GET
    @Path("/xml")
    @Produces({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
    public Response getProductAsXml(@QueryParam("id") int id) {
    
        // Try to get the "products" collection from application state.
        HashMap<Integer, Product> products = (HashMap<Integer, Product>)context.getAttribute("products");
        if (products == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        
        // Try to get the product with the specified id.
        Product product = products.get(id);
        if (product == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        // Build and return an HTTP response. Note the following points:
        //   - The ok() method takes an entire Product object as a parameter.
        //     JAX-RS will use a JAXB binder to return the object in XML format (because this method's MIME type is XML).
        return Response.ok(product)
                       .header("Custom_HostName", hostName)
                       .header("Custom_Timestamp", new Date().toString())
                       .build();        
    }

    
    // ===================================================================================================================
    // Gets a product from application state (based on product id), and returns as JSON.
    // 
    // If there is no "products" collection in application state, or the product id isn't recognized:
    //   - Builds an HTTP response with a status code of "not found" (i.e. 404).
    //
    // Otherwise:
    //   - Builds an HTTP response with a status code of "OK" (i.e. 200).
    //   - Adds a couple of custom headers to the HTTP response (for illustrative purposes).
    //   - Populates the response body with the requested product (JAX-RS will use a JSON binder implicitly).
    // ===================================================================================================================
    @GET
    @Path("/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductAsJson(@QueryParam("id") int id) {
    
        // Try to get the "products" collection from application state.
        HashMap<Integer, Product> products = (HashMap<Integer, Product>)context.getAttribute("products");
        if (products == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        
        // Try to get the product with the specified id.
        Product product = products.get(id);
        if (product == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        // Build and return an HTTP response. Note the following points:
        //   - The ok() method takes an entire Product object as a parameter.
        //     JAX-RS will use a JSON binder to return the object in JSON format (because this method's MIME type is JSON).
        return Response.ok(product)
                       .header("Custom_HostName", hostName)
                       .header("Custom_Timestamp", new Date().toString())
                       .build();        
    }

    // ===================================================================================================================
    // Gets a product from application state (based on product ID), and returns as an appropriate type.
    // 
    // If there is no products collection in application state, or the product ID isn't recognized:
    //   - Builds an HTTP response with a status code of "not found" (i.e. 404).
    //
    // Otherwise:
    //   - Builds an HTTP response with a status code of "OK" (i.e. 200).
    //   - Adds a couple of custom headers to the HTTP response (for illustrative purposes).
    //   - Populates the response body with the requested product (as plain text, XML, or JSON).
    // ===================================================================================================================
    @GET
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
    public Response getProductGeneralPurpose(@QueryParam("id") int id) {
    
        // Try to get the "products" collection from application state.
        HashMap<Integer, Product> products = (HashMap<Integer, Product>)context.getAttribute("products");
        if (products == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        
        // Try to get the product with the specified id.
        Product product = products.get(id);
        if (product == null) {
            return Response.status(Status.NOT_FOUND).build();
        }

        // Test which button the user clicked in the form ("GetXml", "GetJson" or "GetText").
        // Set the HTTP response body and MIME type accordingly.
        ResponseBuilder rb;
        if (request.getParameter("GetXml") != null) {
            rb = Response.ok(product, MediaType.APPLICATION_XML);
        }
        else if (request.getParameter("GetJson") != null) {
            rb = Response.ok(product, MediaType.APPLICATION_JSON);
        }
        else {
            rb = Response.ok(product.toString(), MediaType.TEXT_PLAIN);
        }
        
        return rb.header("Custom_HostName", hostName)
                 .header("Custom_Timestamp", new Date().toString())
                 .build();        
    }

    // ===================================================================================================================
    // Inserts a new product into application state (using the description and unit price posted as an HTTP form).
    // 
    // This method follows the "rules" for REST POST requests:
    //   - Sets the status code to "created" (i.e. 201).
    //   - Sets the Location header to the URL of the new product.
    //   - Populates the HTTP body with new new product (including the id, which is set by this method).
    // ===================================================================================================================
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertProduct(
          @FormParam("description") String description,
          @FormParam("unitPrice") double unitPrice) {

        // Try to get the "products" collection from application state.
        HashMap<Integer, Product> products = (HashMap<Integer, Product>)context.getAttribute("products");
        if (products == null) {
            products = new HashMap<Integer, Product>();
            context.setAttribute("products", products);
        }
        
        // Create a new Product and add it to the "products" collection.
        Product product = new Product(description, unitPrice);
        int id = product.assignNextId();
        products.put(id, product);

        // Create a URI object, representing the partial URL for the newly inserted Product object (tells clients how to GET it).
        URI uri = null;
        try {
            uri = new URI("?id=" + product.getId());
        } catch (URISyntaxException ex) {}

        // Build and return an HTTP response. Note the following points:
        //   - The created() method implicitly sets the HTTP status code to 201.
        //   - The created() method takes a parameter that indicates the URI of the newly inserted Product object.
        //   - The entity() method returns the inserted Product object (as a string).
        //
        // Note, Response.created(uri) is equivalent to Response.status(Status.CREATED).location(uri).
        return Response.created(uri)
                       .entity(product.toString())
                       .header("Custom_HostName", hostName)
                       .header("Custom_Timestamp", new Date().toString())
                       .build();        
    }

    // ===================================================================================================================
    // Inserts a new product into application state. JAX-RS creates the method's Product object parameter as follows:
    //   - JAX-RS receives XML or JSON data from the client, representing a product.
    //   - JAX-RS first creates an empty Product object, via the no-arg constructor in the Product class.
    //   - JAX-RS then calls setDescription() and setUnitPrice() to populate the Product object.
    // 
    // This method follows the "rules" for REST POST requests:
    //   - Sets the status code to "created" (i.e. 201).
    //   - Sets the Location header to the URL of the new product.
    //   - Populates the HTTP body with new new product (including the id, which is set by this method).
    // ===================================================================================================================
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
    public Response insertProductObject(Product product) {

        // Try to get the "products" collection from application state.
        HashMap<Integer, Product> products = (HashMap<Integer, Product>)context.getAttribute("products");
        if (products == null) {
            products = new HashMap<Integer, Product>();
            context.setAttribute("products", products);
        }
        
        // Add the Product object to the "products" collection.
        int id = product.assignNextId();
        products.put(id, product);

        // Create a URI object, representing the partial URL for the newly inserted Product object (tells clients how to GET it).
        URI uri = null;
        try {
            uri = new URI("/" + product.getId());
        } catch (URISyntaxException ex) {}

        // Build and return an HTTP response. Note the following points:
        //   - The created() method implicitly sets the HTTP status code to 201.
        //   - The created() method takes a parameter that indicates the URI of the newly inserted Product object.
        //   - The entity() method returns the inserted Product object (as a string).
        //
        // Note, Response.created(uri) is equivalent to Response.status(Status.CREATED).location(uri).
        return Response.created(uri)
                       .entity(product.toString())
                       .header("Custom_HostName", hostName)
                       .header("Custom_Timestamp", new Date().toString())
                       .build();        
    }
}
