package invoice.split.store;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class Storage {

    private final StandardServiceRegistry registry;
    private final SessionFactory sf;

    private Storage() {
        registry = new StandardServiceRegistryBuilder().configure().build();
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    private static class Holder {
        private static Storage st = new Storage();
    }

    public static Storage getInstance() {
        return Holder.st;
    }

    public SessionFactory getSessionFactory() {
        return sf;
    }

    public ServiceRegistry getServiceRegistry() {
        return registry;
    }
}