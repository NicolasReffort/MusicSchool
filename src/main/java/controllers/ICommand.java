package controllers ;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {

  /**
   * 
   * @param request
   * @param response
   * @return qqchose
   * @throws Exception
   */
  public String execute(HttpServletRequest request,HttpServletResponse response)
   throws Exception; 
}
