;$(function() {

    let init = function() {
        initBuyBtn();
        initAddToCart();
        $('#addProductPopup .count').change(calculateCost);
        $('#loadMore').click(loadMoreProducts);
    };

    /**
     * This указывает на тот объект, который вызывает эту функцию ($('.buy-btn') - кнопка)
     * Функция заполняет Popup.
     * Для каждого товара, создается свой popup, c параметрами этого товара
     */
    let showAddProductPopup = function() {
        let idProduct = $(this).attr('data-id-product'); // как тут работает this?
        let product = $('#product'+idProduct);
        $('#addProductPopup').attr('data-id-product', idProduct); // атрибуту присваивается значение(idProduct)
        $('#addProductPopup .product-image').attr('src', product.find('.thumbnail img').attr('src'));
        $('#addProductPopup .name').text(product.find('.name').text());
        let price = product.find('.price').text();
        $('#addProductPopup .category').text(product.find('.category').text());
        $('#addProductPopup .producer').text(product.find('.producer').text());
        $('#addProductPopup .price').text(price);
        $('#addProductPopup .cost').text(price);
        $('#addProductPopup .count').val(1);
        $('#addProductPopup').modal({
            show: true
        });
    };

    /**
     * Присваивает кнопке обработчик по клику.
     */
    let initBuyBtn = function() {
        $('.buy-btn').click(showAddProductPopup);
    };

    let addProductToCart = function() {
        let idProduct = $('#addProductPopup').attr('data-id-product'); // считывает значение атрибута
        let count = $('#addProductPopup .count').attr('value');
        $('#addToCartIndi').removeClass('d-none');
        $('#addToCart').addClass('d-none');
        setTimeout(function() {
            let data = {
                totalCount: count,
                totalCost: 2000
            };
            let currentCost = Number($('#currentShoppingCart .total-cost').text());
            let currentCount = Number($('#currentShoppingCart .total-count').text());
            $('#currentShoppingCart .total-count').text(Number(currentCount) + Number(data.totalCount));
            $('#currentShoppingCart .total-cost').text(currentCost + data.totalCost);
            $('#currentShoppingCart').removeClass('d-none');
            $('#addProductPopup').modal('hide');
        }, 800);
    };

    let initAddToCart = function() {
        $('#addToCart').click(addProductToCart);
    }

    /**
     * 
     */
    let calculateCost = function() {
        let priceStr = $('#addProductPopup .price').text();
        let price = parseFloat(priceStr.replace('$', ' '));
        let count = parseInt($('#addProductPopup .count').val());
        let min = parseInt($('#addProductPopup .count').attr('min'));
        let max = parseInt($('#addProductPopup .count').attr('max'));
        if(count >= min && count <= max) {
            let cost = price * count;
            $('#addProductPopup .cost').text('$ ' + cost);
        } else {
            $('#addProductPopup .count').val(1);
            $('#addProductPopup .cost').text(priceStr);

        }
    };

    /**
     * Обработчик нажатия кнопки загрузить еще
     */
    let loadMoreProducts = function() {
        $('#loadMore').addClass('d-none');
        $('#loadMoreIndicator').removeClass('d-none');
        setTimeout(function() {
            $('#loadMoreIndicator').addClass('d-none');
            $('#loadMore').removeClass('d-none');
        }, 800);
    };

    init();
});