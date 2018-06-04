<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang.toString()}"/>
<fmt:setBundle basename="bundle"/>
<form>
    <div class="form-group">
        <label for="current-bid">Current Bid</label>
        <input type="text" class="form-control" contenteditable="false" id="current-bid"/>
    </div>
    <div class="form-group">
        <button type="button" class="form-control btn btn-light">Add 10%</button>
    </div>
    <div class="form-group">
        <label for="your-bid">Your bid</label>
        <input type="text" class="form-control" id="your-bid"/>
        <button type="button" class="form-control btn btn-light">Make Bid</button>
    </div>
    <div class="form-group">
        <button type="button" class="form-control btn btn-light">Give Up</button>
    </div>
</form>