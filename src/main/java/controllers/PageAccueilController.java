package controllers ;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageAccueilController implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "Accueil.jsp";
    }
}
