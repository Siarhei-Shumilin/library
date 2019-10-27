package by.javatr.library.command.impl.common;

import by.javatr.library.command.Command;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.User;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class LanguageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request) {
        CommandResult commandResult = null;
        HttpSession session = request.getSession();
        String language = request.getParameter("language");
        Locale locale = new Locale(language);
        session.setAttribute("locale", locale);
        User user = (User) session.getAttribute("user");
        if (user!=null){
            commandResult = new CommandResult(Constants.MAIN_COMMAND, true);
        } else {
            commandResult = new CommandResult(Constants.LOGIN, false);
        }
        return commandResult;
    }
}
