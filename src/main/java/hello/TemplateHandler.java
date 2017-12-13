package hello;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine;
import io.vertx.ext.web.templ.TemplateEngine;

public class TemplateHandler implements Handler<RoutingContext> {

    private final TemplateEngine engine = FreeMarkerTemplateEngine.create();

    @Override
    public void handle(RoutingContext context) {

        // set variable
        context.put("greeting", "Hello from FreeMarkerTemplateEngine!");
        
        engine.render(context, "templates/", "hello.ftl", res -> {
            if (res.succeeded()) {
                context.response()
                        .end(res.result());
            } else {
                context.fail(res.cause());
            }
        });
    }

}
