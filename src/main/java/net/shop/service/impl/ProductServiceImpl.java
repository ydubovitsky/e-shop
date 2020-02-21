package net.shop.service.impl;

import net.shop.entity.impl.Category;
import net.shop.entity.impl.Producer;
import net.shop.entity.impl.Product;
import net.shop.exception.InternalServerErrorException;
import net.shop.form.SearchForm;
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
    private static final ResultSetHandler<List<Category>> categoryResultSetHandler = ResultSetFactory.getListResultSetHandler(
            ResultSetFactory.CATEGORY_RESULT_SET_HANDLER
    );
    private static final ResultSetHandler<List<Producer>> producersResultSetHandler = ResultSetFactory.getListResultSetHandler(
            ResultSetFactory.PRODUCER_RESULT_SET_HANDLER
    );
    private static final ResultSetHandler<Integer> productCountResultSetHandler = ResultSetFactory.getSingleResultHandler(
            ResultSetFactory.COUNT_PRODUCT_RESULT_SET_HANDLER
    );

    /**
     * A factory for connections to the physical data source that this DataSource object represents.
     */
    private DataSource dataSource;

    /**
     * Constructor
     * @param dataSource
     */
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

    /**
     * Возвращает список продуктов конкретной категории
     * @param categoryUrl
     * @param page - какая страница по счету
     * @param limit
     * @return
     */
    @Override
    public List<Product> listProductsByCategory(String categoryUrl, int page, int limit) {
        try(Connection c = dataSource.getConnection()) {
            int offset = (page - 1) * limit;
            String sql = "select p.*, c.name as category, pr.name as producer from product p, producer pr, category c " +
                    "where c.id=p.id_category and pr.id=p.id_producer and c.url=? limit ? offset ?";
            List<Product> list = JDBCUtils.select(c, sql, productsResultSetHandler, categoryUrl, limit, offset);
            return list;
        } catch (SQLException e) {
            throw new InternalServerErrorException("Cant execute sql query " +e.getMessage(), e );
        }
    }

    /**
     * This method returns all categories;
     * @return
     */
    @Override
    public List<Category> listAllCategories() {
        try(Connection c = dataSource.getConnection()) {
            String sql = "select c.* from category c";
            List<Category> categories = JDBCUtils.select(c, sql, categoryResultSetHandler);
            return categories;
        } catch (SQLException e) {
            throw new InternalServerErrorException("Cant execute sql query " + e.getMessage(), e);
        }
    }

    public List<Producer> listAllProducers() {
        try(Connection c = dataSource.getConnection()) {
            String sql = "select p.* from producer p order by p.name";
            List<Producer> producers = JDBCUtils.select(c, sql, producersResultSetHandler);
            return producers;
        } catch (SQLException e) {
            throw new InternalServerErrorException("Cant execute sql query " + e.getMessage(), e);
        }
    }

    @Override
    public int countAllProducts() {
        try(Connection c = dataSource.getConnection()) {
            String sql = "select count(*) from product";
            int count = JDBCUtils.select(c, sql, productCountResultSetHandler);
            return count;
        } catch (SQLException e) {
            throw new InternalServerErrorException("Cant execute sql query " + e.getMessage(), e);
        }
    }

    @Override
    public int countProductsByCategories(String categoryUrl) {
        try(Connection c = dataSource.getConnection()) {
            String sql = "select count(*) from product p, category c where p.id_category=c.id and c.url=?";
            int count = JDBCUtils.select(c, sql, productCountResultSetHandler, categoryUrl);
            return count;
        } catch (SQLException e) {
            throw new InternalServerErrorException("Cant execute sql query " + e.getMessage(), e);
        }
    }

    /**
     * Поиск по запросу из поисковой строки
     */
    @Override
    public List<Product> listProductsBySearchForm(SearchForm searchForm, int page, int limit) {
        try(Connection c = dataSource.getConnection()) {
            int offset = (page - 1) * limit;
            String sql = "select p.*, c.name as category, pr.name as producer from product p, producer pr, category c where c.id=p.id_category and pr.id=p.id_producer and (p.name ilike ? or p.description ilike ?) limit ? offset ?";
            //! searchForm.getQuery() - это то, что ввел пользователь в поисковой строке
            //! % - это sql синтаксис, любая строка любой длины (включая нулевую длину)
            return JDBCUtils.select(c, sql, productsResultSetHandler, "%"+searchForm.getQuery()+"%", "%"+searchForm.getQuery()+"%", limit, offset);
        } catch (SQLException e) {
            throw new InternalServerErrorException("Cant execute sql query " + e.getMessage(), e);
        }
    }

    @Override
    public int countProductsBySearchForm(SearchForm searchForm) {
        try(Connection c = dataSource.getConnection()) {
            String sql = "select count(*) from product p, category c, producer pr " +
                    " where (p.name ilike ? or p.description ilike ?) and c.id=p.id_category and pr.id=p.id_producer";
            return JDBCUtils.select(c, sql, productCountResultSetHandler, "%"+searchForm.getQuery()+"%", "%"+searchForm.getQuery()+"%");
        } catch (SQLException e) {
            throw new InternalServerErrorException("Cant execute sql query " + e.getMessage(), e);
        }
    }
}
