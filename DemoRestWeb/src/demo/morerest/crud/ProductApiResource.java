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

@Path("/productapi")
public class ProductApiResource {

    // Inject an HttpServletRequest object (allows us to inspect incoming HTTP request parameters - see the "GET" method).
    @Context 
    private HttpServletRequest request;

    // Inject a ServletContext object (allows us to access HTTP application state, where we store a collection of Products).
    @Context
    private ServletContext context;
    
    // Name of server computer (set in the constructor, and returned in custom HTTP headers from our RESTful methods).
    private String hostName;
    
    // Constructor, sets the name of the server computer.
    public ProductApiResource() {
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            hostName = "Unknown";
        }
    }
   
    // ===================================================================================================================
    // Gets a product from application state (based on product id), and returns as an appropriate type.
    // 
    // If there is no "products" collection in session state, or the product id isn't recognized:
    //   - Builds an HTTP response with a status code of "not found" (i.e. 404).
    //
    // Otherwise:
    //   - Builds an HTTP response with a status code of "OK" (i.e. 200).
    //   - Adds a couple of custom headers to the HTTP response (for illustrative purposes).
    //   - Populates the response body with the requested product (as plain text, XML, or JSON).
    // ===================================================================================================================
    @GET
    @Path("/{id}")
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
    public Response getProductGeneralPurpose(@PathParam("id") int id) {
    
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
    	
        // If the HTTP "Accept" header is "text/plain", return the product as a string!
        // Otherwise, just return an entire Product object (JAX-RS will automatically serialize into XML or JSON accordingly). 
        ResponseBuilder rb;
        if (request.getHeader("Accept").indexOf(MediaType.TEXT_PLAIN) != -1) {
            rb = Response.ok(product.toString());
        }
        else {
            rb = Response.ok(product);
        }
            
        return rb.header("Custom_HostName", hostName)
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
    //   - Populates the HTTP body with new new product (including the ID, which is set by this method).
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
            uri = new URI("/{%d}" + product.getId());
        } catch (URISyntaxException ex) {}

        // Build and return an HTTP response. Note the following points:
        //   - The created() method implicitly sets the HTTP status code to 201.
        //   - The created() method takes a parameter that indicates the URI of the newly inserted Product object.
        //   - The entity() method returns the inserted Product object (JAX-RS will automatically serialize as XML or JSON).
        //
        // Note, Response.created(uri) is equivalent to Response.status(Status.CREATED).location(uri).
        return Response.created(uri)
                       .entity(product)
                       .header("Custom_HostName", hostName)
                       .header("Custom_Timestamp", new Date().toString())
                       .build();        
    }

    // ===================================================================================================================
    // Updates an existing product in application state. JAX-RS creates the method's Product object parameter as follows:
    //   - JAX-RS receives XML or JSON data from the client, representing a product.
    //   - JAX-RS first creates an empty Product object, via the no-arg constructor in the Product class.
    //   - JAX-RS then calls setId(), setDescription(), and setUnitPrice() to populate the Product object.
    //
    // This method follows the "rules" for REST PUT requests:
    //   - Sets the status code to "OK" (i.e. 200).
    //   - Returns an empty response body.
    // ===================================================================================================================
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
    public Response updateProduct(Product product) {

        // Try to get the "products" collection from application state.
        HashMap<Integer, Product> products = (HashMap<Integer, Product>)context.getAttribute("products");
        if (products == null) {
            products = new HashMap<Integer, Product>();
            context.setAttribute("products", products);
        }

        // Ensure the "products" collection contains the specified key.
        if (!products.containsKey(product.getId())) {
            return Response.status(Status.NOT_FOUND).build();
        }

        // Store the product in the "products" collection, to update an existing item in the collection.
        products.put(product.getId(), product);
        
        return Response.ok()
                       .header("Custom_HostName", hostName)
                       .header("Custom_Timestamp", new Date().toString())
                       .build();        
    }


    // ===================================================================================================================
    // Deletes an existing product in application state (using the id in the path parameter).
    // 
    // If there is no products collection in application state, or the product id isn't recognized:
    //   - Builds an HTTP response with a status code of "not found" (i.e. 404).
    //
    // Otherwise:
    //   - Builds an HTTP response with a status code of "OK" (i.e. 200).
    //   - Adds a couple of custom headers to the HTTP response (for illustrative purposes).
    // ===================================================================================================================
    @DELETE
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") int id) {

        // Try to get the "products" collection from application state.
        HashMap<Integer, Product> products = (HashMap<Integer, Product>)context.getAttribute("products");
        if (products == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        
        // Ensure the "products" collection contains the specified key.
        if (!products.containsKey(id)) {
            return Response.status(Status.NOT_FOUND).build();
        }
        
        // Remove the product from the "products" collection.
        products.remove(id);
        
        return Response.ok()
                       .header("Custom_HostName", hostName)
                       .header("Custom_Timestamp", new Date().toString())
                       .build();        
    }
}
