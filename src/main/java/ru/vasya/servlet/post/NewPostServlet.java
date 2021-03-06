package ru.vasya.servlet.post;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vasya.model.staff.Post;
import ru.vasya.service.DerbyService;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dyapparov on 07.07.2016.
 */
public class NewPostServlet extends HttpServlet{
    private static final Logger LOGGER = LoggerFactory.getLogger(NewPostServlet.class);

    @EJB
    DerbyService ds;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post p = null;
        String mode = null;
        if(req.getParameter("id")!=null){
            int id = Integer.parseInt(req.getParameter("id"));
            mode = "Edit";
            p = (Post)ds.getById(Post.class, id);
        } else {
            mode = "New";
            p = new Post();
        }

        req.setAttribute("post", p);
        req.setAttribute("mode", mode);

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/post/post.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post p = new Post();
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name").trim();
        String action = req.getParameter("action").trim();

        if ("Save".equals(action)){
            if (StringUtils.isAlpha(name) && !name.isEmpty()) {
                p = (Post) ds.getById(Post.class, id);
                p.setName(name);
                ds.updateItem(p);
                resp.sendRedirect("posts");
                return;
            }
        } else if("Delete".equals(action)) {
            p.setId(id);
            ds.deleteItem(p);
            resp.sendRedirect("posts");
            return;
        } else if ("Add".equals(action)){
            if (StringUtils.isAlpha(name) && !name.isEmpty()) {
                p.setName(name);
                if(!isDuplicate(p)) {
                    ds.insertItem(p);
                    resp.sendRedirect("posts");
                    return;
                }
            }
        }

        resp.sendRedirect("post");
    }

    private boolean isDuplicate(Post item){
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("name", item.getName());
        return ds.contains(item.getClass(), values);
    }
}
