package web;

import bean.Comment;
import dao.CommentDAO;
import vo.ResultVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/comments")
public class CommentListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int postid = Integer.parseInt(req.getParameter("postid"));
        try {
            CommentDAO commentDAO = new CommentDAO();
            List<Comment> comments = commentDAO.getCommentByPostId(postid);
            PrintWriter writer = resp.getWriter();
            writer.print(ResultVO.ok(comments).toJSON());
        } catch (Exception e) {
            e.printStackTrace();
            PrintWriter writer = resp.getWriter();
            writer.write(ResultVO.err(113,e.getLocalizedMessage()).toJSON());
        }
    }
}
