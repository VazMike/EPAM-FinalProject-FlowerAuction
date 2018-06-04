package com.viazovski.flowerauction.service;

import com.viazovski.flowerauction.exception.RepositoryException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.model.Buyer;
import com.viazovski.flowerauction.repository.BuyerRepository;
import com.viazovski.flowerauction.specification.buyer.RemoveBuyerByIdSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BuyerServiceTest {

    private static Logger logger = LogManager.getLogger();

    private BuyerService service = new BuyerService();

    private int buyerId;

    private static final String DUMMY_LOGIN = "Hopefully unique login buyer service";

    private static final String DUMMY_PASSWORD = "Password";

    private static final String UNUSED_LOGIN = "hopefully unused login buyer service";

    @BeforeClass(groups = {"service", "validation"})
    public void addDummyBuyer() throws RepositoryException {
        var buyer = new Buyer();
        buyer.setLogin(DUMMY_LOGIN);
        buyer.setPasswordSalt("");
        buyer.setPasswordHash(service.hashPassword("", DUMMY_PASSWORD));
        buyerId = new BuyerRepository().add(buyer).getBuyerId();
    }

    @AfterClass(groups = {"service", "validation"})
    public void removeDummyBuyer() throws RepositoryException {
        new BuyerRepository().nonQuery(new RemoveBuyerByIdSpecification(buyerId));
    }

    @Test(groups = {"service", "validation"})
    public void testPositiveValidateChangePasswordForm() throws ServiceException {
        var isValid = service.validateChangePasswordForm(
                DUMMY_LOGIN, DUMMY_PASSWORD, "pass", "pass").isEmpty();
        Assert.assertTrue(isValid);
        logger.info("Positive change password validation test passed");
    }

    @Test(groups = {"service", "validation"})
    public void testNegativeNonMatchingPasswordsValidateChangePasswordForm() throws ServiceException {
        var isValid = service.validateChangePasswordForm(
                DUMMY_LOGIN, DUMMY_PASSWORD, "pass", "pass2").isEmpty();
        Assert.assertFalse(isValid);
        logger.info("Negative non matching passwords change password validation test passed");
    }

    @Test(groups = {"service", "validation"})
    public void testNegativeIncorrectOldPasswordValidateChangePasswordForm() throws ServiceException {
        var isValid = service.validateChangePasswordForm(
                DUMMY_LOGIN, DUMMY_PASSWORD + "append", "pass", "pass").isEmpty();
        Assert.assertFalse(isValid);
        logger.info("Negative incorrect old password change password validation test passed");
    }

    @Test(groups = {"service", "validation"})
    public void testPositiveValidateSignInForm() throws ServiceException {
        var isValid = service.validateSignInForm(DUMMY_LOGIN, DUMMY_PASSWORD)
                .signInValidationMessage.isEmpty();
        Assert.assertTrue(isValid);
        logger.info("Positive sign in validation test passed");
    }

    @Test(groups = {"service", "validation"})
    public void testNegativeValidateSignInForm() throws ServiceException {
        var isValid = service.validateSignInForm(DUMMY_LOGIN, DUMMY_PASSWORD + "append")
                .signInValidationMessage.isEmpty();
        Assert.assertFalse(isValid);
        logger.info("Negative sign in validation test passed");
    }

    @Test(groups = {"service", "validation"})
    public void testPositiveValidateSignUpForm() throws ServiceException {
        var password = "pass";
        var confirmPassword = "pass";
        var isValid = service.validateSignUpForm(UNUSED_LOGIN, password, confirmPassword).isEmpty();
        Assert.assertTrue(isValid);
        logger.info("Positive sign up validation test passed");
    }

    @Test(groups = {"service", "validation"})
    public void testNegativeNonMatchingPasswordsValidateSignUpForm() throws ServiceException {
        var password = "pass";
        var confirmPassword = "pass2";
        var isValid = service.validateSignUpForm(UNUSED_LOGIN, password, confirmPassword).isEmpty();
        Assert.assertFalse(isValid);
        logger.info("Negative non matching passwords sign up validation test passed");
    }

    @Test(groups = {"service", "validation"})
    public void testNegativeAlreadyExistingLoginValidateSignUpForm() throws ServiceException {
        var password = "pass";
        var confirmPassword = "pass";
        var isValid = service.validateSignUpForm(DUMMY_LOGIN, password, confirmPassword).isEmpty();
        Assert.assertFalse(isValid);
        logger.info("Negative already existing login sign up validation test passed");
    }
}