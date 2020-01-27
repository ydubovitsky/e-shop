$(function () {
    let init = function () {
        initBuyBtn();
        initAddToCart();
        $("#addProductPopup .count").change(calculateCost);
        $("#loadMore").click(loadMoreProducts);
        initSearchForm();
        $("#goSearch").click(goSearch);
        $(".remove-product").click(removeProductFromCart);
        totalCostProducts();
        //$('addToCart').click(getItemProperties);
    };

    /**
     * This указывает на тот объект, который вызывает эту функцию ($('.buy-btn') - кнопка)
     * Функция заполняет Popup.
     * Для каждого товара, создается свой popup, c параметрами этого товара
     */
    let showAddProductPopup = function () {
        let idProduct = $(this).attr("data-id-product"); // как тут работает this?
        let product = $("#product" + idProduct);
        $("#addProductPopup").attr("data-id-product", idProduct); // атрибуту присваивается значение(idProduct)
        $("#addProductPopup .product-image").attr(
            "src",
            product.find(".thumbnail img").attr("src")
        );
        $("#addProductPopup .name").text(product.find(".name").text());
        let price = product.find(".price").text();
        $("#addProductPopup .category").text(product.find(".category").text());
        $("#addProductPopup .producer").text(product.find(".producer").text());
        $("#addProductPopup .price").text(price);
        $("#addProductPopup .cost").text(price);
        $("#addProductPopup .count").val(1);
        $("#addProductPopup").modal({
            show: true
        });
    };

    /**
     * Присваивает кнопке обработчик по клику.
     */
    let initBuyBtn = function () {
        $(".buy-btn").click(showAddProductPopup);
    };

    /**
     * Добавляем товары в корзину
     */
    let addProductToCart = function () {
        let idProduct = $("#addProductPopup").attr("data-id-product"); // считывает значение атрибута? Каким образом?
        let count = parseInt($("#addProductPopup .count").attr("value"));
        $("#addToCartIndi").removeClass("d-none");
        $("#addToCart").addClass("d-none");
        setTimeout(function () {
            let data = {
                totalCount: getItemProperties(), // используем внешнюю функцию
                totalCost: 2000
            };
            let currentCost = parseInt($("#currentShoppingCart .total-cost").text());
            let currentCount = parseInt($("#currentShoppingCart .shopping-cart-desc .total-count").text());
            $("#currentShoppingCart .shopping-cart-desc .total-count").text(currentCount + data.totalCount);
            $("#currentShoppingCart .dropdown-toggle .total-count").text(currentCount + data.totalCount);
            $("#currentShoppingCart .total-cost").text(currentCost + data.totalCost);
            $("#currentShoppingCart").removeClass("d-none");
            $("#addProductPopup").modal("hide");
        }, 800);
        setTimeout(function() {
            $("#addToCartIndi").addClass("d-none");
            $("#addToCart").removeClass("d-none");
        }, 800);        
    };

    /**
     * Получение количества добавляемых в корзину товаров из popup-a.
     */
    let getItemProperties = function() {
        let count = parseInt($('#addProductPopup .item-properties .count').val());
        return count;
    };

    let initAddToCart = function () {
        $("#addToCart").click(addProductToCart);
    };

    /**
     * Вычисляем стоимость в зависимости от количества товара
     */
    let calculateCost = function () {
        let priceStr = $("#addProductPopup .price").text();
        let price = parseFloat(priceStr.replace("$", " "));
        let count = parseInt($("#addProductPopup .count").val());
        let min = parseInt($("#addProductPopup .count").attr("min"));
        let max = parseInt($("#addProductPopup .count").attr("max"));
        if (count >= min && count <= max) {
            let cost = price * count;
            $("#addProductPopup .cost").text("$ " + cost);
        } else {
            $("#addProductPopup .count").val(1);
            $("#addProductPopup .cost").text(priceStr);
        }
    };

    /**
     * Обработчик нажатия кнопки загрузить еще
     */
    let loadMoreProducts = function () {
        $("#loadMore").addClass("d-none");
        $("#loadMoreIndicator").removeClass("d-none");
        setTimeout(function () {
            $("#loadMoreIndicator").addClass("d-none");
            $("#loadMore").removeClass("d-none");
        }, 800);
    };

    /**
     * Инициализация checkbox
     */
    let initSearchForm = function () {
        // categories
        $("#allCategories").click(function () {
            $(".categories .search-option").prop("checked", $(this).is(":checked")); // если выбраны все категории, то ставятся везде галки
        });
        $(".categories .search-option").click(function () {
            $("#allCategories").prop("checked", false);
        });
        // allProducers
        $("#allProducers").click(function () {
            $(".producers .search-option").prop("checked", $(this).is(":checked")); // если выбраны все категории, то ставятся везде галки
        });
        $(".producers .search-option").click(function () {
            $("#allProducers").prop("checked", false);
        });
    };

    /**
     * Функция поиска
     */
    let goSearch = function () {
        /**
         * selector - массив элементов.
         * value - сам объект-селектор из массива. Если у каждого селектора стоит состояние :checked, тогда
         * увеличивается счетчик.
         * @param {} selector
         */
        var isAllSelected = function (selector) {
            let unchecked = 0;
            $(selector).each(function (index, value) {
                if (!$(value).is(":checked")) {
                    unchecked++;
                }
            });
            return unchecked === 0;
        };
        if (isAllSelected(".categories .search-option")) {
            // если выбраны все элементы, то галочки снимаются
            $(".categories .search-option").prop("checked", false);
        }
        if (isAllSelected(".producers .search-option")) {
            $(".producers .search-option").prop("checked", false);
        }
        $("form.search").submit();
    };

    /**
     * Функция вызывает всплывающее окно и если true - вызывается okFunction
     * @param {} msg - вопрос в window
     * @param {*} okFunction
     */
    let confirn = function (msg, okFunction) {
        if (window.confirm(msg)) {
            okFunction();
        }
    };

    let removeProductFromCart = function () {
        let btn = $(this);
        confirn("Are you sure?", function () {
            // нельзя сразу executeRemoveProduct(btn), она вызовется сразу
            executeRemoveProduct(btn);
        });
    };

    let executeRemoveProduct = function (btn) {
        // узнаем id продукта (ПО КНОПКЕ)
        let idProduct = btn.attr("data-id-product");
        let count = btn.attr("data-count");
        btn.removeClass("btn");
        btn.removeClass("btn-danger");
        btn.addClass("load-indicator");
        let text = btn.text(); // сохраняем текс из кнопки
        btn.text("");
        btn.off("click");

        // Эмуляция ответа от сервера, запускается функция и мы получаем значения
        // помни, это скрипт, он работает сверху вниз и функция эта вызывается после всяких там let, тех что выще
        setTimeout(function () {
            let data = {
                totalCount: 1,
                totalCost: 1
            };
            // Если сервер прислал количество(totalCount === 0) мы переходим на главную страницу
            if (data.totalCount === 0) {
                window.location.href = "products.html";
            } else {
                // Тут мы уже обращаемся к продукту в таблице, а не к кнопке!
                let prevCount = parseInt($("#product" + idProduct + " .count").text()); // !!!!!! Не забудь пробел ' .count'
                let removeCount = parseInt(count);
                if (removeCount === prevCount) {
                    $("#product" + idProduct).remove();
                    /**
                     * Самовызывающаяся функция, проверяет если в таблице нет больше товара, то делает редирект
                     * Эта функция эмулирует сервер!
                     */
                    (function checkingCountProduct() {
                        if ($("#shoppingCard .item").length == 0) {
                            window.location.href = "products.html";
                        }
                    })();
                } else {
                    btn.removeClass("load-indicator");
                    btn.addClass("btn");
                    btn.addClass("btn-danger");
                    btn.text(text);
                    btn.click(removeProductFromCart);
                    $("#product" + idProduct + " .count").text(prevCount - removeCount);
                }
            }
            totalCostProducts();
        }, 1000);
    };

    /**
     * Функция считает суммарную цену
     */
    let totalCostProducts = function () {
        let total = 0;
        $('#shoppingCard .item').each(function (index, value) {
            let count = parseInt(($(value).find('.count')).text());
            let price = parseFloat($(value).find('.price').text().replace('$', ' '));
            let val = price * count;
            total = total + val;
        });
        $('#shoppingCard tbody .total').text('$' + total);
    };

    // Инициализирует функцию, которая все запускает
    init();
});
