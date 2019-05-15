package proto;

import org.apache.camel.builder.RouteBuilder;

public class SimpleRoute extends RouteBuilder {
    public void configure() throws Exception {
        onException(Exception.class).process(exchange -> System.out.println("in route exception handler"));

        from("direct:a")
                .process(exchange -> {
                    System.out.println(exchange.getMessage().getBody());
                    exchange.getMessage().setBody("`a` executed");
                })
                .to("mock:b")
                .process(exchange -> {throw new AnyException();})
                .process(exchange -> System.out.println(exchange.getMessage().getBody()))
                .to("mock:c");
    }
}
