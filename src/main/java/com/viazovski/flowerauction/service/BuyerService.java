package com.viazovski.flowerauction.service;

import com.google.common.hash.Hashing;
import com.viazovski.flowerauction.exception.RepositoryException;
import com.viazovski.flowerauction.exception.ServiceException;
import com.viazovski.flowerauction.model.Buyer;
import com.viazovski.flowerauction.repository.BuyerRepository;
import com.viazovski.flowerauction.specification.buyer.*;
import com.viazovski.flowerauction.validationmessage.BuyerWithSignInMessage;
import com.viazovski.flowerauction.validationmessage.ChangePasswordValidationMessage;
import com.viazovski.flowerauction.validationmessage.SignInValidationMessage;
import com.viazovski.flowerauction.validationmessage.SignUpValidationMessage;
import org.apache.commons.lang.RandomStringUtils;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * {@code BuyerService} serves as a bridge between command and
 * repository layer. It receives input regarding buyers from a client
 * validates it and relays responsibility for database operations to
 * repository layer.
 */
public class BuyerService {

    private static final String PASSWORD_REGEX = ".+";

    private BuyerRepository repository = new BuyerRepository();

    public Buyer addUser(String login, String password) throws ServiceException {
        var buyer = createBuyer(login, password);
        var buyerId = addBuyerAndGetId(buyer);
        buyer.setBuyerId(buyerId);
        return buyer;
    }

    public void removeUser(int userId) throws ServiceException {
        try {
            repository.nonQuery(new RemoveBuyerByIdSpecification(userId));
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Buyer> findAllBuyers() throws ServiceException {
        try {
            return repository.selectAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void updateLocale(String login, String locale) throws ServiceException {
        try {
            repository.nonQuery(new UpdateBuyerLocaleSpecification(locale, login));
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void updatePassword(String login, String password) throws ServiceException {
        try {
            var buyer = findBuyerByLogin(login).orElseThrow();
            var passwordHash = hashPassword(buyer.getPasswordSalt(), password);
            repository.nonQuery(new UpdateBuyerPasswordByLoginSpecification(login, passwordHash));
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void updateProfile(String firstName, String lastName, Date dob, String login) throws ServiceException {
        try {
            repository.nonQuery(
                    new UpdateBuyerProfileByLoginSpecification(
                            firstName, lastName, dob, login));
        } catch (RepositoryException e) {
            throw new ServiceException("Unable to update user", e);
        }
    }

    public Optional<Buyer> findBuyerByLogin(String login) throws ServiceException {
        try {
            var buyerList = repository.query(new SelectBuyerByLoginSpecification(login));
            return !buyerList.isEmpty() ?
                    Optional.of(buyerList.get(0)) :
                    Optional.empty();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Buyer> findAllAcceptedBuyers() throws ServiceException {
        try {
            return repository.query(new SelectAllAcceptedBuyersSpecification());
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public List<Buyer> findRequestedBuyers() throws ServiceException {
        try {
            return repository.query(new SelectRequestedBuyersSpecification());
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Old password must match existing password.
     * New password must match {@link #PASSWORD_REGEX}.
     * New password must match confirm new password.
     *
     * @param login client's login input.
     * @param oldPassword client's oldPassword input.
     * @param newPassword client's newPassword input.
     * @param confirmNewPassword client's confirmNewPassword input.
     * @return validation message.
     * @throws ServiceException if {@link #findBuyerByLogin(String)} does.
     */
    public ChangePasswordValidationMessage validateChangePasswordForm(String login, String oldPassword, String newPassword, String confirmNewPassword) throws ServiceException {
        var validationMessage = new ChangePasswordValidationMessage();
        if (!newPassword.equals(confirmNewPassword)) {
            var msg = "New Password and Confirm New Password do not match";
            validationMessage.setNewPasswordError(msg);
            validationMessage.setConfirmNewPasswordError(msg);
        }
        if (!newPassword.matches(PASSWORD_REGEX)) {
            validationMessage.setNewPasswordError("Password doesn't match PASSWORD_REGEX");
        }
        findBuyerByLogin(login)
                .filter(buyer -> !isCorrectPassword(buyer, oldPassword))
                .ifPresent(b -> validationMessage.setOldPasswordError("Incorrect Old Password"));
        return validationMessage;
    }

    /**
     * Login must exist.
     * Password must match existing password.
     *
     * @param login client's login input.
     * @param password client's password input.
     * @return validation message.
     * @throws ServiceException if {@link #findBuyerByLogin(String)} does.
     */
    public BuyerWithSignInMessage validateSignInForm(String login, String password) throws ServiceException {
        var emptyValidationMessage = new SignInValidationMessage();
        var validationMessage = new SignInValidationMessage();
        var msg = "Incorrect login or password";
        validationMessage.setSignInLoginError(msg);
        validationMessage.setSignInPasswordError(msg);
        return findBuyerByLogin(login)
                .filter(b -> isCorrectPassword(b, password))
                .map(b -> new BuyerWithSignInMessage(b, emptyValidationMessage))
                .orElse(new BuyerWithSignInMessage(null, validationMessage));
    }

    /**
     * Login must not yet exist.
     * Password must match confirmPassword.
     * Password must match {@link #PASSWORD_REGEX}.
     *
     * @param login client's login input.
     * @param password client's password input.
     * @param confirmPassword client's confirmPassword input.
     * @return validation message.
     * @throws ServiceException if {@link #findBuyerByLogin(String)} does.
     */
    public SignUpValidationMessage validateSignUpForm(String login, String password, String confirmPassword) throws ServiceException {
        var validationMessage = new SignUpValidationMessage();
        findBuyerByLogin(login).ifPresent(
                b -> validationMessage.setSignUpLoginError("Login already exists"));
        if (!password.equals(confirmPassword)) {
            var msg = "Password and Confirm Password do not match";
            validationMessage.setSignUpPasswordError(msg);
            validationMessage.setSignUpConfirmPasswordError(msg);
        }
        if (!password.matches(PASSWORD_REGEX)) {
            validationMessage.setSignUpPasswordError("Password doesn't match PASSWORD_REGEX");
        }
        return validationMessage;
    }

    private Buyer createBuyer(String login, String password) {
        var buyer = new Buyer();
        buyer.setLogin(login);
        String passwordSalt = RandomStringUtils.randomAscii(50);
        String passwordHash = hashPassword(passwordSalt, password);
        buyer.setPasswordHash(passwordHash);
        buyer.setPasswordSalt(passwordSalt);
        return buyer;
    }

    private boolean isCorrectPassword(Buyer buyer, String password) {
        var passwordHash = hashPassword(buyer.getPasswordSalt(), password);
        return buyer.getPasswordHash().equals(passwordHash);
    }

    /**
     * {@code hashPassword} uses SHA-256 algorithm to hash user's password.
     * To get better security salt is prepended to password. Often users use
     * short passwords and most likely hackers already have a dictionary of
     * calculated hashes. Salt also makes impossible to determine whether 2 users
     * have the same password or not.
     *
     * @param passwordSalt is a long {@code String} salt value.
     * @param password is user's password.
     * @return password hash.
     */
    public String hashPassword(String passwordSalt, String password) {
        return Hashing.sha256()
                .hashString(passwordSalt + password, StandardCharsets.UTF_8)
                .toString();
    }

    private int addBuyerAndGetId(Buyer buyer) throws ServiceException {
        try {
            return repository.add(buyer).getBuyerId();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
