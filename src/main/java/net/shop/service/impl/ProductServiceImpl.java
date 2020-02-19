package net.shop.service.impl;

import net.shop.entity.impl.Product;
import net.shop.exception.InternalServerErrorException;
import net.shop.jdbc.JDBCUtils;
import net.shop.jdbc.ResultSetFactory;
import net.shop.jdbc.ResultSetHandler;
import net.shop.service.ProductService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

class ProductServiceImpl implements ProductService {


    private static final ResultSetHandler<List<Product>> productsResultSetHandler = ResultSetFactory.getListResultSetHandler(ResultSetFactory.PRODUCT_RESULT_SET_HANDLER);

    private DataSource dataSource;

    public ProductServiceImpl(DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

    /**
     * Возвращает список всех продуктов
     *
     * @param page  - какую страницу продуктов отобразить
     * @param limit - количество продуктов для отображения
     * @return
     */
    @Override
    public List<Product> listAllProducts(int page, int limit) {
        try(Connection c = dataSource.getConnection()) {
            int offset = (page - 1) * limit; //* offset - это с какой позиции выводить объекты из БД
            String sql = "select p.*, c.name as category, pr.name as producer from product p, producer pr, category c where c.id=p.id_category and pr.id=p.id_producer limit ? offset ?";
            List<Product> list = JDBCUtils.select(c, sql, productsResultSetHandler, limit, offset);
            return list;
        } catch (SQLException e) { //* Это checked исключение, преобразуется в unchecked
            throw new InternalServerErrorException("Cant execute sql query " + e.getMessage(), e); //! Преобразуем в RuntimeException (unchecked) для того, чтобы эти исключения пробрасывались до уровня фильтров, а там логировались
        }
    }
}
