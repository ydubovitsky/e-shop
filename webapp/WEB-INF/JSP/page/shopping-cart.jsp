<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<div id="shoppingCard">
    <div class="alert alert-warning" role="alert">To make order, please sign in</div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Product</th>
            <th>Price</th>
            <th>Count</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <tr id="product278009" class="item">
            <td class="text-center"><img src="media/iphone.png" alt="Prestigio SH398187"><br>Prestigio SH398187</td>
            <td class="price">$ 500.00</td>
            <td class="count">1</td>
            <td>
                <a class="btn btn-danger remove-product" data-id-product="278009" data-count="1">Remove one</a>
            </td>
        </tr>
        <tr id="product278014" class="item">
            <td class="text-center"><img src="media/motorola.png" alt="EvroMedia NU6353951"><br>EvroMedia NU6353951</td>
            <td class="price">$ 1500.00</td>
            <td class="count">2</td>
            <td>
                <a class="btn btn-danger remove-product" data-id-product="278014" data-count="1">Remove one</a><br><br>
                <a class="btn btn-danger remove-product all" data-id-product="278014" data-count="2">Remove all</a>
            </td>
        </tr>
        <tr>
            <td colspan="2" class="text-right"><strong>Total:</strong></td>
            <td colspan="2" class="total">$0.00</td>
        </tr>
        </tbody>
    </table>
    <div class="row">
        <div class="col-md-4 col-md-offset-4 col-lg-2 col-lg-offset-5">
            <a href="#" class="btn btn-primary btn-block"><i class="fab fa-facebook-square"></i> Sign in
            </a>
        </div>
    </div>
</div>
