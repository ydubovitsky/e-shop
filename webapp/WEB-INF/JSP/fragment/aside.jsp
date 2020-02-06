<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>

<div class="d-block d-sm-none xs-option-container">
    <a class="pull-right" data-toggle="collapse" href="#productCatalog">Product Catalog<span
            class="caret"></span></a>
    <a class="pull-left" data-toggle="collapse" href="#findProducts">Find products<span class="caret"></span></a>
</div>
<!-- Search -->
<form class="search" action="/search">
    <div id="findProducts" class="panel panel-success collapse">
        <div class="panel-heading">Find products</div>
        <div class="panel-body">
            <div class="input-group">
                <input type="text" name="query" class="form-control" placeholder="Search for...">
                <span class="input-group-btn">
                  <a id="goSearch" class="btn btn-default" type="button">Go!</a>
                </span>
            </div>
            <div class="more-options">
                <a data-toggle="collapse" href="#searchOptions">More filters<span class="caret"></span></a>
            </div>
        </div>
        <div id="searchOptions" class="collapse">
            <div class="panel-heading">Category filter</div>
            <div class="panel-body categories">
                <label><input type="checkbox" id="allCategories">All</label>
                <div class="form-group">
                    <div class="checkbox">
                        <label><input type="checkbox" name="category" value="0" class="search-option">E-Books (17)</label>
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" name="category" value="1" class="search-option">Phones (23)</label>
                    </div>
                </div>
            </div>
            <div class="panel-heading">Producers filter</div>
            <div class="panel-body producers">
                <label><input type="checkbox" id="allProducers">All</label>
                <div class="form-group">
                    <div class="checkbox">
                        <label><input type="checkbox" name="producers" value="0" class="search-option">Samsung (18)</label>
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" name="producers" value="1" class="search-option">Apple (67)</label>
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" name="producers" value="2" class="search-option">Nokia (13)</label>
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" name="producers" value="3" class="search-option">LG (7)</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<!-- Search -->
<!-- Categories -->
<div id="productCatalog" class="panel panel-success collapse">
    <div class="panel-heading">Product Catalog</div>
    <div class="list-group">
        <a href="/products" class="list-group-item">Phones<span class="badge">11</span></a>
        <a href="/products" class="list-group-item">TV-sets<span class="badge">7</span></a>
        <a href="/products" class="list-group-item">Computers<span class="badge">45</span></a>
        <a href="/products" class="list-group-item">Laptops<span class="badge">33</span></a>
        <a href="/products" class="list-group-item">Vestibulum<span class="badge">5</span></a>
    </div>
</div>
<!-- Categories -->