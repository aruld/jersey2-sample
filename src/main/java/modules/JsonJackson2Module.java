package modules;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.jaxrs.xml.JacksonJaxbXMLProvider;
import com.fasterxml.jackson.jaxrs.xml.JacksonXMLProvider;
import org.glassfish.hk2.scopes.Singleton;
import org.glassfish.jersey.internal.inject.AbstractModule;
import org.glassfish.jersey.media.json.internal.entity.JsonWithPaddingProvider;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Module with JAX-RS Jackson 2 JSON providers.
 *
 * @author Arul Dhesiaseelan (arul at flux.ly)
 */
public class JsonJackson2Module extends AbstractModule {

    private static final Collection<Class<?>> PROVIDERS = Collections.unmodifiableList(Arrays.asList(new Class<?>[]{
            JacksonJsonProvider.class,
            JacksonXMLProvider.class,
            JacksonJaxbXMLProvider.class,
            JsonWithPaddingProvider.class
    }));

    /**
     * Returns providers used for serialization and deserialization of Json entities.
     *
     * @return {@link Collection} of providers.
     */
    public static Collection<Class<?>> getProviders() {
        return PROVIDERS;
    }

    @Override
    protected void configure() {
        bindSingletonReaderWriterProvider(JacksonJsonProvider.class);
        bindSingletonReaderWriterProvider(JacksonXMLProvider.class);
        bindSingletonReaderWriterProvider(JacksonJaxbXMLProvider.class);
        bindSingletonReaderWriterProvider(JsonWithPaddingProvider.class);
    }

    private <T extends MessageBodyReader<?> & MessageBodyWriter<?>> void bindSingletonReaderWriterProvider(Class<T> provider) {
        bind().to(provider).in(Singleton.class);
        bind(MessageBodyReader.class).to(provider);
        bind(MessageBodyWriter.class).to(provider);
    }
}

