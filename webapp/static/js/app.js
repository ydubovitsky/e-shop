// Обновить скрипты в Браузере хром -> Ctrl + F5
;$(function(){
	var init = function (){
		initBuyBtn();
		$('#addProductPopup .count').change(calculateCost);
		$('#addToCart').click(addProductToCart);
		$('#loadMore').click(loadMoreProducts);
		initSearchForm();
		$('#goSearch').click(goSearch);
		$('.remove-product').click(removeProductFromCart);
	};

	var showAddProductPopup = function (){
		var idProduct = $(this).attr('data-id-product');
		var product = $('#product'+idProduct);
		$('#addProductPopup').attr('data-id-product', idProduct);
		$('#addProductPopup .product-image').attr('src', product.find('.thumbnail img').attr('src'));
		$('#addProductPopup .name').text(product.find('.name').text());
		var price = product.find('.price').text();
		$('#addProductPopup .price').text(price);
		$('#addProductPopup .category').text(product.find('.category').text());
		$('#addProductPopup .producer').text(product.find('.producer').text());
		$('#addProductPopup .count').val(1);
		$('#addProductPopup .cost').text(price);
		$('#addToCart').removeClass('hidden');
		$('#addToCartIndicator').addClass('hidden');
		$('#addProductPopup').modal({
			show:true
		});
	};

	var initBuyBtn = function(){
		$('.buy-btn').click(showAddProductPopup);
	};

	/**
	 * Функция добавления продуктов в корзину
	 */
	var addProductToCart = function (){
		var idProduct = $('#addProductPopup').attr('data-id-product');
		var currentTotalCount = $('#currentShoppingCart .total-count').text(); // текущее количество из корзины
		var currentTotalCost = parseInt($('#currentShoppingCart .total-cost').text()); // текущая цена из корзины
		var popUpCount = parseInt($('#addProductPopup .count').val()); // считаное значение из popUp
		var popUpCost = parseInt(($('#addProductPopup .cost').text()).match(/\d+/)); // считаное значение из popUp
		var url = '/ajax/json/product/add';
		$.ajax({
			url : url,
			method : 'POST',
			data : {
				idProduct: idProduct,
				count: popUpCount
			},
			success : function(data) {
				var sum = parseInt(data.totalCount,10) + parseInt(currentTotalCount, 10);
				$('#currentShoppingCart .total-count').text("");
				$('#total-count-header').text(sum); //! Эта чертова хрень не работаеТ!!!!
				$('#currentShoppingCart .total-cost').text(currentTotalCost + data.totalCost);
				$('#currentShoppingCart').removeClass('hidden');
				$('#addProductPopup').modal('hide');
			},
			error : function(data) {
				alert('Error' + data);
			}
		});
	};

	var calculateCost = function(){
		var priceStr = $('#addProductPopup .price').text();
		var price = parseFloat(priceStr.replace('$',' '));
		var count = parseInt($('#addProductPopup .count').val());
		var min = parseInt($('#addProductPopup .count').attr('min'));
		var max = parseInt($('#addProductPopup .count').attr('max'));
		if(count >= min && count <= max) {
			var cost = price * count;
			$('#addProductPopup .cost').text('$ '+cost);
		} else {
			$('#addProductPopup .count').val(1);
			$('#addProductPopup .cost').text(priceStr);
		}
	};

	/**
	 * 		var pathname = window.location.pathname; // Returns path only (/path/example.html)
	 // 	var url      = window.location.href;     // Returns full URL (https://example.com/path/example.html)
	 // 	var origin   = window.location.origin;   // Returns base URL (https://example.com)

	 // 	https://www.w3schools.com/jsref/prop_loc_search.asp
	 */
	var loadMoreProducts = function (){
		console.log(location.pathname);
		console.log(location.search);
		$('#loadMore').addClass('hidden');
		$('#loadMoreIndicator').removeClass('hidden');
		var pageCount = parseInt($('#productList').attr("data-page-count"));
		var pageNumber = parseInt($('#productList').attr("data-page-number"));
		// (pageNumber + 1) - отправляет на сервер запрос текущая страница + 1
		var url = '/ajax/html/more' + location.pathname + '?page=' + (pageNumber + 1) + "&" + location.search.substring(1);
		$.ajax({
			url : url,
			success : function(html) {
				$('#productList .row').append(html);
				pageNumber++; // Увеличиваем page number
				if (pageNumber < pageCount) { // Если есть еще страницы для отображения
					$('#productList').attr("data-page-number", pageNumber);
				} else { // В противном случае удаляем кнопку
					$('#loadMore').remove();
				}
				$('#loadMoreIndicator').addClass('hidden');
				$('#loadMore').removeClass('hidden');
			},
			error : function(data) {
				alert('Error' + data);
			}
		});
	};

	var initSearchForm = function (){
		$('#allCategories').click(function(){
			$('.categories .search-option').prop('checked', $(this).is(':checked'));
		});
		$('.categories .search-option').click(function(){
			$('#allCategories').prop('checked', false);
		});
		$('#allProducers').click(function(){
			$('.producers .search-option').prop('checked', $(this).is(':checked'));
		});
		$('.producers .search-option').click(function(){
			$('#allProducers').prop('checked', false);
		});
	};

	var goSearch = function(){
		var isAllSelected = function(selector) {
			var unchecked = 0;
			$(selector).each(function(index, value) {
				if(!$(value).is(':checked')) {
					unchecked ++;
				}
			});
			return unchecked === 0;
		};
		if(isAllSelected('.categories .search-option')) {
			$('.categories .search-option').prop('checked', false);
		}
		if(isAllSelected('.producers .search-option')) {
			$('.producers .search-option').prop('checked', false);
		}
		$('form.search').submit();
	};

	var confirm = function (msg, okFunction) {
		if(window.confirm(msg)) {
			okFunction();
		}
	};

	var removeProductFromCart = function (){
		var btn = $(this);
		confirm('Are you sure?', function(){
			executeRemoveProduct(btn);
		});
	};

	var refreshTotalCost = function () {
		var total = 0;
		$('#shoppingCart .item').each(function(index, value) {
			var count = parseInt($(value).find('.count').text());
			var price = parseFloat($(value).find('.price').text().replace('$', ' '));
			var val = price * count;
			total = total + val;
		});
		$('#shoppingCart .total').text('$'+total);
	};

	var executeRemoveProduct = function (btn) {
		var idProduct = btn.attr('data-id-product');
		var count = btn.attr('data-count');
		btn.removeClass('btn-danger');
		btn.removeClass('btn');
		btn.addClass('load-indicator');
		var text = btn.text();
		btn.text('');
		btn.off('click');

		setTimeout(function(){
			var data = {
				totalCount : 1,
				totalCost : 1
			};
			if(data.totalCount === 0) {
				window.location.href = 'products.html';
			} else {
				var prevCount = parseInt($('#product'+idProduct+' .count').text());
				var remCount = parseInt(count);
				if(remCount === prevCount) {
					$('#product'+idProduct).remove();

					//
					if($('#shoppingCart .item').length === 0) {
						window.location.href = 'products.html';
					}
					//
				} else {
					btn.removeClass('load-indicator');
					btn.addClass('btn-danger');
					btn.addClass('btn');
					btn.text(text);
					btn.click(removeProductFromCart);
					$('#product'+idProduct+' .count').text(prevCount - remCount);
					if(prevCount - remCount == 1) {
						$('#product'+idProduct+' a.remove-product.all').remove();
					}
				}
				refreshTotalCost();
			}
		}, 1000);
	}

	init();
});