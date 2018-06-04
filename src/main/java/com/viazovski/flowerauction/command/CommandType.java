package com.viazovski.flowerauction.command;

/**
 * Whenever user sends HTTP-request a command gets executed.
 * {@code CommandType} represents every possible command.
 */
public enum CommandType {
    ACCEPT_BUYER(new AcceptBuyerCommand()),
    ACCEPT_FLOWER(new AcceptFlowerCommand()),
    ADD_AUCTION(new AddAuctionCommand()),
    ADD_CREDIT_CARD(new AddCreditCardCommand()),
    ADD_FLOWER(new AddFlowerCommand()),
    BUSINESS_PROFILE(new BusinessProfileCommand()),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    EDIT_AUCTION(new EditAuctionCommand()),
    EDIT_FLOWER(new EditFlowerCommand()),
    EDIT_PROFILE(new EditProfileCommand()),
    EMPTY(new EmptyCommand()),
    FIND_AVAILABLE_FLOWERS(new FindAvailableFlowersCommand()),
    MAKE_AUCTION_REQUEST(new MakeAuctionRequestCommand()),
    MAKE_FLOWER_REQUEST(new MakeFlowerRequestCommand()),
    MANAGE_AUCTIONS(new ManageAuctionsCommand()),
    MANAGE_REQUESTS(new ManageRequestsCommand()),
    MANAGE_USERS(new ManageUsersCommand()),
    PROFILE(new ProfileCommand()),
    REJECT_BUYER(new RejectBuyerCommand()),
    REJECT_FLOWER(new RejectFlowerCommand()),
    REMOVE_AUCTION(new RemoveAuctionCommand()),
    REMOVE_CREDIT_CARD(new RemoveCreditCardCommand()),
    REMOVE_FLOWER(new RemoveFlowerCommand()),
    REMOVE_USER(new RemoveUserCommand()),
    REQUEST(new RequestCommand()),
    SIGN_IN(new SignInCommand()),
    SIGN_OUT(new SignOutCommand()),
    SIGN_UP(new SignUpCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
