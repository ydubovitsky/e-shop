package shop.service.impl;

import shop.entity.Category;
import shop.entity.Producer;
import shop.entity.Product;
import shop.exception.InternalServerErrorException;
import shop.jdbc.JDBCUtils;
import shop.jdbc.ResultSetHandler;
import shop.jdbc.ResultSetHandlerFactory;
import shop.service.ProductService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

class ProductServiceImpl implements ProductService {

    //TODO Разобраться с этим методом
    //* Там как бы обработчик плучает другой обработчик, который обрабатывает 1 строку ответа и формируется лист, но разберись еще
    private static final ResultSetHandler<List<Product>> productsResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.PRODUCT_RESULT_SET_HANDLER);

    private static final ResultSetHandler<List<Category>> categoryResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.CATEGORY_RESULT_SET_HANDLER);

    private static final ResultSetHandler<List<Producer>> producerResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.PRODUCER_RESULT_SET_HANDLER);

    private final DataSource dataSource;

    public ProductServiceImpl(DataSource dataSource) {
        super();
        this.dataSource = dataSource;
    }

    /**
     * @param page  - какую страницу продуктов отобразить
     * @param limit - максимальное количество продуктов для отображения
     * @return
     */
    @Override
    public List<Product> listAllProducts(int page, int limit) {
        try(Connection c = dataSource.getConnection()) {
            int offset = (page - 1) * limit; //? Что это
            return JDBCUtils.select(c, "select p.*, c.name as category, pr.name as producer from product p, producer pr, category c "
                    + "where c.id=p.id_category and pr.id=p.id_producer limit ? offset ?", productsResultSetHandler, limit, offset);
        } catch (SQLException s) { //! Это checked exception
            throw new InternalServerErrorException("Cant execute sql query: " + s.getMessage(), s);
        }
    }

    @Override
    public List<Product> listProductByCategory(String categoryUrl, int page, int limit) {
        try(Connection c = dataSource.getConnection()) {
            int offset = (page - 1) * limit;
            return JDBCUtils.select(c,
                    "select p.*, c.name as category, pr.name as producer from product p, category c, producer pr where c.url=? and pr.id=p.id_producer and c.id=p.id_category order by p.id limit ? offset ?",
                    productsResultSetHandler, categoryUrl, limit, offset);
        } catch (SQLException s) {
            throw new InternalServerErrorException("Cant execute sql query: " + s.getMessage(), s);
        }
    }

    @Override
    public List<Category> listAllCategory() {
        try(Connection c = dataSource.getConnection()) {
            return JDBCUtils.select(c, "select * from category order by id", categoryResultSetHandler);
        } catch (SQLException s) {
            throw new InternalServerErrorException("Cant execute sql query: " + s.getMessage(), s);
        }
    }

    @Override
    public List<Producer> listAllProducer() {
        try(Connection c = dataSource.getConnection()) {
            return JDBCUtils.select(c, "select * from producer order by id", producerResultSetHandler);
        } catch (SQLException s) {
            throw new InternalServerErrorException("Cant execute sql query: " + s.getMessage(), s);
        }
    }
}
