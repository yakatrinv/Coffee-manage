package it.academy.controller.commands.general;

import it.academy.controller.commands.Command;
import it.academy.services.ICreditCardService;
import it.academy.services.impl.CreditCardService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static it.academy.utils.DataCreditCard.ATTR_CREDIT_CARD_ID;
import static it.academy.utils.DataGeneral.PREV_URL;

public class DeleteCreditCard implements Command {
    private final ICreditCardService service = new CreditCardService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String creditCardId = request.getParameter(ATTR_CREDIT_CARD_ID);

        Integer id = (StringUtils.isBlank(creditCardId)) ?
                null : Integer.parseInt(creditCardId);

        if (id != null) {
            service.deleteCreditCardById(id);
        }
        return request.getParameter(PREV_URL);
    }
}

