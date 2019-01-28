package resting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author crazzyman
 */
@Path("/")
public class RestKlase {

    static List<Item> list = new ArrayList<>();
    static List<ItemDAO> list2 = new ArrayList<>();
    private static long i = 1;
    private static long icom = 1;

    // WELCOME on http://localhost:8080/RestWeb/rest/
    @GET
    @Produces("text/plain")
    public String grazinti() {
        return "Rest API uzduotis!";
    }

    // API should be able to create item 
    @POST
    @Path("/items/new")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public Item addItem(Item item )  {

        Item preke = new Item();

        preke.setId(i++);
        preke.setTitle(item.getTitle());
        preke.setPrice(item.getPrice());
        preke.setStock(item.getStock());
        preke.setDescription(item.getDescription());
        preke.setLocation(item.getLocation());

        list.add(preke);

        return preke;
    }

    
    
    // update item
    @PUT
    @Path("/items/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public Item update(@PathParam("id") Long id, Item p) {

        if (p == null || p.getId() == null) {
            return null;
        }

        for (Item item : list) {
            if (id.equals(item.getId())) {

                item.setTitle(p.getTitle());
                item.setPrice(p.getPrice());
                item.setStock(p.getStock());
                item.setDescription(p.getDescription());
                item.setLocation(p.getLocation());

//        if (description != null) {
//            item.setDescription(description);
//            }
         
            }

        }
        return p;
    }

    // get a list of items
    @GET
    @Path("/items")
    @Produces("application/json")
    public List<Item> getall() {

        return list;
    }

    
    //  get a singe item by id
    @GET
    @Path("/items/{id}")
    @Produces("application/json")
    public Item get(@PathParam("id") Long id) {

        if (id == null) {
            return null;
        }

        for (Item item : list) {
            if (id.equals(item.getId())) {

                return item;
            }
        }
        return null;
//        throw new RuntimeException("Get: item with " + id +  " not found");

    }

    
    
    // delete item by id
    @DELETE
    @Path("/items/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Item> delete(@PathParam("id") Long id) {

        if (id == null) {
            return null;
        }

        for (Item g : list) {
            if (g.getId().equals(id)) {
                list.remove(g);
                return list;
            }
        }

        return list;
    }

    @GET
    @Path("/items/search/{title}")
    @Produces("application/json")
    public List<Item> search(@PathParam("title") String title) {

        if (title == null) {
            return null;
        }

        List<Item> titlelist = new ArrayList<>();

        for (Item item : list) {
            if (title.equals(item.getTitle())) {

                titlelist.add(item);

            }

        }

        return titlelist;
        //        throw new RuntimeException("Get: item with this title " + title +  " not found");

    }

    @GET
    @Path("/items/search/{low}/{high}")
    @Produces("application/json")
    public List<Item> searchprice(@PathParam("low") Double low, @PathParam("high") Double high) {

        if ((low == null) && (high == null)) {
            return null;
        }

        List<Item> titlelist = new ArrayList<>();

        for (Item item : list) {

            if ((item.getPrice() > low) && (item.getPrice() < high)) {

                titlelist.add(item);

            }
        }

        return titlelist;

    }

//    @GET
//    @Path("/items/search/{price}")
//    @Produces("text/plain")
//    public List<Item> searchprice(@PathParam("price") String price, @QueryParam("low") Double low, @QueryParam("high") Double high) {
//
//        if ( (low == null) && (high == null) ) {
//            return null;
//        }
//        
//
//        List<Item> titlelist = new ArrayList<>();
//        
//        for (Item item : list) {
// 
//            if (item.getPrice() > low )  {
//                    
//                    titlelist.add(item);
//                     
//                   
//                }
//            }
//                 
//         return titlelist; 
//  
//    }
    
    
    
    // for comments list with Database i would use @OneToMany 
    @POST
    @Path("/items/{id}/sendcomment")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Comment> getcomments(@PathParam("id") Long id, Comment msg) {

        if (id == null) {
            return null;
        }

        Comment com = new Comment();
        com.setCommentID(icom++);
        com.setMessage(msg.getMessage());

        for (Item item : list) {
            if (id.equals(item.getId())) {

                item.getMsg().add(com);

                return item.getMsg();
            }
        }
        return null;
    }

    @GET
    @Path("/items/{id}/getcomments")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Comment> getcomments(@PathParam("id") Long id) {

        if (id == null) {
            return null;
        }

        for (Item item : list) {
            if (id.equals(item.getId())) {

                return item.getMsg();
            }
        }
        return null;
//        throw new RuntimeException("Get: item with " + id +  " not found");

    }

    
//    THE END of TASK
    
    
    
    
    
    
    
    
    // -------------------------------------------------------- //
    // tryed another way
    // redirect to html form
    @GET
    @Path("/create")
    public void create(@Context HttpServletResponse servletResponse) throws IOException {

        servletResponse.sendRedirect("/RestWeb/creating.html");

    }

    @POST
    @Path("/create")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON})
    public void addItem2(
            @FormParam("title") String title,
            @FormParam("price") Double price,
            @FormParam("stock") Integer stock,
            @FormParam("country") String country,
            @FormParam("city") String city,
            @FormParam("street") String street,
            @FormParam("gps") String gps,
            @FormParam("description") String description,
            @Context HttpServletResponse servletResponse
    ) throws IOException {


        Item preke = new Item();
        preke.setId(i++);
        preke.setTitle(title);
        preke.setPrice(price);
        preke.setStock(stock);
        preke.setDescription(description);
        preke.setLocation(new Location(country, city, street, gps));

        if (description != null) {
            preke.setDescription(description);
        }

        list.add(preke);

        servletResponse.sendRedirect("items");
    }

    //-----------------------
    //  create item with html form
    @POST
    @Path("/old")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public ItemDAO addItemDAO(
            Item item
    )  {

        ItemDAO preke = new ItemDAO(item);

        preke.setId(i++);


        list2.add(preke);

        return preke;
    }

    // get a list of itemsDAO
    @GET
    @Path("/lots")
    @Produces("application/json")
    public List<ItemDAO> getallDao() {

        return list2;
    }

    @POST
    @Path("/newcreate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public static Item add(Item p) {
        p.setId(++i);
        list.add(p);

        return p;
    }

}
