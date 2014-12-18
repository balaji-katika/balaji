package org.balaji;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.balaji.config.ProductConfig;
import org.balaji.exception.BalajiException;
import org.balaji.hibernate.bo.CustomerBo;
import org.balaji.hibernate.model.Customer;
import org.balaji.utils.SysConstants;
import org.balaji.utils.SysPropertiesMap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Telemetry utility to extract and interpret telemetry bundles uploaded by the data collector
 * 
 * @author root
 *
 */
public class App {

    private static Logger logger = Logger.getLogger(App.class);
    public static ApplicationContext springAppContext;

    public static void main(String[] args) {
        /*
         * Initialize the apache logger
         */
        PropertyConfigurator.configure(System.getProperty(
                SysPropertiesMap.LOG_CONFIG,
                SysConstants.CONST_DEF_LOG_CONF_LOCATION));

        /*
         * Initialize the application
         */
        initialize();

        sampleForCustomer();
    }

    /**
     * Sample code to retrieve Customer ORM from the db
     *
     */
    private static void sampleForCustomer() {
        Customer customer = null;
        CustomerBo cbo =
                (CustomerBo) App.springAppContext.getBean("customerBo");
        /*
         * Replace the below null values with actuals
         */
        // Fetch customer
        customer = cbo.getCustomer(null, null);
        if (customer == null) {
            customer = new Customer(null, null, null);
            cbo.save(customer);
        }
    }

    /**
     * Initialize the system
     */
    private static void initialize() {
        /*
         * Throw error if productConfig not specified
         */
        String productConfig =
                System.getProperty(SysPropertiesMap.PROP_PRODUCT_CONFIG, null);
        if (productConfig == null) {
            System.err
                    .println("Property productConfig not mentioned in the configuration file");
            System.err.println("Unable to proceed....Exiting...");
            System.exit(-4);
        }

        try {
            ProductConfig.init(productConfig);
        } catch (BalajiException e) {
            logger.error("Error initializing Product configuration", e);
            System.err.println("Error initiliazing product configuration");
            System.exit(-5);
        }

        /*
         * Initialize spring/hibernate
         */
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext(
                        SysConstants.getBeanLocation());
        springAppContext = applicationContext;

        initSSLTrustStore();

    }

    /**
     * Initialize Java Store certificate repository
     */
    private static void initSSLTrustStore() {

        System.setProperty("javax.net.ssl.trustStore", System.getProperty(
                SysPropertiesMap.PROP_TRUST_STORE_LOCATION,
                "/usr/java/latest/jre/lib/security/cacerts"));
        System.setProperty("javax.net.ssl.trustStorePassword", System
                .getProperty(SysPropertiesMap.PROP_TRUST_STORE_PASSWORD,
                        "changeit"));
    }

}