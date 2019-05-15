package proto;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

public class ExampleApp {
    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();
        SimpleRoute simpleRoute = new SimpleRoute();
        simpleRoute.onException(AnyException.class).process(exchange -> System.out.println("out of the route exception handler"));
        context.addRoutes(simpleRoute);
        context.start();
        ProducerTemplate template = context.createProducerTemplate();
        template.sendBody("direct:a", "new body");
        context.stop();
    }
}
