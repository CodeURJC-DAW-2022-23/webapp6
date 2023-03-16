package es.codeurjc.readmebookstore.controller.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.RequestDispatcher;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class PageErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(Model model, HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("404", true);
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("403", true);
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("500", true);
            } else {
                model.addAttribute("else", true);
            }
            model.addAttribute("error", statusCode);
        }
        return "error-page";
    }

    @Override
    public String getErrorPath() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getErrorPath'");
    }

}
