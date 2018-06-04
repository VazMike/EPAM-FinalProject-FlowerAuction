package com.viazovski.flowerauction.command;

import com.viazovski.flowerauction.exception.CommandException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.service.BuyerService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

public class EditProfileCommand implements Command {

    /**
     * Updates user profile related information in buyer table row.
     *
     * @param   req the {@code HttpServletRequest} containing user input
     *              parameters and session attributes.
     * @return  empty json {@code String}.
     * @throws  CommandException if {@link BuyerService#updateProfile(String, String, Date, String)}
     *          throws {@link ServiceException}.
     */
    @Override
    public String execute(HttpServletRequest req) throws CommandException {
        var firstName = req.getParameter("firstName");
        var lastName = req.getParameter("lastName");
        var dob = convertToDate(req.getParameter("dob"));
        var login = (String) req.getSession().getAttribute("login");
        var service = new BuyerService();
        try {
            service.updateProfile(firstName, lastName, dob, login);
            return "{}";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

    private Date convertToDate(String stringDate) {
        var date = LocalDate.from(ISO_LOCAL_DATE.parse(stringDate));
        return Date.valueOf(date);
    }
}
