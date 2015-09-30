package demo.morerest.crud;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent e) {
        ServletContext context = e.getServletContext();
        HashMap<Integer, Product> products = new HashMap<>();
        context.setAttribute("products", products);
    }

    public void contextDestroyed(ServletContextEvent e) {
        // Nothing happening here. Move along please.
    }

}
