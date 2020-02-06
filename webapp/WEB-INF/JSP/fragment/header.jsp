<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/products">Electronics store</a>
    <div class="collapse navbar-collapse" id="ishopNav">
        <ul id="currentShoppingCart" class="nav navbar-nav navbar-right hidden">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <i class="fa fa-shopping-cart" aria-hidden="true"></i> Shopping cart (<span class="total-count">0</span>)<span class="caret"></span>
                    </a>
                    <div class="dropdown-menu shopping-cart-desc">
                        Total count: <span class="total-count">0</span><br>
                        Total cost: <span class="total-cost">0</span><br>
                        <a href="/shopping-cart" class="btn btn-primary btn-block">View cart</a>
                    </div>
                </li>
            </ul>
            <a href="#" class="btn btn-primary navbar-btn navbar-right sign-in"><i class="fa fa-facebook-official" aria-hidden="true"></i> Sign in</a>
        </ul>
    </div>
</nav>