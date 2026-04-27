package app.action;


import app.framework.Cohort12Framework;
import app.framework.Cohort12Table;
import app.framework.GenericDao;
import app.framework.PageContent;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.annotation.PostConstruct;
import javax.sql.DataSource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.BigDecimalConverter;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BaseAction<T> extends HttpServlet {

    @Inject
    protected Cohort12Framework framework;

    private DataSource dataSource;
    protected GenericDao<T, Long> genericDao;

    @Inject
    public void setDataSource(@Named("primaryDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void init() {
        genericDao = new GenericDao<>(this.getType(), dataSource);
    }

    @SuppressWarnings("unchecked")
    public T serializeForm(Map<String, String[]> requestMap) {

        System.out.println("Form Serialization....");

        try {
            T clazzInstance = this.getType().newInstance();

            BeanUtilsBean beanUtilsBean = new BeanUtilsBean(new ConvertUtilsBean() {
                @Override
                public Object convert(String value, Class clazz) {
                if (clazz.isEnum()) {
                    return Enum.valueOf(clazz, value);
                } else if (clazz == Date.class) {
                    // web forms return the date in the form
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        return dateFormat.parse(value);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    return super.convert(value, clazz);
                }
                }
            });

            ConvertUtils.register(new BigDecimalConverter(), BigDecimal.class);

            beanUtilsBean.populate(clazzInstance, requestMap);

            return clazzInstance;

        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e ) {
            throw new RuntimeException(e);
        }

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //if session exist use it, otherwise create a new one
        HttpSession session = req.getSession();

        try {
            genericDao.save(this.serializeForm(req.getParameterMap()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (this.getType().isAnnotationPresent(Cohort12Table.class)) {
            resp.sendRedirect(this.getType()
                .getAnnotation(Cohort12Table.class).tableUrl());

        } else {
            resp.sendRedirect("./home");
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{

        request.setAttribute(PageContent.CONTENT.name(),
                framework.htmlForm(this.getType()));
        RequestDispatcher rd = request.getRequestDispatcher("./app_page");
        rd.include(request, response);

    }

    @SuppressWarnings("unchecked")
    public Class<T> getType() {
        ParameterizedType superClass =
            (ParameterizedType) getClass().getGenericSuperclass();

        return (Class<T>) superClass.getActualTypeArguments()[0];
    }

    public List<T> returnData(){
        return genericDao.findAll();
    }

}