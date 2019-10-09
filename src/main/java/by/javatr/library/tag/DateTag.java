package by.javatr.library.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTag extends TagSupport {
    private static final String PATTERN = "MM/dd/yyyy HH:mm:ss";

    @Override
    public int doStartTag() throws JspException {
        DateFormat dateFormat = new SimpleDateFormat(PATTERN);
        Date date = Calendar.getInstance().getTime();
        String today = dateFormat.format(date);
        try {
            JspWriter out = pageContext.getOut();
            out.write(today);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
