<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>

<div id="addProductPopup" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Add product to Shopping cart</h4>
            </div>
            <div class="modal-body row">
                <div class="col-xs-12 col-sm-6">
                    <div class="thumbnail">
                        <img src="#" alt="Product image" class="product-image center-block">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6">
                    <h4 class="name">Product name</h4>
                    <div class="list-group d-none d-sm-block">
                        <span class="list-group-item"><small>Category:</small><span class="category">?</span></span>
                        <span class="list-group-item"><small>Producer:</small><span class="producer">?</span></span>
                    </div>
                    <div class="list-group item-properties">
                        <div class="list-group item-properties">
                            <span class="list-group-item"><small>Price:</small><span class="price">0</span></span>
                            <span class="list-group-item"><small>Count:</small><input type="number" class="count" value="1"
                                                                                      min="1" max="10"></span>
                            <span class="list-group-item"><small>Cost:</small><span class="cost">0</span></span>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <img id="addToCartIndi" src="web/static/img/loading.gif" class="d-none" alt="loading...">
                    <button id="addToCart" type="button" class="btn btn-primary">Add to cart</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</div>